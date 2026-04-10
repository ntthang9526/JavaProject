package Project.src.view.Payment.Bottom;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import Project.src.view.Payment.Center.CenterLeftPanel;
public class PaymentDialog extends JDialog {
    public PaymentDialog( Frame parent,CenterLeftPanel cart){
        super(parent, "Xác nhận thanh toán", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setResizable(false);

        int totalAmount = cart.getOrder().getTotalAmount();
        JLabel titleLabel = new JLabel("TỔNG TIỀN CẦN THANH TOÁN: " + totalAmount + "đ", SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(600, 50));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel paymentMethods = new JPanel(new BorderLayout());
        paymentMethods.setPreferredSize(new Dimension(600, 360));
        paymentMethods.setBackground(Color.RED);

        JPanel paymentBar = new JPanel();
        paymentMethods.add(paymentBar, BorderLayout.NORTH);
        paymentBar.setPreferredSize(new Dimension(600, 50));
        JButton cashButton = Button("Tiền mặt");
        paymentBar.add(cashButton);
        JButton transferButton = Button("Chuyển khoản");
        paymentBar.add(transferButton);
        JButton cardButton = Button("Thẻ");
        paymentBar.add(cardButton);

        CardLayout cardLayout = new CardLayout();
        JPanel paymentInfo = new JPanel(cardLayout);

        add(paymentMethods, BorderLayout.CENTER);

        JPanel actionBar = new JPanel();
        actionBar.setPreferredSize(new Dimension(600, 50));
        add(actionBar, BorderLayout.SOUTH);

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
