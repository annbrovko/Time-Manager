import java.io.IOException;
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


    public static void main(String... args) throws Exception {

        Tasks tasks = new Tasks();
        TimeInterval timeInterval = new TimeInterval();
        int[] hours = new int[2];

        Scanner scan = new Scanner(System.in);

        boolean exit = false;
        String userChoice;
        // create main menu for the user
        while (!exit) {
            renderMenu();
            userChoice = scan.nextLine();

            switch (userChoice) {
                case "1":
                    hours = timeInterval.setTimeInterval();
                    break;
                case "2":
                    tasks.addTask();
                    break;
                case "3":
                    //call a function to send the task to the calendar
                    tasks.handleTasksToCalendar(hours);
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
    }

    public static void renderMenu(){
        System.out.println("\nScheduling Menu\n");
        System.out.println("1. Set you working time interval");
        System.out.println("2. Add new task");
        System.out.println("3. Send tasks to the calendar");
        System.out.println("4. Show all entered tasks");
        System.out.println("5. Exit");
    }
}