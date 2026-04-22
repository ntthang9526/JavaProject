package Project.src.view.Payment.Center;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Project.src.core.ProductInfo;
import Project.src.view.Buttons;
public class ItemPanel extends JPanel {
    public ItemPanel(List<ProductInfo> pList, CenterLeftPanel cart){
        setLayout(new GridLayout(0 , 4, 5, 5));
        if (pList == null){
            return;
        }
        for (ProductInfo p : pList) {
            String btnText = "<html><center>" + p.getName() + "<br/><b>" + (p.getSalePrice() - p.getDiscount()) + "đ</b></center></html>";
            Buttons btn = new Buttons(btnText, 0);
            btn.setPreferredSize(new Dimension(50,80));
            btn.setFocusable(false);
            add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e){
                    if (p.getQuantity() <= 0 || p.getQuantity() <= cart.getOrder().getProductQuantityInOrder(p) ){
                        JOptionPane.showMessageDialog(null, "Sản phẩm đã hết hàng!");
                        return;
                    }
                    cart.addItem(p);
                }
            });
        }
    }
}
