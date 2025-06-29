import java.util.ArrayList;
import java.util.List;

public class Database {
    List<Task> toDoList = new ArrayList<>();

    public Database() {

    }
    public void addTask(Task task) {
        toDoList.add(task);
    }
    public Task getTask(int id) {
        return toDoList.get(id);
    }
    public List<Task> getAll() {
        return toDoList;
    }
    public void editTask(Task task) {
        toDoList.set(toDoList.indexOf(task), task);
    }
    public void removeTask(Task task) {
        toDoList.remove(task);
    }
}
