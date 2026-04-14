package Project.src.core;

import java.util.HashMap;
import java.util.Map;

public class CustomerManage {
    private Map<String, CustomerInfo> customerList = new HashMap<>();

    public void addCustomer(CustomerInfo c){
        customerList.put(c.getID(), c);
    }
    public CustomerInfo findCustomer(String s){
        return customerList.get(s);
    }
    public void loadData(){
        addCustomer(new CustomerInfo("1","Thang" , 100000, 10000, ""));
        addCustomer(new CustomerInfo("2","Thang2" , 300000, 10000, ""));
        addCustomer(new CustomerInfo("3","Thang3" , 10000000, 10000, ""));
    }
}
