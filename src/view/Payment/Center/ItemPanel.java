package Project.src.view.Payment.Center;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import Project.src.core.ProductInfo;
import Project.src.view.Buttons;
public class ItemPanel extends JPanel {
    public ItemPanel(ArrayList<ProductInfo> pList, CenterLeftPanel cart){
        setLayout(new GridLayout(0 , 4, 5, 5));
        for (ProductInfo p : pList) {
            String btnText = "<html><center>" + p.getName() + "<br/><b>" + (p.getSalePrice() - p.getDiscount()) + "đ</b></center></html>";
            Buttons btn = new Buttons(btnText, 0);
            add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e){
                    cart.addItem(p);
                }
            });
        }
    }
}
