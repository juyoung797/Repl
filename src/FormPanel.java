import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class FormPanel extends JPanel {
    Controller controller;
    private DefaultTableModel tableModel;
    private JTable table;

    public FormPanel(Map<String, Object> box) {
        this.controller = new Controller();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JTextField descField = new JTextField(20);
        JButton submitBtn = new JButton("추가");

        submitBtn.addActionListener(e -> {
            String desc = descField.getText();
            if (!desc.isBlank()) {
                controller.handleAddTask(desc);  // 데이터베이스에도 저장
                addRowToTable(desc);            // 테이블에도 반영
                descField.setText("");          // 입력창 비우기
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(descField);
        topPanel.add(submitBtn);
        add(topPanel, BorderLayout.NORTH);

        // 테이블 초기화
        String[] columnNames = controller.getHeader();
        tableModel = new DefaultTableModel(columnNames, 0);  // 빈 데이터
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 초기 데이터 불러오기
        loadInitialData();
    }

    private void loadInitialData() {
        String[][] data = controller.getDataArray();
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }

    private void addRowToTable(String desc) {
        // 간단히 추가용 데이터 구성 (실제로는 DB에서 다시 불러와도 됨)
        String[] newRow = new String[] {
                "false",     // isDone 기본값
                desc,        // 사용자가 입력한 내용
                "edit",
                "delete"
        };
        tableModel.addRow(newRow);
    }
}
