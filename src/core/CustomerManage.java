package Project.src.core;

import java.util.HashMap;
import java.util.Map;

import Project.src.dao.CustomerInfoDAO;

public class CustomerManage {
    private Map<String, CustomerInfo> customerList = new HashMap<>();

    public void addCustomer(CustomerInfo c){
        customerList.put(c.getID(), c);
    }
    public CustomerInfo findCustomer(String s){
        return customerList.get(s);
    }
    public void loadData(){
        CustomerInfoDAO customerInfoDAO = new CustomerInfoDAO();
        customerList = customerInfoDAO.getCustomer();
    }
}
