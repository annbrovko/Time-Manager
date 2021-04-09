import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.Scanner;

public class TimeData {
    protected final float HOURS_PER_DATE = 24;
    protected float minSleepingTimePerDay;
    protected float workHoursPerDay;
    protected float breakTimePerDay;
    protected float hoursLeftPerDay;
    protected float bufferTime;

    Scanner userSetup = new Scanner(System.in);

    public void TimeInputReceiver() {
        System.out.print("Set minimum sleeping time: ");
        minSleepingTimePerDay = this.userSetup.nextFloat();

        System.out.print("Set daily work hours: ");
        workHoursPerDay = this.userSetup.nextFloat();

        System.out.print("Set break time: ");
        breakTimePerDay = this.userSetup.nextFloat();
    }

    // convert deadline input into date&time of type Date
    public static DateTime convertToDate(String deadlineStr) throws ParseException {
        DateTimeFormatter formatterDate = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
        DateTime deadline = formatterDate.parseDateTime(deadlineStr);
        return deadline;
    }

    // convert duration input into time of type Date
    public static DateTime convertToTime(String durationStr) throws ParseException {
        DateTimeFormatter formatterTime = DateTimeFormat.forPattern("HH:mm");
        DateTime duration = formatterTime.parseDateTime(durationStr);
        return duration;
    }

    // calculate buffer time for a task (event)
    public void BufferTimeCalculator() {
        //first calculate how much time the user has left in a day
        this.hoursLeftPerDay = HOURS_PER_DATE - (this.minSleepingTimePerDay + this.workHoursPerDay + this.breakTimePerDay);

        //then calculate buffer time for the day
        bufferTime = this.hoursLeftPerDay * (this.workHoursPerDay / HOURS_PER_DATE);
    }

    public void ConvertToTime(float x) {
        int hours = (int) x;
        int minutes = (int) (x * 60) % 60;
        int seconds = (int) (x * (60 * 60)) % 60;
        System.out.println((String.format("%s(h) %s(m) %s(s)", hours, minutes, seconds)));
    }
}