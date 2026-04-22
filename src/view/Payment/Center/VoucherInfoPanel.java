package Project.src.view.Payment.Center;

import javax.swing.*;

import Project.src.core.VoucherInfo;
import Project.src.core.VoucherManage;

import java.awt.*;

public class VoucherInfoPanel extends JPanel{
    private boolean isAddedVoucher;
    private JTextField voucherField;
    private JLabel voucherLbl;
    public VoucherInfoPanel(VoucherManage voucherManage, CenterLeftPanel cart){
        isAddedVoucher = false;
        setPreferredSize(new Dimension(260, 120));
        setLayout(new GridLayout(2,1));
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Mã voucher:"));
        voucherField = new JTextField();
        voucherField.setPreferredSize(new Dimension(80, 25));
        JButton addVoucherInfoBtn = new JButton("Nhập");
        addVoucherInfoBtn.setFocusable(false);
        addVoucherInfoBtn.setBackground(Color.white);
        addVoucherInfoBtn.setFocusPainted(false);
        addVoucherInfoBtn.setPreferredSize(new Dimension(50, 25));
        addVoucherInfoBtn.setMargin(new Insets(0, 0, 0, 0));
        addVoucherInfoBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JButton resetVoucherInfoBtn = new JButton("Xóa");
        resetVoucherInfoBtn.setFocusable(false);
        resetVoucherInfoBtn.setBackground(Color.white);
        resetVoucherInfoBtn.setFocusPainted(false);
        resetVoucherInfoBtn.setMargin(new Insets(0, 0, 0, 0));
        resetVoucherInfoBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        resetVoucherInfoBtn.setPreferredSize(new Dimension(30, 25));
        topPanel.add(voucherField);
        topPanel.add(addVoucherInfoBtn);
        topPanel.add(resetVoucherInfoBtn);
        
        add(topPanel);

        JPanel voucherInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        voucherLbl = new JLabel("Thông tin voucher: ");
        voucherLbl.setHorizontalAlignment(SwingConstants.LEFT);
        voucherInfo.add(voucherLbl);
        add(voucherInfo);

        addVoucherInfoBtn.addActionListener(e -> {
            if (isAddedVoucher == true){
                JOptionPane.showMessageDialog(null, "Đã áp dụng voucher trước đó rồi !");
                return;
            }
            if (cart.getOrder().getItems().size() == 0){
                JOptionPane.showMessageDialog(null, "Giỏ hàng đang trống !");
                voucherField.setText("");
                return;
            }
            if (voucherField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã voucher");
                return;
            }
            String input = voucherField.getText();
            if (!input.isEmpty()){
                VoucherInfo voucher = voucherManage.findVoucher(input);
                if (voucher != null && voucher.getVoucherQuantity() > 0){
                    cart.addVoucher(voucher);
                    cart.reprintUI();
                    voucherField.setText("");
                    voucherLbl.setText("Thông tin voucher: " + voucher.getVoucherNote());
                    isAddedVoucher = true;
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Voucher không tồn tại hoặc đã hết lượt sử dụng");
                    voucherField.setText("");
                    return;
                }
            }
        });
        resetVoucherInfoBtn.addActionListener(e -> {
            if (isAddedVoucher == false){
                JOptionPane.showMessageDialog(null, "Chưa áp dụng voucher !");
                return;
            }
            cart.removeVoucher();
            cart.reprintUI();
            voucherField.selectAll();
            voucherLbl.setText("Thông tin voucher: ");
            isAddedVoucher = false;
        });
    }
    public void changeIsAddedVoucher(){
        this.isAddedVoucher = false;
        if (voucherField != null) {
            voucherField.setText("");
        }
        if (voucherLbl != null) {
            voucherLbl.setText("Thông tin voucher: ");
        }
    }
    public JTextField getVoucherField(){
        return this.voucherField;
    }
}
