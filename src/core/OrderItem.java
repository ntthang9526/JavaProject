package Project.src.core;

public class OrderItem {
    private ProductInfo product;
    private int quantity;

    public OrderItem(ProductInfo p, int quantity){
        this.product = p;
        this.quantity = quantity;
    }
    public int getAmount(){
        return (product.getSalePrice()-product.getDiscount()) * this.quantity;
    }
    public int getDiscount(){
        return product.getDiscount();
    }
    public int getSalePrice(){
        return product.getSalePrice();
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public ProductInfo getProduct(){
        return this.product;
    }
}
