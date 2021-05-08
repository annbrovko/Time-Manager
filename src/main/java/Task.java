import org.joda.time.DateTime;
import org.joda.time.Duration;

class Task {
    private final String title;
    private final int priority;
    private final Duration duration;
    private final DateTime deadline;
    private final DateTime latestStart;

    // create an object Task with four properties title, duration, priority and deadline
    public Task (String title, Duration duration, int priority, DateTime deadline, DateTime latestStart){
        this.title = title;
        this.duration = duration;
        this.deadline = deadline;
        this.priority = priority;
        this.latestStart = latestStart;
        deadline.compareTo(latestStart);
    }

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

    public DateTime getLatestStart(){
        return this.latestStart;
    }

    /*
    @Override
    public int compareTo(Task otherTask){
        return this.title.compareTo(otherTask.getTitle());
    }


    @Override
    public int compareTo(Task otherTask){
        return this.deadline.compareTo(otherTask.getDeadline());
    }
    */

    TimeData timeData = new TimeData();

    // convert list to string
    public String toString(){
        // format deadline for print
        String printDeadline = this.deadline.toString("dd/MM/yyyy HH:mm");
        // format duration for print
        String durationToMinutes = this.duration.toStandardMinutes().toString();
        String clean = durationToMinutes.replaceAll("\\D+","");
        int durationUser = Integer.parseInt(clean);
        // format latest start for print
        String printLatestStart = this.latestStart.toString("dd/MM/yyyy HH:mm");
        // print a string with item data
        return "\nTask title: " + this.title + "\nTask priority: " + this.priority + "\nTask duration: " + timeData.convertToHoursAndDays(durationUser) + "\nTask deadline: " + printDeadline + "\nLatest start time: " + printLatestStart;
    }
}