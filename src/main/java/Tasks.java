import com.google.api.services.calendar.model.Event;
import org.joda.time.*;

import java.io.IOException;
import java.util.*;

public class Tasks {

    // create a linked list to store tasks entered by the user
    LinkedList listOfTasks = new LinkedList();
    GoogleCalendar googleCalendar = new GoogleCalendar();

    String title;
    String userExit;
    String priority;
    String deadlineStr;
    String durationStr;
    DateTime deadline;
    Duration duration;
    final int MILLIS_IN_MIN = 60000;
    final int MILLIS_IN_HOUR = 3600000;
    Scanner scan = new Scanner(System.in);

    public Tasks() throws IOException {
    }

    // create a constant for priority values of type Enum
    enum priorityEnum{
        LOW(3),
        MEDIUM(2),
        HIGH(1);

        private final int priorityValue;
        priorityEnum(int value) {
            this.priorityValue = value;
        }
        public int getPriorityValue(){
            return this.priorityValue;
        }
    }
    
    // method for creating a task with parameters from the user
    public void addTask() {
        boolean exit = false;

        // continue the loop until the user quits the loop
        while (!exit) {
            System.out.println("Create a new task!");
            System.out.println("Set title for your task: ");
            title = scan.nextLine();
            duration = null;
            // ask for duration until the format is compatible
            do {
                System.out.println("Set duration for your task (HH:MM): ");
                durationStr = scan.nextLine();
                String[] exploded = durationStr.split(":");
                try {
                    int hours = Integer.parseInt(exploded[0]);
                    int minutes = Integer.parseInt(exploded[1]);
                    long hoursMillis = (long)hours * MILLIS_IN_HOUR;
                    long minutesMillis = (long)minutes * MILLIS_IN_MIN;
                    // new duration incl. buffer time of 20% of the task duration
                    duration = new Duration((hoursMillis + minutesMillis)/100*120);
                } catch (Exception e){
                    System.out.println("Something went wrong! Try again...");
                }
            } while (duration == null);

            deadline = null;
            // ask for deadline until the format is compatible
            do {
                try {
                    System.out.println("Set deadline for your task (DD/MM/YYYY): ");
                    deadlineStr = scan.nextLine();
                    deadline = TimeData.convertToDate(deadlineStr);
                } catch (Exception e){
                    System.out.println("Wrong date! Try again...");
                }
            } while (deadline == null);

            boolean isPriorityEnum = false;
            int priorityValue = 0;
            // ask for the priority until the format is compatible
            do {
                try {
                    System.out.println("Set priority HIGH, MEDIUM or LOW:");
                    priority = scan.nextLine();
                    priorityValue = priorityEnum.valueOf(priority.toUpperCase()).getPriorityValue();
                    isPriorityEnum = true;
                } catch (Exception e){
                    System.out.println("Something went wrong, try again!");
                }
            } while (!isPriorityEnum);

            // add the entered task into the list of tasks
            Task task = new Task(title, duration, priorityValue, deadline);
            this.listOfTasks.add(task);

            // sort the tasks using the comparator
            Comparator<Task> taskCom = new Comparator <Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    int comparison = 0;
                    comparison = task1.getDeadline().compareTo(task2.getDeadline());
                    if (comparison == 0){
                        comparison = Integer.compare(task1.getPriority(), task2.getPriority());
                    } else if (comparison == 0){
                        comparison = task1.getDuration().compareTo(task2.getDuration());
                    }
                    return comparison;
                }
            };
            Collections.sort(listOfTasks, taskCom);

