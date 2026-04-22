package Project.src.view.Manage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderPanel extends JPanel {

    public OrderPanel() {
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Mã đơn", "Khách", "Tổng tiền"}, 0
        );

        JTable table = new JTable(model);

        model.addRow(new Object[]{"OD01", "Tuấn", 50000});

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}