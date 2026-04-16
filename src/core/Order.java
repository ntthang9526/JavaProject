package Project.src.core;

import java.util.ArrayList;
import java.util.List;
public class Order {
    private String OrderID;
    private List<OrderItem> items = new ArrayList<>();
    private int subTotal;
    private int voucherDiscount;
    private int totalDiscount;
    private int totalAmount;
    private String customerID;

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
        calculateTotalDiscount();
        
        totalDiscount += voucherDiscount;
        totalAmount = subTotal - totalDiscount;
        if (totalDiscount > subTotal){
            totalDiscount = subTotal;
        }
        if (totalAmount < 0){
            totalAmount = 0;
        }
    }
    public void calculateTotalDiscount(){
        totalDiscount = 0;
        for (OrderItem item : items) {
            totalDiscount += (item.getDiscount() * item.getQuantity());
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
    public int getTotalDiscount(){
        return this.totalDiscount;
    }
    public List<OrderItem> getItems(){
        return items;
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
        }
    }
    public void removeVoucherDiscount(){
        this.voucherDiscount = 0;
        totalDiscount -= voucherDiscount;
        calculateTotalAmount();
    }
}
