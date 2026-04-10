package Project.src.view.Payment.Center;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Project.src.core.CustomerManage;
import Project.src.core.InventoryManage;

import java.awt.*;

public class CenterRightPanel extends JPanel{
    public CenterRightPanel(InventoryManage inventory, CenterLeftPanel cart , CustomerManage customerManage){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(580,580));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        OtherItemPanel otherItemsPanel = new OtherItemPanel(inventory, cart); // KHU VỰC ORDER THÊM                 
        add(otherItemsPanel);
        add(Box.createVerticalStrut(20));
        
        CustomerInfoPanel customerInfoPanel = new CustomerInfoPanel(customerManage); // KHU VỰC THÔNG TIN KHÁCH HÀNG VÀ VOUCHER
        add(customerInfoPanel);
        add(Box.createVerticalStrut(20));

        TotalAmountPanel totalAmountPanel = new TotalAmountPanel(cart); // KHU VỰC TỔNG TIỀN
        add(totalAmountPanel);

    }
}
