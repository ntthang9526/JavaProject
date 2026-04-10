package Project.src.core;

import java.util.HashMap;
import java.util.Map;

public class InventoryManage {
    private Map<Integer,ProductInfo> productsByID = new HashMap<>();
    private Map<String,ProductInfo> productsBySKU = new HashMap<>();
    private Map<String,ProductInfo> productsByBarcode = new HashMap<>();

    public void addProduct(ProductInfo p){
        productsByID.put(p.getID(), p);
        productsBySKU.put(p.getSKU(), p);
        productsByBarcode.put(p.getBarcode(),p);
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
    public void loadData(){
        addProduct(new ProductInfo(1, "DU-TS01", null, "Trà Sữa Truyền Thống", 15000, 30000,5000, 100));
        addProduct(new ProductInfo(2, "DU-CF01", null, "Cà Phê Sữa Đá", 12000, 25000,6000, 50));
        addProduct(new ProductInfo(3, "DU-EP01", null, "Nước Ép Dưa Hấu", 18000, 35000,0, 40));
        addProduct(new ProductInfo(4, "DA-BM01", null, "Bánh Mì Thịt Nướng", 10000, 20000,0, 60));
        addProduct(new ProductInfo(5, "DA-XX01", null, "Xúc Xích Đức Nướng", 8000, 15000,0, 100));
        addProduct(new ProductInfo(6, "DU-TS02", null, "Trà Sữa Truyền Thống L", 15000, 30000,0, 100));
        addProduct(new ProductInfo(7, "DU-CF02", null, "Cà Phê Sữa Đá L", 12000, 25000,0 , 50));
        addProduct(new ProductInfo(8, "DU-EP02", null, "Nước Ép Dưa Hấu L", 18000, 35000,0, 40));
        addProduct(new ProductInfo(9, "DA-BM02", null, "Bánh Mì Thịt Nướng F", 10000, 20000,0, 60));
        addProduct(new ProductInfo(10, "DA-XX02", null, "Xúc Xích Đức Nướng S", 8000, 15000,0, 100));
    // ==========================================
    // NHÓM 2: NƯỚC ĐÓNG CHAI & ĐỒ ĂN VẶT
    // (Có mã vạch - Phục vụ test máy quét Barcode bên Trái)
    // ==========================================
    addProduct(new ProductInfo(11, "BEV-COCA", "1", "Coca Cola Lon 320ml", 7000, 10000,0, 200));
    addProduct(new ProductInfo(12, "BEV-AQUA", "2", "Nước Suối Aquafina 500ml", 3500, 5000,0, 300));
    addProduct(new ProductInfo(13, "BEV-TH", "3", "Sữa TH True Milk Ít Đường", 6000, 8000,0, 150));
    addProduct(new ProductInfo(14, "SN-COSY", "4", "Bánh Quy Cosy Marie", 30000, 42000, 0, 120));
    addProduct(new ProductInfo(15, "SN-LAYS", "5", "Snack Khoai Tây Lay's", 9000, 12000,0, 150));

    // ==========================================
    // NHÓM 3: HÀNG SIÊU THỊ MINI (Tạp hóa)
    // (Mã vạch dài - Test độ co giãn của cột Tên Sản Phẩm trên JTable)
    // ==========================================
    addProduct(new ProductInfo(16, "FOOD-HH", "8936221044177", "Mì Hảo Hảo Tôm Chua Cay", 3000, 4000,0, 500));
    addProduct(new ProductInfo(17, "FOOD-OM", "7", "Mì Omachi Xốt Vang", 6000, 8500,0, 200));
    addProduct(new ProductInfo(18, "FOOD-GAO", "8", "Gạo Đặc Sản ST25 Túi 5kg", 150000, 185000,0, 30));
    addProduct(new ProductInfo(19, "FOOD-NM", "9", "Nước Mắm Chinsu Cá Hồi", 35000, 46000,0, 80));
    addProduct(new ProductInfo(20, "FOOD-DA", "10", "Dầu Ăn Tường An 1 Lít", 45000, 55000,0, 60));
    }
}
