package Project.src.view.Payment.Bottom;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import Project.src.view.Numpad;
import Project.src.view.Payment.Center.CenterLeftPanel;
import Project.src.view.Payment.Center.CenterRightPanel;
public class PaymentDialog extends JDialog {
    public PaymentDialog( Frame parent,CenterLeftPanel cart, CenterRightPanel centerRightPanel){
        super(parent, "Xác nhận thanh toán", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        int totalAmount = cart.getOrder().getTotalAmount();
        JLabel titleLabel = new JLabel("TỔNG TIỀN CẦN THANH TOÁN: " + totalAmount + "đ", SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(600, 50));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        JPanel paymentMethods = new JPanel(new BorderLayout());
        paymentMethods.setPreferredSize(new Dimension(500, 360));
        
        JPanel paymentBar = new JPanel();
        paymentMethods.add(paymentBar, BorderLayout.NORTH);
        paymentBar.setPreferredSize(new Dimension(500, 50));
        JButton cashButton = Button("Tiền mặt");
        paymentBar.add(cashButton);
        JButton transferButton = Button("Chuyển khoản");
        paymentBar.add(transferButton);

        CardLayout cardLayout = new CardLayout();
        JPanel paymentInfo = new JPanel(cardLayout);
        
        
        JPanel cashPanel = new JPanel();
        paymentInfo.add(cashPanel,"Tiền mặt");

            JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            topPanel.setPreferredSize(new Dimension(360, 60));
            JLabel tienKhachDuaLbl = new JLabel("Tiền khách đưa: ");
            tienKhachDuaLbl.setFont(new Font("Arial", Font.PLAIN, 18));
            JTextField tienKhachDuaField = new JTextField();
            
            JLabel tienTraLaiLbl = new JLabel("Tiền trả lại: ");
            tienTraLaiLbl.setFont(new Font("Arial", Font.PLAIN, 18));
            JLabel tienTraLai = new JLabel();
            tienTraLai.setFont(new Font("Arial", Font.PLAIN, 18));
            topPanel.add(tienKhachDuaLbl);
            topPanel.add(tienKhachDuaField);
            topPanel.add(tienTraLaiLbl);
            topPanel.add(tienTraLai);
            cashPanel.add(topPanel, BorderLayout.NORTH);

            Numpad numpad = new Numpad(tienKhachDuaField);
            numpad.setPreferredSize(new Dimension(360, 200));
            cashPanel.add(numpad, BorderLayout.CENTER);
            
            JButton confirmButton = new JButton("Xác nhận");
            confirmButton.setBackground(new Color(0, 184, 148));
            confirmButton.setForeground(Color.WHITE);
            confirmButton.setFocusPainted(false);
            confirmButton.setPreferredSize(new Dimension(100, 40));

            confirmButton.addActionListener(e -> {
                String input = tienKhachDuaField.getText();
                if (!input.isEmpty()){
                    int t = Integer.parseInt(input);
                    if (t < totalAmount){
                        JOptionPane.showMessageDialog(null,"Chưa đủ số tiền cần thanh toán!!","Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        tienTraLai.setText("");
                        return;
                    }
                    else{
                        tienTraLai.setText((t-totalAmount) + "đ");
                    }
                }
            });
            cashPanel.add(confirmButton, BorderLayout.SOUTH);


        JPanel transferPanel = new JPanel(new BorderLayout());
            JLabel qrLabel = new JLabel();
            qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
            try {
                java.net.URL imgURL = getClass().getResource("/Project/src/assets/qr_payment.png");
                if (imgURL != null) {
                    ImageIcon qrImageIcon = new ImageIcon(imgURL);
                    Image qrImage = qrImageIcon.getImage();
                    Image scaleImage = qrImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    qrLabel.setIcon(new ImageIcon(scaleImage));
                } else {                   
                    qrLabel.setText("Lỗi: Không tìm thấy ảnh QR ngân hàng!");
                    qrLabel.setForeground(Color.RED);
                }
            } catch (Exception e) {
                qrLabel.setText("Lỗi xử lý ảnh!");
                qrLabel.setForeground(Color.RED);
                e.printStackTrace();
            }
            JLabel transferNote = new JLabel("Quét mã QR để thanh toán " + totalAmount + "đ", SwingConstants.CENTER);
            transferNote.setBorder(new EmptyBorder(10,0,20,0));

            transferPanel.add(qrLabel, BorderLayout.CENTER);
            transferPanel.add(transferNote, BorderLayout.SOUTH);
        paymentInfo.add(transferPanel, "Chuyển khoản");

        paymentMethods.add(paymentInfo);
        add(paymentMethods, BorderLayout.CENTER);

        cashButton.addActionListener(e -> {
            cardLayout.show(paymentInfo, "Tiền mặt");
        });

        transferButton.addActionListener(e -> {
            cardLayout.show(paymentInfo, "Chuyển khoản");
        });

        JPanel actionBar = new JPanel();
        actionBar.setPreferredSize(new Dimension(600, 50));
        add(actionBar, BorderLayout.SOUTH);

        JButton cancelButton = Button("HỦY");
        cancelButton.setBackground(Color.RED);
        actionBar.add(cancelButton);
        cancelButton.addActionListener(e -> {
            dispose();
        });

        JButton completeButton = Button("HOÀN TẤT");
        completeButton.setBackground(Color.GREEN);
        actionBar.add(completeButton);
        completeButton.addActionListener(e -> {
            // Lưu hóa đơn vào DB
            cart.saveOrder();
            centerRightPanel.removeVoucher();
            dispose();
        });
    }

    private JButton Button(String s){
        JButton btn = new JButton(s);

        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(140, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn; 
    }
}
