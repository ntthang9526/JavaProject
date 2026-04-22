package Project.src.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Order {
    private String OrderID;
    private List<OrderItem> items = new ArrayList<>();
    private int subTotal;
    private int productDiscount;
    private int voucherDiscount;
    private int totalDiscount;
    private int totalAmount;
    private LocalDateTime date;
    private String customerID;
    private String voucherCode;

    public Order(){}
    public Order(String id, int subTotal, int productDiscount, int voucherDiscount, int totalDiscount, int totalAmount, LocalDateTime date, String customerID, String voucherCode){
        this.OrderID = id;
        this.subTotal = subTotal;
        this.productDiscount = productDiscount;
        this.voucherDiscount = voucherDiscount;
        this.totalDiscount = totalDiscount;
        this.date = date;
        this.customerID = customerID;
        this.voucherCode = voucherCode;
    }
    public void add(ProductInfo i){
        for (OrderItem it : items) {
            if (it.getProduct().getID() == i.getID()){
                it.setQuantity(it.getQuantity() + 1);
                calculateTotalAmount();
                return;
            }
        }
        items.add(new OrderItem(i, 1));
        calculateTotalAmount();
    }
    public void remove(int index){
        if (index >= 0 && index < items.size()){
            items.remove(index);
            calculateTotalAmount();
        }
    }
    public void updateQuantity(int index, int newQuantity){
        if (index >= 0 && index < items.size()){
            if (newQuantity == 0){
                remove(index);
            }
            else if (newQuantity < 0){
                items.get(index).setQuantity(1);
                calculateTotalAmount();
            }
            else{
                items.get(index).setQuantity(newQuantity);
                calculateTotalAmount();
            }
        }
    }
    public void calculateTotalAmount(){
        calculateSubTotal();
        calculateProductDiscount();
        
        totalDiscount = productDiscount + voucherDiscount;
        totalAmount = subTotal - totalDiscount;
        if (totalDiscount > subTotal){
            totalDiscount = subTotal;
        }
        if (totalAmount < 0){
            totalAmount = 0;
        }
    }
    public void calculateProductDiscount(){
        productDiscount = 0;
        for (OrderItem item : items) {
            productDiscount += (item.getDiscount() * item.getQuantity());
        }
    }
    public void calculateSubTotal(){
        subTotal = 0;
        for (OrderItem item : items) {
            subTotal += (item.getSalePrice() * item.getQuantity());
        }
    }

    public int getTotalAmount(){
        return this.totalAmount;
    }
    public int getSubTotal(){
        return this.subTotal;
    }
    public int getProductDiscount(){
        return this.productDiscount;
    }
    public int getVoucherDiscount(){
        return this.voucherDiscount;
    }
    public int getTotalDiscount(){
        return this.totalDiscount;
    }
    public List<OrderItem> getItems(){
        return items;
    }
    public void setOrderID(String s){
        this.OrderID = s;
    }
    public String getOrderID(){
        return this.OrderID;
    }
    public void clear(){
        this.voucherDiscount = 0;
        items.clear();
        calculateTotalAmount();
    }
    public void addVoucherDiscount(VoucherInfo v){
        if (v != null){
            this.voucherDiscount = v.getVoucherDiscount();
            totalDiscount += voucherDiscount;
            calculateTotalAmount();
            this.voucherCode = v.getVoucherCode();
        }
    }
    public void removeVoucherDiscount(){
        this.voucherDiscount = 0;
        totalDiscount -= voucherDiscount;
        calculateTotalAmount();
    }
    public String getVoucherCode(){
        return this.voucherCode;
    }
    public void setDate(LocalDateTime currDate){
        this.date = currDate;
    }
    public LocalDateTime getDate(){
        return this.date;
    }
    public void setCustomerID(CustomerInfo c){
        this.customerID = c.getID();
    }
    public String getCustomerID(){
        return this.customerID;
    }

    public void addCustomer(CustomerInfo c){
        if (c != null){
            setCustomerID(c);
        }
    }
    public void removeCustomer(){
        this.customerID = null;
    }
    public int getProductQuantityInOrder(ProductInfo p){
        int soLuong = 0;
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getID() == p.getID()){
                soLuong += orderItem.getQuantity();
            }
        }
        return soLuong;
    }
}
