import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Task {
    private final String title;
    private final int priority;
    private final Duration duration;
    private final DateTime deadline;
    TimeData timeData = new TimeData();

    // create an object Task with five properties title, duration, priority, deadline and latest start
    public Task (String title, Duration duration, int priority, DateTime deadline){
        this.title = title;
        this.duration = duration;
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getTitle(){ return this.title; }
    public Duration getDuration(){ return this.duration; }
    public int getPriority(){ return this.priority; }
    public DateTime getDeadline(){ return this.deadline; }

    // convert list to string
    public String toString(){
        // format deadline for print
        String printDeadline = this.deadline.toString("dd/MM/yyyy HH:mm");
        // format duration for print
        String durationToMinutes = this.duration.toStandardMinutes().toString();
        String clean = durationToMinutes.replaceAll("\\D+","");
        int durationUser = Integer.parseInt(clean);
        // print a string with item data
        return "\nTask title: " + this.title + "\nTask priority: " + this.priority + "\nTask duration: " + timeData.convertToHoursAndDays(durationUser) + "\nTask deadline: " + printDeadline;
    }
}