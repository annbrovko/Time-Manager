import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
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

    public static Date convertToDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date deadline = formatter.parse(dateStr);
        return deadline;
    }

/*
    public static void convertToCalDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date deadline = formatter.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(deadline);
    }

 */

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
