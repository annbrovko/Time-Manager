import org.joda.time.DateTime;

public class Task {
    private String title;
    private int priority;
    private DateTime duration;
    private DateTime deadline;
    private DateTime latestStart;
    private String deadlineToStr;
    private String durationToStr;
    private String latestStartToStr;

    // create an object Task with four properties title, duration, priority and deadline
    public Task (String title, DateTime duration, int priority, DateTime deadline, DateTime latestStart){
        this.title = title;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.latestStart = latestStart;
    }

    public String getTitle(){
        return this.title;
    }
    public DateTime getDuration(){
        return this.duration;
    }
    public int getPriority(){
        return this.priority;
    }
    public DateTime getDeadline(){ return this.deadline; }

    // convert list to string
    public String toString(){
        String result = "";
        deadlineToStr = this.deadline.getYear() + "/" + this.deadline.getMonthOfYear() + "/" + this.deadline.getDayOfMonth() + " " + this.deadline.getHourOfDay() + ":" + this.deadline.getMinuteOfHour();
        durationToStr = this.duration.getHourOfDay() + ":" + this.duration.getMinuteOfDay();
        latestStartToStr = this.latestStart.getYear() + "/" + this.latestStart.getMonthOfYear() + "/"+ this.latestStart.getDayOfMonth() + " " + this.latestStart.getHourOfDay() + ":" + this.latestStart.getMinuteOfDay();
        result = "\nTask title: " + this.title + "\nTask priority: " + this.priority + "\nTask duration: " + this.durationToStr + "\nTask deadline: " + this.deadlineToStr + "\nLatest start time: " + this.latestStartToStr;
        return result;
    }
}
