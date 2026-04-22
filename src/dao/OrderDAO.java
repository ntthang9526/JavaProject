package Project.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import Project.src.core.Order;

public class OrderDAO {
    public boolean saveOrder(Order order){
        String sql = "insert into hoadon(maHoaDon, tamTinh, tienHangGiamGia, tienVoucherGiamGia, tongChietKhau, tongTien, ngayLap, maKhachHang, maVoucher) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, order.getOrderID());
            ps.setInt(2, order.getSubTotal());
            ps.setInt(3, order.getProductDiscount());
            ps.setInt(4, order.getVoucherDiscount());
            ps.setInt(5, order.getTotalDiscount());
            ps.setInt(6, order.getTotalAmount());
            ps.setObject(7, order.getDate());
            ps.setString(8, order.getCustomerID());
            ps.setString(9, order.getVoucherCode());

            return ps.executeUpdate() > 0;

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public int countOrderInDay(LocalDate date){
        int soLuong = 0;
        String sql = "select count(maHoaDon) from hoadon where DATE(ngayLap) = ?";
        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, date);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                soLuong = rs.getInt(1);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return soLuong;
    }
}
