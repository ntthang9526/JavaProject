package Project.src.core;

import java.util.ArrayList;
import java.util.List;
public class Order {
    private String OrderID;
    private List<OrderItem> items = new ArrayList<>();
    private int subTotal;
    private int totalDiscount;
    private int totalAmount;
    private String customerID;

    public void add(ProductInfo i){
        for (OrderItem it : items) {
            if (it.getProduct().getID() == i.getID()){
                it.setQuantity(it.getQuantity() + 1);
                caculateTotalAmount();
                return;
            }
        }
        items.add(new OrderItem(i, 1));
        caculateTotalAmount();
    }
    public void remove(int index){
        if (index >= 0 && index < items.size()){
            items.remove(index);
            caculateTotalAmount();
        }
    }
    public void updateQuantity(int index, int newQuantity){
        if (index >= 0 && index < items.size()){
            if (newQuantity == 0){
                remove(index);
            }
            else if (newQuantity < 0){
                items.get(index).setQuantity(1);
                caculateTotalAmount();
            }
            else{
                items.get(index).setQuantity(newQuantity);
                caculateTotalAmount();
            }
        }
    }
    public void caculateTotalAmount(){
        caculateSubTotal();
        caculateTotalDiscount();
    
        totalAmount = subTotal - totalDiscount;
    }
    public void caculateTotalDiscount(){
        totalDiscount = 0;
        for (OrderItem item : items) {
            totalDiscount += (item.getDiscount() * item.getQuantity());
        }
    }
    public void caculateSubTotal(){
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
        items.clear();
        caculateTotalAmount();
    }
}
