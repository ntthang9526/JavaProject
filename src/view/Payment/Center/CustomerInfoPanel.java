package Project.src.view.Payment.Center;
import javax.swing.*;

import Project.src.core.CustomerInfo;
import Project.src.core.CustomerManage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CustomerInfoPanel extends JPanel {
    private JTextField cTextField;
    public CustomerInfoPanel(CustomerManage customerManage){
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(260, 120));

        JPanel customerInfo = new JPanel();
        JLabel cLabel = new JLabel("Mã KH: ");
        cTextField = new JTextField();
        cTextField.setPreferredSize(new Dimension(80, 25));
        customerInfo.add(cLabel);
        customerInfo.add(cTextField);
        JButton addCustomerInfoBtn = new JButton("Nhập");
        addCustomerInfoBtn.setFocusable(false);
        addCustomerInfoBtn.setBackground(Color.white);
        addCustomerInfoBtn.setFocusPainted(false);
        addCustomerInfoBtn.setPreferredSize(new Dimension(50, 25));
        addCustomerInfoBtn.setMargin(new Insets(0, 0, 0, 0));
        addCustomerInfoBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JButton resetCustomerInfoBtn = new JButton("Xóa");
        resetCustomerInfoBtn.setFocusable(false);
        resetCustomerInfoBtn.setBackground(Color.white);
        resetCustomerInfoBtn.setFocusPainted(false);
        resetCustomerInfoBtn.setMargin(new Insets(0, 0, 0, 0));
        resetCustomerInfoBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        resetCustomerInfoBtn.setPreferredSize(new Dimension(30, 25));
        customerInfo.add(addCustomerInfoBtn);
        customerInfo.add(resetCustomerInfoBtn);

        add(customerInfo, BorderLayout.NORTH);

        JPanel showInfoPanel = new JPanel(new GridBagLayout());
        showInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2,2,2,2);
    
        final JLabel cID = new JLabel("Mã khách hàng: ");
        JLabel customerID = new JLabel("");
        final JLabel cName = new JLabel("Tên KH: ");
        JLabel customerName = new JLabel("");
        final JLabel cPoint = new JLabel("Điểm: ");
        JLabel customerPoint = new JLabel("");
        final JLabel cRank = new JLabel("Hạng: ");
        JLabel customerRank = new JLabel("");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        showInfoPanel.add(cID,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        showInfoPanel.add(customerID,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        showInfoPanel.add(cName,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.9;
        showInfoPanel.add(customerName,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        showInfoPanel.add(cPoint,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.9;
        showInfoPanel.add(customerPoint,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.1;
        showInfoPanel.add(cRank,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.9;
        showInfoPanel.add(customerRank,gbc);
        add(showInfoPanel, BorderLayout.CENTER);

        addCustomerInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String code = cTextField.getText().trim();
                if (code.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng!");
                    return;
                }
                CustomerInfo customer = customerManage.findCustomer(code);
                if (customer == null){
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
                    cTextField.setText("");
                    return;
                }
                customer.setRank();
                customerID.setText(customer.getID());
                customerName.setText(customer.getName());
                customerPoint.setText(Integer.toString(customer.getAvailablePoint()));
                customerRank.setText(customer.getRank());
                
                cTextField.setText("");

            }
        });
        resetCustomerInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                customerID.setText("");
                customerName.setText("");
                customerPoint.setText("");
                customerRank.setText("");
            }
        });
    }
    public JTextField getCustomerTextField(){
        return this.cTextField;
    }
}
