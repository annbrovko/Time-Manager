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
import com.google.api.services.calendar.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleCalendar {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR); //Changed from CALENDAR_READONLY to CALENDAR for permissions
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    public Calendar service;

    public GoogleCalendar() throws IOException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            this.service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
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

    public Event initiateGoogleEvent(String title)
    {
        Event event = new Event()
                .setSummary(title);

        return event;
    }

    // using long instead of JodaTime as the event should be sent to the Google Calendar which uses Google DateTime
    public List<Event> getEvents(String idCalendar, long start, long end) throws IOException {
        DateTime current = new DateTime(start);
        DateTime added = new DateTime(end);
        try {
            Events events = service.events().list(idCalendar)
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
                    event.get(event);
                    System.out.println(event.getSummary());
                }
            }
            return items;
        } catch (Exception e) {
            throw e;
        }
    }

    public void sendEventToCalendar(DateTime startDate, DateTime endDate, String title) throws IOException {
        GoogleCalendar googleCalendar = new GoogleCalendar();

        Event newEvent = googleCalendar.initiateGoogleEvent(title);
        newEvent = setStartDate(newEvent, startDate);
        newEvent = setEndDate(newEvent, endDate);

        try {
            googleCalendar.service.events().insert("cs.semesterproject@gmail.com", newEvent).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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