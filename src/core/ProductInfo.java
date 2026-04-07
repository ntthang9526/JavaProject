package Project.src.core;

public class ProductInfo {
    private int productID;
    private String productSKU;
    private String productBarcode;
    private String productName;
    private int importPrice;
    private int salePrice;
    private int discount;
    private int quantity;

    public ProductInfo(){

    }
    public ProductInfo(int productID, String SKU, String barcode, String Name,int importPrice, int salePrice,int discount, int quantity){
        this.productID = productID;
        SKU = SKU.substring(0).toUpperCase();
        this.productSKU = SKU;
        this.productBarcode = barcode;
        this.productName = Name;
        this.importPrice = importPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.discount = discount;
    }
    public int getID(){
        return this.productID;
    }
    public String getSKU(){
        return this.productSKU;
    }
    public void changeSKU(String newSKU){
        newSKU = newSKU.substring(0).toUpperCase();
        this.productSKU = newSKU;
        return;
    }

    public String getBarcode(){
        if (this.productBarcode != null){
            return this.productBarcode;
        }
        return "";
    }

    public String getName(){
        return this.productName;
    }
    public void changeName(String newName){
        this.productName = newName;
        return;
    }

    public int getSalePrice(){
        return this.salePrice;
    }
    public void changeSalePrice(int newPrice){
        this.salePrice = newPrice;
        return;
    }

    public int getQuantity(){
        return this.quantity;
    }
    public void changeQuantity(int q){
        this.quantity += q;
        return;
    }
    public int getImportPrice(){
        return this.importPrice;
    }
    public int getDiscount(){
        return this.discount;
    }
}
