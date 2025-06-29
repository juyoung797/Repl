import java.util.List;

public class Service {
    Database database = new Database();

    String[] header = {"check", "description", "edit", "delete"};

    Service() {

    }


    public String[][] getData() {
        List<Task> tasks = database.getAll();
        String[][] data = new String[tasks.size()][header.length];
        int i = 0;
        for (Task task : tasks) {
            data[i] = new String[]{
                    String.valueOf(task.getIsDone()),
                    task.getDescription(),
                    "edit",
                    "delete"
            };
        }
        return data;
    }

    public void addTask(String desc) {
        Task task = new Task(desc);
        database.addTask(task);
    }
}
