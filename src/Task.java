import java.util.Date;

public class Task {
    private boolean isDone = false;
    private String description;
    private Date dueDate;

    // getters
    public boolean getIsDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }
    public Date getDueDate() {
        return dueDate;
    }

    // setters
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
