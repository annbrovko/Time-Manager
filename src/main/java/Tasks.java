
import org.joda.time.*;

import java.sql.Time;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Date;

public class Tasks {
    //DateTime dateTime = new DateTime();

    // create a variable for priority of type ENUM, so the final user input will have a value to be stores in the linkedList of tasks
    enum priorityEnum{
        LOW(3),
        MEDIUM(2),
        HIGH(1);

        private int value;

        priorityEnum(int value) {
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    Scanner scan = new Scanner(System.in);
    //Duration due = new Duration();

    // create a linked list to store tasks entered by the user
    LinkedList listOfTasks = new LinkedList();


    String title;
    String userExit;
    String priority;
    String deadlineStr;
    String durationStr;
    DateTime deadline;
    DateTime duration;
    final int MAX_MIN = 59;
    final int MAX_HOURS = 23;
    int durationMin;
    int durationHours;
    
    // create method that will create a task with parameters from the user
    public void addTask() throws ParseException {

        boolean exit = false;

        // continue the loop until the user quits the loop
        while (!exit) {

            System.out.println("Create a new task!");

            System.out.println("Set title for your task: ");
            title = scan.nextLine();

            System.out.println("Set duration for your task: ");
            durationStr = scan.nextLine();
            duration = TimeData.convertToTime(durationStr);


            System.out.println("Set deadline for your task (DD-MM-YYYY HH:MM): ");
            deadlineStr = scan.nextLine();
            deadline = TimeData.convertToDate(deadlineStr);

            System.out.println("Latest start point: ");
            DateTime latestStart = new DateTime(deadline.minusHours(duration.getHourOfDay()).minusMinutes(duration.getMinuteOfHour()));
            System.out.println(latestStart);


            System.out.println("Set priority HIGH, MEDIUM or LOW:");
            priority = scan.nextLine();
            int priorityValue = priorityEnum.valueOf(priority.toUpperCase()).getValue();

            System.out.println("Add one more or exit?");
            userExit = scan.nextLine();

            Task task = new Task(title, duration, priorityValue, deadline, latestStart);
            this.listOfTasks.add(task);

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
            return;
        }
        for (int i = 0; i < this.listOfTasks.size(); i++)
            System.out.println(this.listOfTasks.get(i));

    }
}
