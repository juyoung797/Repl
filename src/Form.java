import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    Controller controller;
    // GridLayout grid = new GridLayout(1,0);

    public FormPanel() {
        this.controller = new Controller();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JTextField descField = new JTextField(20);

        JButton submitBtn = new JButton("추가");
        submitBtn.addActionListener(e -> {
            controller.handleAddTask(descField.getText());
            controller.refreshTable();
        });

        JPanel topPanel = new JPanel();
        topPanel.add(descField);
        topPanel.add(submitBtn);
        add(topPanel, BorderLayout.NORTH);
    }


}
