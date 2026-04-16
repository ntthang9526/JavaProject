package Project.src.view.Payment.Center;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Project.src.core.CustomerManage;
import Project.src.core.InventoryManage;
import Project.src.core.VoucherManage;

import java.awt.*;

public class CenterRightPanel extends JPanel{
    private VoucherInfoPanel voucherInfoPanel;
    public CenterRightPanel(InventoryManage inventory, CenterLeftPanel cart , CustomerManage customerManage, VoucherManage voucherManage){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(580,580));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        OtherItemPanel otherItemsPanel = new OtherItemPanel(inventory, cart); // KHU VỰC ORDER THÊM                 
        add(otherItemsPanel);
        add(Box.createVerticalStrut(20));

        cart.getBarcodeTextField().addFocusListener(otherItemsPanel.numpadFocusListener);

        JPanel centerPanel = new JPanel(new GridLayout(1,2));
        centerPanel.setMaximumSize(new Dimension(540, 120));
        CustomerInfoPanel customerInfoPanel = new CustomerInfoPanel(customerManage); // KHU VỰC THÔNG TIN KHÁCH HÀNG VÀ VOUCHER
        voucherInfoPanel = new VoucherInfoPanel(voucherManage, cart);
        centerPanel.add(customerInfoPanel);
        centerPanel.add(voucherInfoPanel);
        add(centerPanel);
        add(Box.createVerticalStrut(20));
        
        voucherInfoPanel.getVoucherField().addFocusListener(otherItemsPanel.numpadFocusListener);
        customerInfoPanel.getCustomerTextField().addFocusListener(otherItemsPanel.numpadFocusListener);

        TotalAmountPanel totalAmountPanel = new TotalAmountPanel(cart); // KHU VỰC TỔNG TIỀN
        add(totalAmountPanel);

    }
    public void removeVoucher(){
        voucherInfoPanel.changeIsAddedVoucher();
    }
}
