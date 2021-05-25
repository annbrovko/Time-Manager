import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.ParseException;

public class TimeData {

    // convert deadline input into date&time of type DateTime
    public static DateTime convertToDate(String dateStr) throws ParseException {
        try {
            DateTimeFormatter formatterDate = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
            return formatterDate.parseDateTime(dateStr);

        }
        catch (Exception e){
            System.out.println("Wrong date type! Try again...");
            return null;
        }
    }

    public static DateTime convertToTime(String timeStr) throws ParseException {
        try {
                DateTimeFormatter formatterTimeStr = DateTimeFormat.forPattern("HH:mm");
                return formatterTimeStr.parseDateTime(timeStr);
        }
        catch (Exception e){
            System.out.println("Wrong time type! Try again...");
            throw e;
        }
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