package Project.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import Project.src.core.CustomerInfo;
import Project.src.core.Order;
public class CustomerInfoDAO {
    public Map<String, CustomerInfo> getCustomer(){
        Map<String, CustomerInfo> list = new HashMap<>();
        String sql = "select * from khachhang";
        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CustomerInfo customer = new CustomerInfo(rs.getString("maKhachHang"), rs.getString("tenKhachHang"),
                    rs.getInt("diemTichLuy"), rs.getInt("diemHienTai"), "");
                customer.setRank();
                list.put(customer.getID(), customer);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public void updateCustomer(Order order){
        if (order.getCustomerID() != null){
            String sql = "update khachhang set " +
             "diemTichLuy = diemTichLuy + ?, diemHienTai = diemHienTai + ?, " +
             "hang = (case " +
             "    when (diemTichLuy + ?) >= 200000 then N'Kim cương' " +
             "    when (diemTichLuy + ?) >= 100000 then N'Vàng' " +
             "    when (diemTichLuy + ?) >= 50000  then N'Bạc' " +
             "    else N'Đồng' " +
             "end) " +
             "where maKhachHang = ?";
        
            try(Connection conn = DatabaseConnection.getConnection()){
                PreparedStatement ps = conn.prepareStatement(sql);
                int point = order.getTotalAmount() / 100;
                ps.setInt(1, point);
                ps.setInt(2, point);
                ps.setInt(3, point);
                ps.setInt(4, point);
                ps.setInt(5, point);
                ps.setString(6, order.getCustomerID());

                ps.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("Cộng thành công");
        }
    }
}
