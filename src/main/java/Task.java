import com.google.api.client.util.DateTime;

import java.util.Date;

public class Task {
    private String title;
    private float duration;
    private int priority;
    private Date deadline;

    public Task (String title, float duration, int priority, Date deadline){
        this.title = title;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getTitle(){
        return this.title;
    }
    public float getDuration(){
        return this.duration;
    }
    public int getPriority(){
        return this.priority;
    }
    public Date getDeadline(){
        return this.deadline;
    }

    public String toString(){
        String result = "";
        result = "\nTask title: " + this.title + "\nTask duration: " + this.duration + "\nTask deadline: " + this.deadline + "\nTask priority: " + this.priority;
        return result;
    }
}
