package Project.src.core;

import java.util.HashMap;
import java.util.Map;

public class CustomerManage {
    private Map<String, Customer> customerList = new HashMap<>();

    public void addCustomer(Customer c){
        customerList.put(c.getID(), c);
    }
    public Customer findCustomer(String s){
        return customerList.get(s);
    }
    public void loadData(){
        addCustomer(new Customer("1","Thang" , 100000, 10000, ""));
        addCustomer(new Customer("2","Thang2" , 300000, 10000, ""));
        addCustomer(new Customer("3","Thang3" , 10000000, 10000, ""));
    }
}
