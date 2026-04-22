package Project.src.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import Project.src.dao.ProductInfoDAO;

public class InventoryManage {
    private Map<Integer,ProductInfo> productsByID = new HashMap<>();
    private Map<String,ProductInfo> productsBySKU = new HashMap<>();
    private Map<String,ProductInfo> productsByBarcode = new HashMap<>();
    private Map<String,List<ProductInfo>> productsByCategory = new HashMap<>();

    public void addProduct(ProductInfo p){
        productsByID.put(p.getID(), p);
        productsBySKU.put(p.getSKU(), p);
        productsByBarcode.put(p.getBarcode(),p);
        
        if (!productsByCategory.containsKey(p.getCategory())){
            productsByCategory.put(p.getCategory(), new ArrayList<>());
        }
        productsByCategory.get(p.getCategory()).add(p);
    }
    public ProductInfo findByID(int ID){
        return productsByID.get(ID);
    }
    public ProductInfo findBySKU(String SKU){
        SKU = SKU.substring(0).toUpperCase().trim();
        return productsBySKU.get(SKU);
    }
    public ProductInfo findByBarcode(String barcode){
        return productsByBarcode.get(barcode.trim());
    }
    public List<ProductInfo> findByCategory(String category){
        if (productsByCategory.get(category) != null){
            return productsByCategory.get(category);
        }
        return null;
    }
    public void loadData(){
        ProductInfoDAO productInfoDAO = new ProductInfoDAO();

        productsByID = productInfoDAO.getAllProductsByID();
        productsByBarcode = productInfoDAO.getAllProductsByBarcode();
        productsBySKU = productInfoDAO.getAllProductsBySKU();
        productsByCategory = productInfoDAO.getAllProductsByCategory();
    }  
}
