import java.text.Normalizer;

public class Controller {
    Service service = new Service();

    public Controller() {

    }
    public String[] getHeader() {
        return service.header;
    }
    // public Form getData() {
    //     Thymeleaf.Model.box.put("paramName", service.getData());
    //     Form view = new Form();
    //     return view;
    // }
    public String[][] getDataArray() {
        return service.getData();
    }

    public void handleAddTask(String desc) {
        service.addTask(desc);
    }

    public void refreshTable() {
        Thymeleaf.Model.box.put("daata", service.getData());
        Thymeleaf.showView("Form", Thymeleaf.Model.box);

    }


}
