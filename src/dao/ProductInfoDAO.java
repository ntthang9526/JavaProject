package Project.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import Project.src.core.Order;
import Project.src.core.OrderItem;
import Project.src.core.ProductInfo;

public class ProductInfoDAO {

    public Map<Integer, ProductInfo> getAllProductsByID(){
        Map<Integer, ProductInfo> list = new HashMap<>();
        String sql = "select * from sanpham";

        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int pID = rs.getInt("maSanPham");
                String pSKU = rs.getString("SKU");
                String pBarcode = rs.getString("barcode");
                String pName = rs.getString("tenSanPham");
                int pImportPrice = rs.getInt("giaNhap");
                int pSalePrice = rs.getInt("giaBan");
                int pDiscount = rs.getInt("giamGia");
                int pQuantity = rs.getInt("soLuong");
                String pCategory = rs.getString("loaiSanPham");
                boolean pStatus = rs.getBoolean("trangThai");

                ProductInfo product = new ProductInfo(pID, pSKU, pBarcode, pName, pImportPrice, pSalePrice, pDiscount, pQuantity, pCategory, pStatus);
                list.put(pID, product);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, ProductInfo> getAllProductsByBarcode(){
        Map<String, ProductInfo> list = new HashMap<>();
        String sql = "select * from sanpham";

        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int pID = rs.getInt("maSanPham");
                String pSKU = rs.getString("SKU");
                String pBarcode = rs.getString("barcode");
                String pName = rs.getString("tenSanPham");
                int pImportPrice = rs.getInt("giaNhap");
                int pSalePrice = rs.getInt("giaBan");
                int pDiscount = rs.getInt("giamGia");
                int pQuantity = rs.getInt("soLuong");
                String pCategory = rs.getString("loaiSanPham");
                boolean pStatus = rs.getBoolean("trangThai");

                ProductInfo product = new ProductInfo(pID, pSKU, pBarcode, pName, pImportPrice, pSalePrice, pDiscount, pQuantity, pCategory, pStatus);
                list.put(pBarcode, product);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, ProductInfo> getAllProductsBySKU(){
        Map<String, ProductInfo> list = new HashMap<>();
        String sql = "select * from sanpham";

        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int pID = rs.getInt("maSanPham");
                String pSKU = rs.getString("SKU");
                String pBarcode = rs.getString("barcode");
                String pName = rs.getString("tenSanPham");
                int pImportPrice = rs.getInt("giaNhap");
                int pSalePrice = rs.getInt("giaBan");
                int pDiscount = rs.getInt("giamGia");
                int pQuantity = rs.getInt("soLuong");
                String pCategory = rs.getString("loaiSanPham");
                boolean pStatus = rs.getBoolean("trangThai");

                ProductInfo product = new ProductInfo(pID, pSKU, pBarcode, pName, pImportPrice, pSalePrice, pDiscount, pQuantity, pCategory, pStatus);
                list.put(pSKU, product);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public Map<String, List<ProductInfo>> getAllProductsByCategory(){
        Map<String, List<ProductInfo>> list = new HashMap<>();
        String sql = "select * from sanpham";
        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int pID = rs.getInt("maSanPham");
                String pSKU = rs.getString("SKU");
                String pBarcode = rs.getString("barcode");
                String pName = rs.getString("tenSanPham");
                int pImportPrice = rs.getInt("giaNhap");
                int pSalePrice = rs.getInt("giaBan");
                int pDiscount = rs.getInt("giamGia");
                int pQuantity = rs.getInt("soLuong");
                String pCategory = rs.getString("loaiSanPham");
                boolean pStatus = rs.getBoolean("trangThai");

                ProductInfo product = new ProductInfo(pID, pSKU, pBarcode, pName, pImportPrice, pSalePrice, pDiscount, pQuantity, pCategory, pStatus);
                if (!list.containsKey(pCategory)){
                    list.put(pCategory, new ArrayList<>());
                }
                list.get(pCategory).add(product);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;

    }
    public void updateProductQuantity(ProductInfo product, int n){
        String sql = "update sanpham set soLuong = soLuong + ?, trangThai = (case when (soLuong + ?) > 0 then 1 else 0 end) where maSanPham = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, -n);
            ps.setInt(2, -n);
            ps.setInt(3, product.getID());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateAllProductQuantity(Order order){
        for (OrderItem item : order.getItems()) {
            updateProductQuantity(item.getProduct(), item.getQuantity());
        }
    }
}
