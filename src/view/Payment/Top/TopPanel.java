package Project.src.view.Payment.Top;
import javax.swing.*;

import Project.src.view.Buttons;

import java.awt.*;
import java.awt.event.*;
public class TopPanel extends JPanel {
    public TopPanel(CardLayout cardLayout, JPanel mainContentPanel){
        setLayout(new BorderLayout());
        setBackground(new Color(0xCFFFFD));
        setPreferredSize(new Dimension(1000,40));
        JPanel leftTopPanel = new JPanel(new GridLayout(1, 2));
            leftTopPanel.setPreferredSize(new Dimension(300,40));
            leftTopPanel.setBackground(new Color(0,255,0));
            add(leftTopPanel,BorderLayout.WEST);
                // THANH TOAN
                JPanel paymentPanel = new JPanel(new BorderLayout());
                paymentPanel.setBackground(Color.BLUE);

                JButton paymentPanelButton = new Buttons("THANH TOÁN");
                paymentPanelButton.setBackground(Color.white);
                paymentPanel.add(paymentPanelButton, BorderLayout.CENTER);
                leftTopPanel.add(paymentPanel);

                // QUAN LY KHO
                JPanel inventoryManagePanel = new JPanel(new BorderLayout());
                inventoryManagePanel.setBackground(Color.green);

                JButton inventoryManageButton = new Buttons("QUẢN LÝ");
                inventoryManageButton.setBackground(Color.WHITE);
                inventoryManagePanel.add(inventoryManageButton,BorderLayout.CENTER);
                leftTopPanel.add(inventoryManagePanel);

                paymentPanelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        cardLayout.show(mainContentPanel, "TRANG THANH TOÁN");
                    }
                }); 
                inventoryManageButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        cardLayout.show(mainContentPanel, "TRANG QUẢN LÝ");
                    }
                });
            
    }
}
