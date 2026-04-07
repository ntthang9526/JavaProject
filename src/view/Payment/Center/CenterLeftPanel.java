package Project.src.view.Payment.Center;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Project.src.core.InventoryManage;
import Project.src.core.Order;
import Project.src.core.OrderItem;
import Project.src.core.ProductInfo;

import javax.swing.*;
import javax.swing.event.TableModelEvent;

import java.awt.*;
import java.awt.event.*;

public class CenterLeftPanel extends JPanel{
    private JTable cartTable;
    public DefaultTableModel tableModel;
    private Order order;
    private boolean isUpdating = false;
    private JLabel subTotalLabel;
    private JLabel totalDiscountLabel;
    private JLabel totalAmountLabel;
    public CenterLeftPanel(InventoryManage inventory){
        setLayout(new BorderLayout());
        setBackground(new Color(0x26D6E0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(560,400));

        order = new Order();

        String[] columnNames = {"Mã","Tên sản phẩm","Số lượng","Đơn giá","Chiết khấu","Thành tiền"}; //Danh sách các cột
        tableModel = new DefaultTableModel(columnNames,0){
            @Override
                public boolean isCellEditable(int row, int column) {
                    // Cho phép chỉnh sửa các cột
                    if (column == 2){
                        return true;
                    }
                    return false; 
                }
        }; // Tạo bảng mặc định gồm các cột

        // SỬA SỐ LƯỢNG VÀ GIÁ TIỀN
        tableModel.addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e){
                if (isUpdating == true) return;

                if (e.getType() == TableModelEvent.UPDATE){
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if (column == 2){
                        try{
                            int newQuantity = Integer.parseInt(tableModel.getValueAt(row, 2).toString());
                            changeOrderItemQuantity(row, newQuantity);
                        }
                        catch(NumberFormatException err){
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ");
                            reprintUI();
                        }
                    }
                }

            }
        });

        cartTable = new JTable(tableModel);  //Tạo bảng
        JScrollPane scrollPane = new JScrollPane(cartTable); // Tạo thanh cuộn 
        cartTable.setFocusable(false);
        cartTable.setSelectionBackground(Color.LIGHT_GRAY);
        cartTable.setSelectionForeground(Color.black);

        // Khi click ra ngoài sẽ không chọn hàng đó
        cartTable.setFillsViewportHeight(true);
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                int row = cartTable.rowAtPoint(e.getPoint());
                
                if (row == -1) {
                    cartTable.clearSelection();
                }
            }
        });
        TableColumnModel columnModel = cartTable.getColumnModel(); // Lấy ra model của các cột
        columnModel.getColumn(0).setPreferredWidth(65);
        columnModel.getColumn(0).setMaxWidth(65);
        columnModel.getColumn(0).setMinWidth(65);

        columnModel.getColumn(1).setPreferredWidth(440);
        columnModel.getColumn(1).setMaxWidth(440);
        columnModel.getColumn(1).setMinWidth(440);

        columnModel.getColumn(2).setPreferredWidth(60);
        columnModel.getColumn(2).setMaxWidth(60);
        columnModel.getColumn(2).setMinWidth(60);

        columnModel.getColumn(3).setPreferredWidth(65);
        columnModel.getColumn(3).setMaxWidth(65);
        columnModel.getColumn(3).setMinWidth(65);

        columnModel.getColumn(4).setPreferredWidth(65);
        columnModel.getColumn(4).setMaxWidth(65);
        columnModel.getColumn(4).setMinWidth(65);

        columnModel.getColumn(5).setPreferredWidth(65);

        cartTable.setRowHeight(30); // Chiều cao hàng
        cartTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane, BorderLayout.CENTER);

        JPanel addItemPanel = new JPanel();
        JLabel maLabel = new JLabel("Mã sản phẩm: ");
        JTextField maTextField = new JTextField();
        maTextField.setPreferredSize(new Dimension(200, 25));

        // NHẬP SẢN PHẨM
        JButton addItemBtn = new JButton("Nhập");
        addItemBtn.setFocusable(false);
        addItemBtn.setBackground(Color.white);
        addItemBtn.setFocusPainted(false);
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String code = maTextField.getText().trim();               
                if (code.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
                    return;
                }
                addItem(code, inventory);
                maTextField.setText("");              
            }
        });

        // XÓA SẢN PHẨM
        JButton delteItemBtn = new JButton("Xóa");
        delteItemBtn.setFocusable(false);
        delteItemBtn.setBackground(Color.white);
        delteItemBtn.setFocusPainted(false);
        delteItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                deleteItem();
            }
        });

        JButton updateQuantityBtn = new JButton("Sửa");
        updateQuantityBtn.setFocusable(false);
        updateQuantityBtn.setBackground(Color.WHITE);
        updateQuantityBtn.setFocusPainted(false);
        updateQuantityBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = cartTable.getSelectedRow();
                
                if (selectedRow != -1){
                    String tenMon = tableModel.getValueAt(selectedRow, 1).toString();
                    String input = JOptionPane.showInputDialog(null, 
                    "Nhập số lượng mới cho món: " + tenMon, 
                    "Sửa Số Lượng", 
                    JOptionPane.QUESTION_MESSAGE);

                    if (input != null && input.trim().isEmpty() == false){
                        try{
                            int newQuantity = Integer.parseInt(input.trim());
                            changeOrderItemQuantity(selectedRow, newQuantity);
                        }
                        catch(NumberFormatException err){
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ");
                        }
                    }
                }

            }
        });

        addItemPanel.add(maLabel);
        addItemPanel.add(maTextField);
        addItemPanel.add(addItemBtn);
        addItemPanel.add(delteItemBtn);
        addItemPanel.add(updateQuantityBtn);
        add(addItemPanel, BorderLayout.SOUTH);
    }

    public void addItem(String code, InventoryManage inventory){
        if (code.isEmpty()){
            JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
            return;
        }
        ProductInfo item = inventory.findByBarcode(code);
        if (item != null){
            order.add(item);
            reprintUI();
        }
        else{
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
        }
    }
    public void addItem(ProductInfo p){
        if (p != null){
            order.add(p);

            reprintUI();
        }
    }
    public void deleteItem(){
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow != -1){
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này khỏi giỏ hàng?","Message",JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                order.remove(selectedRow);
                reprintUI();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm để xóa");
        }
    }
    public void changeOrderItemQuantity(int rowIndex, int newQuantity){
        if (newQuantity == 0){
            order.remove(rowIndex);
        }
        else if (newQuantity < 0){
            JOptionPane.showMessageDialog(null, "Số lượng không được là số âm!");
        }
        else {
            order.updateQuantity(rowIndex, newQuantity);
        }
        reprintUI();
    }

    public void setTotalAmountLabel(JLabel label){
        this.totalAmountLabel = label;
    }
    public void setSubTotalLabel(JLabel label){
        this.subTotalLabel = label;
    }
    public void setTotalDiscountLabel(JLabel label){
        this.totalDiscountLabel = label;
    }
    public void reprintUI(){
        isUpdating = true;
        tableModel.setRowCount(0);
        for (OrderItem item : order.getItems()) {
            ProductInfo p = item.getProduct();
            Object[] rowData = {p.getID(), p.getName(), item.getQuantity(), p.getSalePrice(), p.getDiscount(), item.getAmount()};
            tableModel.addRow(rowData);
        }
        isUpdating = false;
        if (subTotalLabel != null){
            subTotalLabel.setText(order.getSubTotal() + "đ");
        }
        
        if (totalDiscountLabel != null){
            totalDiscountLabel.setText(order.getTotalDiscount() + "đ");
        }
        if (totalAmountLabel != null){
            totalAmountLabel.setText(order.getTotalAmount() + "đ");
        }
    }
    public void clear(){
        if(order != null){
            order.clear();
            reprintUI();
        }
    }
}