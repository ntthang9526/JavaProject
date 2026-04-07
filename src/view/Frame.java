package Project.src.view;
import javax.swing.*;

import Project.src.core.CustomerManage;
import Project.src.core.InventoryManage;
import Project.src.view.Payment.Bottom.BottomPanel;
import Project.src.view.Payment.Center.CenterLeftPanel;
import Project.src.view.Payment.Center.CenterRightPanel;
import Project.src.view.Payment.Top.TopPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Frame extends JFrame{
    public Frame(){
        this.setTitle("MENU THANH TOAN - QUAN LI");        
        this.setSize(1200,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CustomerManage customerManage = new CustomerManage();
        customerManage.loadData();
        InventoryManage inventory = new InventoryManage();
        inventory.loadData();
        // KHI CLICK VÀO CÁC NÚT SẼ ĐỔI SANG CHỨC NĂNG KHÁC
        CardLayout cardLayout = new CardLayout();
        JPanel mainContentPanel = new JPanel(cardLayout);
        TopPanel topPanel = new TopPanel(cardLayout, mainContentPanel);
        this.add(topPanel, BorderLayout.NORTH); // THÊM MENU BAR
        this.add(mainContentPanel);

            // TRANG THANH TOÁN
            JPanel paymentPanel = new JPanel(new BorderLayout());
            mainContentPanel.add(paymentPanel,"TRANG THANH TOÁN");
            
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.setPreferredSize(new Dimension(1000,580));
            paymentPanel.add(centerPanel, BorderLayout.CENTER);
            
            // HIỂN THỊ DANH SÁCH SẢN PHẨM MUA (CENTER LEFT)
            CenterLeftPanel cartPanel = new CenterLeftPanel(inventory);
            centerPanel.add(cartPanel);
            
            // PHẦN ORDER THÊM VÀ THÔNG TIN KH VÀ TỔNG TIỀN (CENTER RIGHT)
            CenterRightPanel centerRightPanel = new CenterRightPanel(inventory, cartPanel, customerManage);
            centerPanel.add(centerRightPanel, BorderLayout.EAST);
            // PANEL DƯỚI
            BottomPanel bottomPanel = new BottomPanel(cartPanel);
            paymentPanel.add(bottomPanel, BorderLayout.SOUTH);
            // HẾT TRANG THANH TOÁN

            // TRANG QUẢN LÝ
            JPanel managePanel = new JPanel();
            mainContentPanel.add(managePanel, "TRANG QUẢN LÝ");
                
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Frame();     
    }
    
}