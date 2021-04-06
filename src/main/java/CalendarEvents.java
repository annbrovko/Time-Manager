import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import jdk.tools.jlink.internal.DirArchive;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

public class CalendarEvents {
    protected final long MILLISECONDS_IN_MINUTE = 60000;
    protected final long MILLISECONDS_IN_HOUR = 3600000;
    protected final long MILLISECONDS_IN_10_SEC = 10000;
    Scanner scanner = new Scanner(System.in);

    /*
    public void createEvent(){
        System.out.println("Enter an event title: ");
        String eventTitle = this.scanner.nextLine();
        System.out.println("Enter the duration hours: ");
        long eventDurationHours = this.scanner.nextLong();
        System.out.println("Enter the duration minutes: ");
        long eventDurationMinutes = this.scanner.nextLong();
        long durationHoursInMillis = eventDurationHours * MILLISECONDS_IN_HOUR;
        long durationMinutesInMillis = eventDurationMinutes * MILLISECONDS_IN_MINUTE;
        String taskDeadline = this.scanner.nextLine();
        List<Float> activityDurationList = new ArrayList<Float>();
*/

        // formatting the string into a date
        /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        try {
            Date taskDate = formatter.parse(taskDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
         */
    /*
        // TODO here will be the calculation to place the event into the calendar

        DateTime current = new DateTime(System.currentTimeMillis() + MILLISECONDS_IN_10_SEC);
        DateTime finishDateTime = new DateTime(System.currentTimeMillis() + (durationHoursInMillis + durationMinutesInMillis + MILLISECONDS_IN_10_SEC));
        GoogleCalendar googleCalendar = new GoogleCalendar();

        Event newEvent = googleCalendar.initiateGoogleEvent(eventTitle);
        newEvent = setStartDate(newEvent, current);
        newEvent = setEndDate(newEvent, finishDateTime);

        try {
            googleCalendar.service.events().insert("cs.semesterproject@gmail.com", newEvent).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DateTime sendTaskToCalendar(Task task){
        GoogleCalendar googleCalendar = new GoogleCalendar();
        Calendar cal = Calendar.getInstance();
        cal.setTime(task.getDeadline());
        DateTime current = new DateTime(System.currentTimeMillis() + MILLISECONDS_IN_10_SEC);
        DateTime startTime = new DateTime(cal.add(Calendar.HOUR, -2));


        Event newEvent = googleCalendar.initiateGoogleEvent(task.getTitle());
        newEvent = setStartDate(newEvent, current);
        newEvent = setEndDate(newEvent, task.getDeadline());

        try {
            googleCalendar.service.events().insert("cs.semesterproject@gmail.com", newEvent).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */
/*
    public void listOfActivities()
    {
        List<Float> activityDurationList = new ArrayList<Float>();
        activityDurationList.add();
    }
    // calendar type
*/
    private Event setStartDate(Event event, DateTime startDateTime)
    {
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("CET");

        event.setStart(start);

        return event;
    }

    private Event setEndDate(Event event, DateTime endDateTime)
    {
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("CET");

        event.setEnd(end);

        return event;
    }

}
