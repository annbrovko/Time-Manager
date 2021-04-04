public class Task {
    private String title;
    private float duration;
    private int priority;

    public Task (String title, float duration, int priority){
        this.title = title;
        this.duration = duration;
        this.priority = priority;
    }

    public String toString(){
        String result = "";
        result = "\nTask title: " + this.title + "\nTask duration: " + this.duration + "\n: Task priority: " + this.priority;
        return result;
    }
}
