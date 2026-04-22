package Project.src.view.Manage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public CustomerPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0xCFFFFD));

        // ===== TABLE =====
        model = new DefaultTableModel(
            new String[]{"ID", "Tên", "SĐT", "Điểm", "Hạng"}, 0
        );

        table = new JTable(model);

        // dữ liệu mẫu
        model.addRow(new Object[]{"KH01", "Tuấn", "0123456789", 120, getRank(120)});

        // ===== TOP PANEL =====
        JPanel top = new JPanel();
        top.setBackground(new Color(0xCFFFFD));

        JTextField txtID = new JTextField(5);
        JTextField txtName = new JTextField(10);
        JTextField txtPhone = new JTextField(10);
        JTextField txtPoint = new JTextField(5);

        JButton addBtn = new JButton("Thêm");

        top.add(new JLabel("ID"));
        top.add(txtID);

        top.add(new JLabel("Tên"));
        top.add(txtName);

        top.add(new JLabel("SĐT"));
        top.add(txtPhone);

        top.add(new JLabel("Điểm"));
        top.add(txtPoint);

        top.add(addBtn);

        // ===== ADD EVENT =====
        addBtn.addActionListener(e -> {
            try {
                String id = txtID.getText();
                String name = txtName.getText();
                String phone = txtPhone.getText();
                int point = Integer.parseInt(txtPoint.getText());

                String rank = getRank(point);

                model.addRow(new Object[]{id, name, phone, point, rank});

                // clear input
                txtID.setText("");
                txtName.setText("");
                txtPhone.setText("");
                txtPoint.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Nhập sai dữ liệu!");
            }
        });

        // ===== TABLE STYLE (bonus nhẹ) =====
        table.setRowHeight(25);

        // ===== ADD =====
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // ===== TÍNH HẠNG =====
    private String getRank(int point) {
        if (point >= 500) return "Kim cương";
        if (point >= 300) return "Vàng";
        if (point >= 100) return "Bạc";
        return "Thành viên";
    }
    
}