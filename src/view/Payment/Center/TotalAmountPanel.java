package Project.src.view.Payment.Center;
import javax.swing.*;
import java.awt.*;
public class TotalAmountPanel extends JPanel {
    public TotalAmountPanel(CenterLeftPanel cart){
        setLayout(new GridLayout(3,2,5,5));
        setMaximumSize(new Dimension(540, 100));
        
        JLabel subTotalLabel = new JLabel("Tổng tiền hàng: ");
        subTotalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(subTotalLabel);
        JLabel subTotal = new JLabel("0đ");
        subTotal.setFont(new Font("Arial", Font.BOLD, 18));
        add(subTotal);

        JLabel totalDiscountLabel = new JLabel("Chiết khấu: ");
        totalDiscountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(totalDiscountLabel);
        JLabel totalDiscount = new JLabel("0đ");
        totalDiscount.setFont(new Font("Arial", Font.BOLD, 18));
        add(totalDiscount);

        JLabel totalAmountLabel = new JLabel("Thành tiền: ");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(totalAmountLabel);
        JLabel totalAmount = new JLabel("0đ");
        totalAmount.setFont(new Font("Arial", Font.BOLD, 24));
        add(totalAmount);

        
        cart.setSubTotalLabel(subTotal);
        cart.setTotalDiscountLabel(totalDiscount);
        cart.setTotalAmountLabel(totalAmount);
        
    }
}
