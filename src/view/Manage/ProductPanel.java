package Project.src.view.Manage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public ProductPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
            new String[]{"ID", "Tên", "Giá", "Tồn kho"}, 0
        );

        table = new JTable(model);

        // giả dữ liệu
        model.addRow(new Object[]{"SP01", "Coca", 10000, 50});
        model.addRow(new Object[]{"SP02", "Pepsi", 12000, 30});

        JPanel top = new JPanel();

        JTextField txtID = new JTextField(5);
        JTextField txtName = new JTextField(10);
        JTextField txtPrice = new JTextField(7);
        JTextField txtStock = new JTextField(5); // 🔥 thêm dòng này

        JButton addBtn = new JButton("Thêm");
        JButton editBtn = new JButton("Sửa");
        JButton deleteBtn = new JButton("Xóa");

        // add label + input
        top.add(new JLabel("ID"));
        top.add(txtID);

        top.add(new JLabel("Tên"));
        top.add(txtName);

        top.add(new JLabel("Giá"));
        top.add(txtPrice);

        top.add(new JLabel("Tồn"));
        top.add(txtStock);

        top.add(addBtn);
        top.add(editBtn);
        top.add(deleteBtn);     

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addBtn.addActionListener(e -> {
            String id = txtID.getText();
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int stock = Integer.parseInt(txtStock.getText());

            model.addRow(new Object[]{id, name, price, stock});
        });
    }
}