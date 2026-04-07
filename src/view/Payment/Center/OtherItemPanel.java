package Project.src.view.Payment.Center;

import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import Project.src.core.InventoryManage;
import Project.src.core.ProductInfo;
import Project.src.view.Buttons;

public class OtherItemPanel extends JPanel{
    public OtherItemPanel(InventoryManage inventory, CenterLeftPanel cart){
        setLayout(new BorderLayout());
        JPanel otherItemsBar = new JPanel();
        otherItemsBar.setPreferredSize(new Dimension(360, 50));
        Buttons foodBtn = new Buttons("Đồ ăn", 100, 40, Color.cyan);
        Buttons drinkBtn = new Buttons("Đồ uống", 100, 40, Color.cyan);
        otherItemsBar.add(foodBtn);
        otherItemsBar.add(drinkBtn);
        add(otherItemsBar, BorderLayout.NORTH);

        CardLayout otherItemsCardLayout = new CardLayout(); // DANH MỤC ĐỒ ĂN VÀ ĐỒ UỐNG
        JPanel otherItems = new JPanel(otherItemsCardLayout);
            ArrayList<ProductInfo> fList = new ArrayList<>();
            for (int i = 1;i <= 10; i++){
                fList.add(inventory.findByID(i));
            }
            JPanel foodPanel = new ItemPanel(fList, cart);
            foodPanel.setBackground(Color.WHITE);
            otherItems.add(foodPanel,"ĐỒ ĂN");
            JPanel drinkPanel = new JPanel(new GridLayout(3,4));
            drinkPanel.setBackground(Color.BLUE);
            otherItems.add(drinkPanel,"ĐỒ UỐNG");                
        add(otherItems, BorderLayout.CENTER);
        setMaximumSize(new Dimension(360,270));
        
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
    }
}
