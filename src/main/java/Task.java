import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Task {
    private final String title;
    private final int priority;
    private final Duration duration;
    private final DateTime deadline;
    private final DateTime latestStart;
    private String deadlineToStr;
    private String durationToStr;
    private String latestStartToStr;

    // create an object Task with four properties title, duration, priority and deadline
    public Task (String title, Duration duration, int priority, DateTime deadline, DateTime latestStart){
        this.title = title;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.latestStart = latestStart;
    }
/*
    public String getTitle(){
        return this.title;
    }
    public Duration getDuration(){
        return this.duration;
    }
    public int getPriority(){
        return this.priority;
    }
    public DateTime getDeadline(){ return this.deadline; }
*/
    // convert list to string
    public String toString(){
        String result;
        deadlineToStr = this.deadline.toString("dd/MM/yyyy HH:mm");
        durationToStr = this.duration.toStandardHours().toString();
        latestStartToStr = this.latestStart.toString("dd/MM/yyyy HH:mm");
        result = "\nTask title: " + this.title + "\nTask priority: " + this.priority + "\nTask duration: " + this.durationToStr + "\nTask deadline: " + this.deadlineToStr + "\nLatest start time: " + this.latestStartToStr;
        return result;
    }
}