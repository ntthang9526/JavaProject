package Project.src.view.Manage;
import javax.swing.*;
import java.awt.*;

public class ManagerFrame extends JPanel {

    public ManagerFrame() {
        setLayout(new BorderLayout());

        //  PANEL CHỨA NỘI DUNG 
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);

        contentPanel.add(new ProductPanel(), "SANPHAM");
        contentPanel.add(new CustomerPanel(), "KHACHHANG");
        contentPanel.add(new OrderPanel(), "DONHANG");

        //  SIDEBAR 
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200,0));

        JButton btnSP = new JButton("Sản phẩm");
        JButton btnKH = new JButton("Khách hàng");
        JButton btnDH = new JButton("Đơn hàng");

        btnSP.setFont(new Font("Arial", Font.BOLD, 16));
        btnKH.setFont(new Font("Arial", Font.BOLD, 16));
        btnDH.setFont(new Font("Arial", Font.BOLD, 16));

        //  STYLE SIDEBAR 
        sidebar.setBackground(new Color(0xCFFFFD)); 

        btnSP.setBackground(Color.WHITE);
        btnKH.setBackground(Color.WHITE);
        btnDH.setBackground(Color.WHITE);

        btnSP.setForeground(Color.BLACK);
        btnKH.setForeground(Color.BLACK);
        btnDH.setForeground(Color.BLACK);

        btnSP.setHorizontalAlignment(SwingConstants.CENTER);
        btnSP.setVerticalAlignment(SwingConstants.CENTER);
        btnKH.setHorizontalAlignment(SwingConstants.CENTER);
        btnKH.setVerticalAlignment(SwingConstants.CENTER);
        btnDH.setHorizontalAlignment(SwingConstants.CENTER);
        btnDH.setVerticalAlignment(SwingConstants.CENTER);

        btnSP.setMaximumSize(new Dimension(200, 55));
        btnKH.setMaximumSize(new Dimension(200, 55));
        btnDH.setMaximumSize(new Dimension(200, 55));

        Color hoverColor = new Color(200, 255, 255);

        btnSP.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btnSP.setBackground(hoverColor);
            }
         public void mouseExited(java.awt.event.MouseEvent evt) {
            btnSP.setBackground(Color.WHITE);
            }
        });

        btnKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKH.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKH.setBackground(Color.WHITE);
            }
        });

        btnDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDH.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDH.setBackground(Color.WHITE);
            }
        });

        sidebar.add(btnSP);
        sidebar.add(btnKH);
        sidebar.add(btnDH);

        //  SỰ KIỆN 
        btnSP.addActionListener(e -> cardLayout.show(contentPanel, "SANPHAM"));
        btnKH.addActionListener(e -> cardLayout.show(contentPanel, "KHACHHANG"));
        btnDH.addActionListener(e -> cardLayout.show(contentPanel, "DONHANG"));

        //  ADD VÀO FRAME 
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }
}