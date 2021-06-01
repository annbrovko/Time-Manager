import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Scanner;

class TimeInterval {
    DateTime start;
    DateTime end;

    public DateTime getStart(){ return this.start; }
    public DateTime getEnd(){
        return this.end;
    }

    Scanner scan = new Scanner(System.in);

    public int[] setTimeInterval() {
        Interval periodInterval = null;
        int[] dayInterval = new int[2];
        do {
            try {
                System.out.println("Set a time interval when you can work during a day: ");
                boolean validStart = false;
                do {
                    try {
                        System.out.println("Set the start hour (HH): ");
                        String startStr = scan.nextLine();
                        this.start = TimeData.convertToHour(startStr);
                        validStart = true;
                    } catch (Exception e){
                    }
                } while (!validStart);

                boolean validEnd = false;
                do {
                    try {
                        System.out.println("Set the end hour (HH): ");
                        String endStr = scan.nextLine();
                        this.end = TimeData.convertToHour(endStr);
                        validEnd = true;
                    }catch (Exception e){
                    }
                } while (!validEnd);

                periodInterval = new Interval(this.start, this.end);
                dayInterval[0] = Integer.parseInt(getStart().toString("HH"));
                dayInterval[1] = Integer.parseInt(getEnd().toString("HH"));
                System.out.println("Your working time will start at " + dayInterval[0] + " and end at " + dayInterval[1]);
            } catch (Exception e){
                System.out.println("Wrong format! Please try again...");
            }
        } while(periodInterval == null);
        return dayInterval;
    }
}