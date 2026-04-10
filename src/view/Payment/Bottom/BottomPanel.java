package Project.src.view.Payment.Bottom;

import javax.swing.*;
import java.util.Date;
import Project.src.view.Payment.Center.CenterLeftPanel;
import java.awt.event.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class BottomPanel extends JPanel {
    public BottomPanel(CenterLeftPanel cart){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000,50));

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 12));
        infoPanel.setOpaque(false);

        JLabel cashierLabel = new JLabel("Thu ngân: Admin");
        cashierLabel.setForeground(Color.BLACK);
        cashierLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel timeLabel = new JLabel();
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        timeLabel.setText("|   Ngày " + timeFormat.format(new Date()));
        Timer clockTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeNow = timeFormat.format(new Date());
                timeLabel.setText("|   Ngày " + timeNow);
            }
        });
        
        clockTimer.start();

        infoPanel.add(cashierLabel);
        infoPanel.add(timeLabel);
        add(infoPanel, BorderLayout.WEST);

        JPanel actionPanel = new JPanel(new GridLayout(1,2,100,5));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        actionPanel.setPreferredSize(new Dimension(400, 50));
        actionPanel.setOpaque(false);
        JButton cancelButton = Buttons("HỦY", Color.RED);
        JButton payButton = Buttons("THANH TOÁN", Color.GREEN);
        actionPanel.add(cancelButton);
        actionPanel.add(payButton);

        add(actionPanel, BorderLayout.EAST);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy hóa đơn", "Hủy hóa đơn", JOptionPane.YES_NO_OPTION );
                if (choice == JOptionPane.YES_OPTION){
                    cart.clear();
                }
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (cart.tableModel.getRowCount() == 0){
                    JOptionPane.showMessageDialog(null, "Giỏ hàng đang trống!","Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Window parentWindow = SwingUtilities.getWindowAncestor(BottomPanel.this); // Tìm frame cha
                PaymentDialog paymentDialog = new PaymentDialog((Frame) parentWindow, cart);
                paymentDialog.setVisible(true);
            }
        });
    }
    private JButton Buttons(String s, Color c){
        JButton btn = new JButton(s);
        btn.setFocusable(false);
        btn.setBorderPainted(false);
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(50, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn; 
    }

}
