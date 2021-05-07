import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Scanner;

class TimeInterval {
    DateTime start;
    DateTime end;

    public TimeInterval(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    public DateTime getStart(){
        return this.start;
    }
    public DateTime getEnd(){
        return this.end;
    }

   

    public static void getTimeInterval() throws Exception{
        Scanner scan = new Scanner(System.in);
        DateTime start = new DateTime();
        DateTime end = new DateTime();
        TimeInterval interval = new TimeInterval(start, end);

        Period periodInterval = null;

        do {
            try {
                System.out.println("Set the interval on the timespan where you can work in a day: ");

                System.out.println("Set the start interval (HH:MM): ");
                String startStr = scan.nextLine();
                start = TimeData.convertToTime(startStr);

                System.out.println("Set the end interval (HH:MM): ");
                String endStr = scan.nextLine();
                end = TimeData.convertToTime(endStr);

                periodInterval = new Period(start, end);

                System.out.println("Interval = " + periodInterval);
                System.out.println("Start    = " + interval.getStart());
                System.out.println("End      = " + interval.getEnd());


            } catch (Exception e){
                System.out.println("Wrong format! Please try again...");
            }
        } while(periodInterval == null);

    }
}
