import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            Thymeleaf thymeleaf = Thymeleaf.getInstance(); // 싱글턴 가져오기
            thymeleaf.setMainFrame(frame);                 // 프레임 주입
            thymeleaf.showView(Thymeleaf.ViewName.FORM, Thymeleaf.Model.box);
            frame.setVisible(true);
        });
    }
}