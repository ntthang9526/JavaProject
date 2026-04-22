package Project.src.view.Payment.Center;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Project.src.core.CustomerInfo;
import Project.src.core.InventoryManage;
import Project.src.core.Order;
import Project.src.core.OrderItem;
import Project.src.core.ProductInfo;
import Project.src.core.VoucherInfo;
import Project.src.dao.CustomerInfoDAO;
import Project.src.dao.OrderDAO;
import Project.src.dao.OrderItemDAO;
import Project.src.dao.ProductInfoDAO;
import Project.src.dao.VoucherInfoDAO;
import Project.src.view.Numpad;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class CenterLeftPanel extends JPanel{
    private JTable cartTable;
    public DefaultTableModel tableModel;
    private Order order;
    private JTextField barcodeTextField;
    private JLabel subTotalLabel;
    private JLabel totalDiscountLabel;
    private JLabel totalAmountLabel;

    public CenterLeftPanel(InventoryManage inventory){
        setLayout(new BorderLayout());
        setBackground(new Color(0x26D6E0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(560,400));
        order = new Order();
        String[] columnNames = {"STT","Tên sản phẩm","Số lượng","Đơn giá","Chiết khấu","Thành tiền"}; //Danh sách các cột
        tableModel = new DefaultTableModel(columnNames,0){
            @Override
                public boolean isCellEditable(int row, int column) {
                    // Cho phép chỉnh sửa các cột
                    return false; 
                }
        };

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

        columnModel.getColumn(1).setPreferredWidth(530);
        columnModel.getColumn(1).setMaxWidth(530);
        columnModel.getColumn(1).setMinWidth(530);

        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(2).setMaxWidth(80);
        columnModel.getColumn(2).setMinWidth(80);

        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);
        columnModel.getColumn(3).setMinWidth(80);

        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(4).setMaxWidth(80);
        columnModel.getColumn(4).setMinWidth(80);

        columnModel.getColumn(5).setPreferredWidth(80);

        cartTable.setRowHeight(30); // Chiều cao hàng
        cartTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane, BorderLayout.CENTER);

        JPanel addItemPanel = new JPanel();
        JLabel barcodeLabel = new JLabel("Mã sản phẩm: ");
        
        barcodeTextField = new JTextField();
        barcodeTextField.setPreferredSize(new Dimension(200, 25));

        // NHẬP SẢN PHẨM
        JButton addItemBtn = new JButton("Nhập");
        addItemBtn.setFocusable(false);
        addItemBtn.setBackground(Color.white);
        addItemBtn.setFocusPainted(false);
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String code = barcodeTextField.getText().trim();               
                if (code.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
                    return;
                }
                addItem(code, inventory);
                barcodeTextField.setText("");   
                barcodeTextField.requestFocus();           
            }
        });

        barcodeTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addItemBtn.doClick(0);
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
                barcodeTextField.requestFocus();
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
                    Window parentWindow = SwingUtilities.getWindowAncestor(CenterLeftPanel.this);

                    JDialog updateQuantityDialog = new JDialog((Frame) parentWindow, "Sửa số lượng: " + tenMon, true);
                    updateQuantityDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    updateQuantityDialog.setResizable(false);
                    updateQuantityDialog.setSize(400, 400);
                    updateQuantityDialog.setLayout(new BorderLayout(10, 10));

                    JPanel topPanel = new JPanel();
                    JLabel quantityLabel = new JLabel("Nhập số lượng mới: ");
                    quantityLabel.setFont(new Font("Arial", Font.PLAIN, 18));

                    JTextField quantityField = new JTextField();
                    quantityField.setPreferredSize(new Dimension(100, 30));
                    topPanel.add(quantityLabel);
                    topPanel.add(quantityField);

                    updateQuantityDialog.add(topPanel, BorderLayout.NORTH);

                    JPanel centerPanel = new JPanel();
                    centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
                    Numpad numpad = new Numpad(quantityField);
                    numpad.setPreferredSize(new Dimension(300, 250));
                    centerPanel.add(numpad, BorderLayout.CENTER);
                    updateQuantityDialog.add(centerPanel, BorderLayout.CENTER);

                    JPanel bottomPanel = new JPanel();
                    JButton btnCancel = new JButton("Hủy");
                    btnCancel.setBackground(Color.RED);
                    btnCancel.setForeground(Color.WHITE);
                    btnCancel.setFocusPainted(false);
                    btnCancel.setPreferredSize(new Dimension(100, 40));
                    
                    JButton btnConfirm = new JButton("Xác nhận");
                    btnConfirm.setBackground(new Color(0, 184, 148));
                    btnConfirm.setForeground(Color.WHITE);
                    btnConfirm.setFocusPainted(false);
                    btnConfirm.setPreferredSize(new Dimension(100, 40));
                    bottomPanel.add(btnCancel);
                    bottomPanel.add(btnConfirm);
                    updateQuantityDialog.add(bottomPanel, BorderLayout.SOUTH);

                    btnCancel.addActionListener(ev -> {
                        updateQuantityDialog.dispose();
                    });

                    btnConfirm.addActionListener(ev -> {
                        String input = quantityField.getText().trim();
                        if (!input.isEmpty()){
                            try{
                                ProductInfo product = order.getItems().get(selectedRow).getProduct();
                                int newQuantity = Integer.parseInt(input);
                                if (newQuantity > product.getQuantity()){
                                    JOptionPane.showMessageDialog(null, "Số lượng hàng trong kho không đủ!");
                                    quantityField.setText("");
                                    return;
                                }
                                changeOrderItemQuantity(selectedRow, newQuantity);
                                updateQuantityDialog.dispose();
                            }
                            catch(NumberFormatException err){
                                JOptionPane.showMessageDialog(updateQuantityDialog, "Vui lòng nhập số hợp lệ!");
                            }
                        }
                    });
                    updateQuantityDialog.setLocationRelativeTo(parentWindow);
                    updateQuantityDialog.setVisible(true);
                }

            }
        });

        addItemPanel.add(barcodeLabel);
        addItemPanel.add(barcodeTextField);
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
        tableModel.setRowCount(0);
        for (OrderItem item : order.getItems()) {
            ProductInfo p = item.getProduct();
            Object[] rowData = {tableModel.getRowCount()+1, p.getName(), item.getQuantity(), p.getSalePrice(), p.getDiscount(), item.getAmount()};
            tableModel.addRow(rowData);
        }
        if (subTotalLabel != null){
            subTotalLabel.setText(order.getSubTotal() + "đ");
        }
        
        if (totalDiscountLabel != null){
            totalDiscountLabel.setText(order.getTotalDiscount() + "đ");
        }
        if (totalAmountLabel != null){
            totalAmountLabel.setText(order.getTotalAmount() + "đ");
        }
        if (barcodeTextField != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    barcodeTextField.requestFocusInWindow();
                }
            });
        }
    }
    public void clear(){
        if(order != null){
            order.clear();
            reprintUI();
        }
    }
    public void saveOrder(){
        if (order != null && order.getItems().size() > 0){
            LocalDateTime today = LocalDateTime.now();

            OrderDAO orderDAO = new OrderDAO();
            int count = orderDAO.countOrderInDay(today.toLocalDate());
            String id = "HD" + today.getYear() + today.getMonthValue() + today.getDayOfMonth() + (count + 1);
            order.setOrderID(id);
            order.setDate(today);
            if (orderDAO.saveOrder(order)){
                OrderItemDAO orderItemDAO = new OrderItemDAO();
                ProductInfoDAO productInfoDAO = new ProductInfoDAO();
                CustomerInfoDAO customerInfoDAO = new CustomerInfoDAO();
                VoucherInfoDAO voucherInfoDAO = new VoucherInfoDAO();
               
                orderItemDAO.saveOrderItems(order);
                productInfoDAO.updateAllProductQuantity(order);         
                voucherInfoDAO.updateVoucher(order);
                customerInfoDAO.updateCustomer(order);
                System.out.println("Lưu hóa đơn thành công");
            }
            clear();
        }
    }
    public DefaultTableModel getTableModel(){
        return this.tableModel;
    }
    public Order getOrder(){
        return this.order;
    }
    public JTextField getBarcodeTextField(){
        return this.barcodeTextField;
    }
    public void addVoucher(VoucherInfo v){
        order.addVoucherDiscount(v);
    }
    public void removeVoucher(){
        order.removeVoucherDiscount();
    }
    public void addCustomer(CustomerInfo c){
        order.addCustomer(c);
    }
    public void removeCustomer(){
        order.removeCustomer();
    }
}