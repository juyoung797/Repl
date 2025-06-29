import java.text.Normalizer;


public class Controller {
    Service service = new Service();
    Thymeleaf thymeleaf = Thymeleaf.getInstance(); // 기존과 동일하게 사용

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
        Thymeleaf.Model.box.put("data", service.getData());
        thymeleaf.showView(Thymeleaf.ViewName.valueOf("FORM"), Thymeleaf.Model.box);

    }


}