            // ask user if add a new task - if exit, go to main menu
            System.out.println("Add more or exit?");
            userExit = scan.nextLine();
                if (userExit.equals("exit")) {
                    exit = true;
                }
            }
        }

    // get the list of all entered tasks that have not been sent to the calendar yet
    public void showListOfTasks(){
        if (this.listOfTasks.isEmpty()){
            System.out.println("No tasks to show");
        } else {
            // loop through the list of tasks and print each of them
            for (Object i:
                    listOfTasks) {
                System.out.println(i);
            }
        }
    }

    public void handleTasksToCalendar(int[] hours)
    {
        // foreach list of tasks and call the push to the calendar for each task
        for (Iterator<Task> i = listOfTasks.iterator(); i.hasNext(); ) {
            // get intervals per day where the task can be placed and subtract the hours from the duration of the task
            try {
                // get all events from the calendar from tomorrow till the deadline of the task
                DateTime now = new DateTime();
                LocalDate today = now.toLocalDate();
                LocalDate tomorrow = today.plusDays(1);
                DateTime start = tomorrow.toDateTimeAtStartOfDay(now.getZone());
                Days days = Days.daysBetween(start.toLocalDate(), this.deadline.toLocalDate());
                int daysCount = days.getDays();
                Task currentTask = i.next();
                // get the duration of the task in minutes
                long taskHoursInMinutes = currentTask.getDuration().getStandardMinutes();

                // loop through the number of days until the day of deadline
                for (int u = 0; u <= daysCount; u++){
                    // get the current day for looping through the events
                    DateTime currentDate = start.plusDays(u);
                    // get the end of the current day by adding 23 h and 59 m in minutes
                    DateTime endOfDay = currentDate.plusMinutes(1439);
                    // start of the work time interval in the current day - user defined
                    int currentHour = hours[0];
                    // create an array of 2 positions for the available slots in the calendar inside of work time interval with start and end hour
                    int[] currentFreeHours = new int[2];
                    // initialize the start of available time interval with the hour of work time interval, and the end hour is not yet found
                    currentFreeHours[0] = currentHour;
                    currentFreeHours[1] = -1;
                    // create an 2D arraylist to store free hours found in the calendar within the work time interval
                    List<int[]> freeHours = new ArrayList<>();
                    // retrieve the events from the specific calendar ID, within the work time interval and until the deadline date
                    List<Event> calendarEvents = this.googleCalendar.getEvents("cs.semesterproject@gmail.com", currentDate.getMillis(), endOfDay.getMillis());
                    // loop through each hour start during a day until the end of work time
                    do{
                        // when no events are found in a day within work interval, we can use the full working hours in the day to push the event
                        if (calendarEvents.isEmpty()) {
                            currentFreeHours[1] = currentHour + 1;
                        } else {
                            //TODO do I have a task that starts at this hour???
                            int hourEndOfTask = eventStartsAtHour(calendarEvents, currentHour);
                            if (hourEndOfTask < 0) {
                                //TODO means that there is no task that starts at this hour and it is a free hour in the calendar
                                currentFreeHours[1] = currentHour + 1;
                            } else {
                                //TODO means that there is a task starting at this hour and the returned value is the value of the end hour of the task
                                if(currentFreeHours[1] >= 0) {
                                    int[] hoursRange = {currentFreeHours[0], currentFreeHours[1]};
                                    freeHours.add(hoursRange);
                                }

                                currentHour = hourEndOfTask;
                                //TODO means that the start hour should change for the current hour and the range of free hours finished and the task should be pushed
                                currentFreeHours[0] = currentHour;
                                currentFreeHours[1] = -1;
                                continue;
                            }
                        }
                        currentHour++;
                    }
                    while (currentHour < hours[1]);
                    // if currentFreeHours[1] is different than -1 means that there is a range to take into account
                    if(currentFreeHours[1] >= 0) {
                        int[] hoursRange = {currentFreeHours[0], currentFreeHours[1]};
                        freeHours.add(hoursRange);
                    }

                    int firstHour = -1;
                    int lastHour = -1;

                    // loop over the free hours and push the event to those hours
                    for (Iterator<int[]> a = freeHours.iterator(); a.hasNext(); ) {
                        int[] currentArray = a.next();
                        firstHour = currentArray[0];
                        lastHour = -1;
                        for (int e = currentArray[0]; e < currentArray[1]; e++) {
                            taskHoursInMinutes -= 60;
                            lastHour = e + 1;
                            if (taskHoursInMinutes <= 0) {
                                break;
                            }
                        }

                        // create calendar object with the start hour and end hour
                        com.google.api.client.util.DateTime startDate = new com.google.api.client.util.DateTime(currentDate.plusHours(firstHour).getMillis());
                        com.google.api.client.util.DateTime endDate = new com.google.api.client.util.DateTime(currentDate.plusHours(lastHour).getMillis());
                        String taskTitle = currentTask.getTitle();
                        // push the even to the calendar
                        this.googleCalendar.sendEventToCalendar(startDate, endDate, taskTitle);

                        if (taskHoursInMinutes <= 0) {
                            break;
                        }
                    }
                    if (taskHoursInMinutes <= 0) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        //Clear list of task to avoid re-submission
        listOfTasks.clear();
    }

    public int eventStartsAtHour(List<Event> events, int requiredHour)
    {
        for (Iterator<Event> i = events.iterator(); i.hasNext(); ) {
            Event currentEvent = i.next();
            System.out.println(currentEvent.getStart().getDateTime().toString());
            int startHour = DateTime.parse(currentEvent.getStart().getDateTime().toString()).getHourOfDay();
            // add the next line to extend the task planning taking into account the minute entities
            //int minutes = DateTime.parse(currentEvent.getStart().getDateTime().toString()).getMinuteOfHour();
            if (requiredHour == startHour){
                return DateTime.parse(currentEvent.getEnd().getDateTime().toString()).getHourOfDay();
            }
        }
        return -1;
    }
}