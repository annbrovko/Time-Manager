import java.util.LinkedList;
import java.util.Scanner;

public class Tasks {

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

    LinkedList listOfTasks = new LinkedList();

    public void addTask(){

        boolean exit = false;
        String title;
        float duration;
        String userExit;
        String priority;

        while (!exit) {
            System.out.println("Create a new task!");

            System.out.println("Set title for your task: ");
            title = scan.nextLine();

            System.out.println("Set duration for your task: ");
            duration = scan.nextFloat();
            scan.nextLine();

            System.out.println("Set deadline for your task: ");

            System.out.println("Set priority HIGH, MEDIUM or LOW:");
            priority = scan.nextLine();
            int priorityValue = priorityEnum.valueOf(priority.toUpperCase()).getValue();

            System.out.println("Add one more or exit?");
            userExit = scan.nextLine();

            Task task = new Task(title, duration, priorityValue);
            this.listOfTasks.add(task);

            if (userExit.equals("exit")) {
                exit = true;
            }

        }
    }

    public void showListOfTasks(){
        if (this.listOfTasks.isEmpty()){
            System.out.println("No tasks to show");
            return;
        }
        for (int i = 0; i < this.listOfTasks.size(); i++)
            System.out.println(this.listOfTasks.get(i));

    }
}
