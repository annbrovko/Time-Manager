import org.joda.time.*;
import java.text.ParseException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Tasks {

    // create a variable for priority of type ENUM, so the final user input will have a value to be stores in the linkedList of tasks
    enum priorityEnum{
        LOW(3),
        MEDIUM(2),
        HIGH(1);

        private final int value;

        priorityEnum(int value) {
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    Scanner scan = new Scanner(System.in);

    // create a linked list to store tasks entered by the user
    LinkedList listOfTasks = new LinkedList();

    String title;
    String userExit;
    String priority;
    String deadlineStr;
    String durationStr;
    DateTime deadline;
    Duration duration;
    final int MILLIS_IN_MIN = 60000;
    final int MILLIS_IN_HOUR = 3600000;
    
    // create method that will create a task with parameters from the user
    public void addTask() throws ParseException {

        boolean exit = false;

        // continue the loop until the user quits the loop
        while (!exit) {

            System.out.println("Create a new task!");

            System.out.println("Set title for your task: ");
            title = scan.nextLine();

            // try asking for duration until the format is compatible
            duration = null;
            do {
                System.out.println("Set duration for your task (HH:MM): ");
                durationStr = scan.nextLine();
                String[] exploded = durationStr.split(":");
                try {
                    int hours = Integer.parseInt(exploded[0]);
                    int minutes = Integer.parseInt(exploded[1]);
                    long hoursMillis = (long)hours * MILLIS_IN_HOUR;
                    long minutesMillis = (long)minutes * MILLIS_IN_MIN;
                    duration = new Duration(hoursMillis + minutesMillis);
                } catch (Exception e){
                    System.out.println("Something went wrong! Try again...");
                }
            } while (duration == null);

            // try asking for deadline until the format is compatible
            deadline = null;
            do {
                try {
                    System.out.println("Set deadline for your task (DD/MM/YYYY HH:MM): ");
                    deadlineStr = scan.nextLine();
                    deadline = TimeData.convertToDate(deadlineStr);
                } catch (Exception e){
                    System.out.println("Deadline format is wrong! Try again...");
                }
            } while (deadline == null);

            // calculate the latest start time and date for this task
            DateTime latestStart = new DateTime(deadline.minus(duration));

            // ask for the priority until the format is compatible
            boolean isPriorityEnum = false;
            int priorityValue = 0;
            do {
                try {
                    System.out.println("Set priority HIGH, MEDIUM or LOW:");
                    priority = scan.nextLine();
                    priorityValue = priorityEnum.valueOf(priority.toUpperCase()).getValue();
                    isPriorityEnum = true;
                } catch (Exception e){
                    System.out.println("Something went wrong, try again!");
                }
            } while (!isPriorityEnum);

            // ask user if add a new task - if exit, go to main menu
            System.out.println("Add one more or exit?");
            userExit = scan.nextLine();

            // add the entered task into the linkedList of tasks
            Task task = new Task(title, duration, priorityValue, deadline, latestStart);
            this.listOfTasks.add(task);
            listOfTasks.sort(new Comparator <Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    int comparison = 0;
                    comparison = task1.getDuration().compareTo(task2.getDuration());
                    if (comparison == 0){
                        comparison = task1.getDeadline().compareTo(task2.getDeadline());
                    } else if (comparison == 0){
                        comparison = task1.getLatestStart().compareTo(task2.getLatestStart());
                    }
                    return comparison;
                }
            });
            // quit the method to the main menu
                if (userExit.equals("exit")) {
                    exit = true;
                }
            }
        }

    // show the list of all entered tasks during the current session
    public void showListOfTasks(){
        if (this.listOfTasks.isEmpty()){
            System.out.println("No tasks to show");
        } else {
            // loop through the linkedList of tasks and print each of them
            for (Object i:
                    listOfTasks) {
                System.out.println(i);
            }
        }
    }

}