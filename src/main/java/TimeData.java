import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.ParseException;

public class TimeData {
    protected final float HOURS_PER_DATE = 24;
    protected float minSleepingTimePerDay;
    protected float workHoursPerDay;
    protected float breakTimePerDay;
    protected float hoursLeftPerDay;
    protected float bufferTime;

    // convert deadline input into date&time of type DateTime
    public static DateTime convertToDate(String deadlineStr) throws ParseException {
        try {
            DateTimeFormatter formatterDate = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
            return formatterDate.parseDateTime(deadlineStr);
        }
        catch (Exception e){
            System.out.println("Wrong date type! Try again...");
            return null;
        }
    }

    // calculate buffer time for a task (event)
    public void BufferTimeCalculator() {
        //first calculate how much time the user has left in a day
        this.hoursLeftPerDay = HOURS_PER_DATE - (this.minSleepingTimePerDay + this.workHoursPerDay + this.breakTimePerDay);

        //then calculate buffer time for the day
        bufferTime = this.hoursLeftPerDay * (this.workHoursPerDay / HOURS_PER_DATE);
    }

    public void ConvertToTime(int x) {
        int minutes = (x * 60) % 60;
        int seconds = (x * (60 * 60)) % 60;
        System.out.println((String.format("%s(h) %s(m) %s(s)", x, minutes, seconds)));
    }

    // takes input as minutes and converts it into days, hours and minutes
    public String convertToHoursAndDays(int x) {
        //since both are ints, you get an int
        int days = x / 1440;
        // subtracting days in minute equivalent co continue converting the resting minutes into hours and minutes
        x -= days * 1440;
        int hours = x / 60;
        int minutes = x % 60;
        String printResult = "";
        if (days >= 1) printResult = printResult + days + " days ";
        if (hours >= 1) printResult = printResult + hours + " hours ";
        if (minutes >= 1) printResult = printResult + minutes + " minutes";
        return printResult;
    }
}