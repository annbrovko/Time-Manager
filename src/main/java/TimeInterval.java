import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

class TimeInterval {
    DateTime start;
    DateTime end;

    public TimeInterval() {
    }

    public DateTime getStart(){
        return this.start;
    }
    public DateTime getEnd(){
        return this.end;
    }

    Scanner scan = new Scanner(System.in);

    public int[] getTimeInterval() {
        Interval periodInterval = null;
        TimeData converter = new TimeData();
        int[] dayInterval = new int[2];
        do {
            try {
                System.out.println("Set the interval on the timespan where you can work in a day: ");
                boolean validStart = false;
                do {
                    try {
                        System.out.println("Set the start interval (HH:MM): ");
                        String startStr = scan.nextLine();
                        System.out.println(startStr);
                        this.start = TimeData.convertToTime(startStr);
                        validStart = true;
                    } catch (Exception e){
                        System.out.println("Wrong start time! Try again...");
                    };
                } while (!validStart);

                boolean validEnd = false;
                do {
                    try {
                        System.out.println("Set the end interval (HH:MM): ");
                        String endStr = scan.nextLine();
                        this.end = TimeData.convertToTime(endStr);
                        validEnd = true;
                    }catch (Exception e){
                        System.out.println("Wrong end time! Try again...");
                    }
                } while (!validEnd);

                periodInterval = new Interval(this.start, this.end);

                dayInterval[0] = Integer.parseInt(getStart().toString("HH"));
                dayInterval[1] = Integer.parseInt(getEnd().toString("HH"));

            } catch (Exception e){
                System.out.println("Wrong format! Please try again...");
            }
        } while(periodInterval == null);
        return dayInterval;
    }
}
