import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;

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

    public void getTimeInterval() {
        Interval periodInterval = null;
        TimeData converter = new TimeData();
        do {
            try {
                System.out.println("Set the interval on the timespan where you can work in a day: ");

                System.out.println("Set the start interval (HH:MM): ");
                String startStr = scan.nextLine();
                this.start = TimeData.convertToTime(startStr);

                System.out.println("Set the end interval (HH:MM): ");
                String endStr = scan.nextLine();
                this.end = TimeData.convertToTime(endStr);

                periodInterval = new Interval(this.start, this.end);
                String periodStr = periodInterval.toString();

                //System.out.println("Interval = " + converter.convertToTime(periodStr));
                System.out.println("Start    = " + getStart().toString("HH:mm"));
                System.out.println("End      = " + getEnd().toString("HH:mm"));

            } catch (Exception e){
                System.out.println("Wrong format! Please try again...");
            }
        } while(periodInterval == null);
    }
}
