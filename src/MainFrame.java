import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel currentView;

    public MainFrame() {
        setTitle("My MVC App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    public void setView(JPanel newView) {
        if (currentView != null) {
            remove(currentView);  // 기존 뷰 제거
        }

        currentView = newView;
        add(currentView, BorderLayout.CENTER);
        revalidate(); // 레이아웃 다시 계산
        repaint();    // 화면 다시 그림
    }
}