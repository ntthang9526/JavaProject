package Project.src.view.Payment.Center;

import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

import Project.src.core.InventoryManage;
import Project.src.core.ProductInfo;
import Project.src.view.Buttons;
import Project.src.view.Numpad;

public class OtherItemPanel extends JPanel{
    private Numpad numpad;
    public OtherItemPanel(InventoryManage inventory, CenterLeftPanel cart){
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(540,400));
        JPanel otherItemsBar = new JPanel();
        otherItemsBar.setPreferredSize(new Dimension(360, 50));
        Buttons foodBtn = new Buttons("Đồ ăn", 100, 40, Color.cyan);
        foodBtn.setFocusable(false);
        Buttons drinkBtn = new Buttons("Đồ uống", 100, 40, Color.cyan);
        drinkBtn.setFocusable(false);
        Buttons numpadBtn = new Buttons("Bàn phím", 100, 40, Color.cyan);
        numpadBtn.setFocusable(false);
        otherItemsBar.add(foodBtn);
        otherItemsBar.add(drinkBtn);
        otherItemsBar.add(numpadBtn);
        add(otherItemsBar, BorderLayout.NORTH);

        CardLayout otherItemsCardLayout = new CardLayout(); // DANH MỤC ĐỒ ĂN VÀ ĐỒ UỐNG
        JPanel otherItems = new JPanel(otherItemsCardLayout);
            List<ProductInfo> fList = inventory.findByCategory("DO-AN-NHANH");
            JPanel foodPanel = new ItemPanel(fList, cart);
            JPanel fwrapper = new JPanel(new BorderLayout());
            fwrapper.add(foodPanel, BorderLayout.NORTH);
            JScrollPane fscrollPane = new JScrollPane(fwrapper, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
            fscrollPane.setBorder(BorderFactory.createEmptyBorder()); 
            fscrollPane.getVerticalScrollBar().setUnitIncrement(16);
            
            otherItems.add(fscrollPane, "ĐỒ ĂN");

            List<ProductInfo> dList = inventory.findByCategory("DO-UONG-NHANH");
            JPanel drinkPanel = new ItemPanel(dList, cart);
            JPanel dwrapper = new JPanel(new BorderLayout());
            dwrapper.add(drinkPanel, BorderLayout.NORTH);
            JScrollPane dscrollPane = new JScrollPane(dwrapper, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
            dscrollPane.setBorder(BorderFactory.createEmptyBorder()); 
            dscrollPane.getVerticalScrollBar().setUnitIncrement(16);
            otherItems.add(dscrollPane,"ĐỒ UỐNG");
            
            numpad = new Numpad();
            otherItems.add(numpad, "Bàn phím");
        
        add(otherItems, BorderLayout.CENTER);
        
        foodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                otherItemsCardLayout.show(otherItems, "ĐỒ ĂN");
            }
        });
        drinkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                otherItemsCardLayout.show(otherItems, "ĐỒ UỐNG");
            }
        });
        numpadBtn.addActionListener(e -> {
            otherItemsCardLayout.show(otherItems, "Bàn phím");
        });
    }
    public FocusAdapter numpadFocusListener = new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e){
            JTextField activeTextField = (JTextField) e.getSource();
            if (numpad != null) {
                numpad.setTarget(activeTextField);
            }
        }
    };
}
