package Project.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Project.src.core.Order;
import Project.src.core.OrderItem;

public class OrderItemDAO {
    public void saveOrderItems(Order order){
        String sql = "insert into chitiethoadon(maHoaDon, maSanPham, soLuong, donGia, chietKhau) values(?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection()){
            for (OrderItem item : order.getItems()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, order.getOrderID());
                ps.setInt(2, item.getProduct().getID());
                ps.setInt(3, item.getQuantity());
                ps.setInt(4, item.getSalePrice());
                ps.setInt(5, item.getDiscount());

                ps.executeUpdate();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
