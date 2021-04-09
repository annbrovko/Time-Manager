import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class CalendarQuickstart {
    /*
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    /**
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
     */

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    /**
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    */


    public static void main(String... args) throws IOException, GeneralSecurityException, ParseException {
        // Build a new authorized API client service.
        /*
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        //initializing the object TimeData
        TimeData timeData = new TimeData();

        //calculating buffer time out of user input and converting the time to hours, minutes and seconds
        timeData.TimeInputReceiver();
        timeData.BufferTimeCalculator();
        System.out.print("Free time left in a day: ");
        timeData.ConvertToTime(timeData.hoursLeftPerDay);
        System.out.print("Your buffer time is: ");
        timeData.ConvertToTime(timeData.bufferTime);

        CalendarList calList = service.calendarList().list().execute();

        List<CalendarListEntry> listCal = calList.getItems();

        for (CalendarListEntry entry : listCal) {
        System.out.println("Calendar: " + entry.getId());
        // List the next 2 weeks events from all calendars.
        DateTime current = new DateTime(System.currentTimeMillis());
        DateTime added = new DateTime(System.currentTimeMillis() + 1209600000);
        Events events = service.events().list(entry.getId())
                .setTimeMin(current)
                .setTimeMax(added)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }


        // time interval, add tasks, push tasks, exit
        int option = 0;
        while (option != 4){
            renderMenu();
        }
*/

        Tasks tasks = new Tasks();
        CalendarEvents calendarEvents = new CalendarEvents();

        Scanner scan = new Scanner(System.in);


        boolean exit = false;

        String userChoice;
        // create main menu for the user
        while (!exit) {
            renderMenu();
            userChoice = scan.nextLine();

            switch (userChoice) {
                case "1":
                    break;
                case "2":
                    tasks.addTask();
                    break;
                case "3":
                    break;
                case "4":
                    tasks.showListOfTasks();
                    break;
                case "5":
                    exit = true;
                    System.out.println("See you!");
                    break;
                default:
                    System.out.println("Wrong choice, try again!");
                    break;
            }
        }
/*


        // push new event to the calendar
        CalendarEvents calendarEvents = new CalendarEvents();
        calendarEvents.createEvent();
 */
    }


    public static void renderMenu(){
        System.out.println("Scheduling Menu");
        System.out.println("1. Set you working time interval");
        System.out.println("2. Add new task");
        System.out.println("3. Send tasks to the calendar");
        System.out.println("4. Show all entered tasks");
        System.out.println("5. Exit");

    }

}