package Project.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Project.src.core.Order;
import Project.src.core.VoucherInfo;

public class VoucherInfoDAO {

    public Map<String, VoucherInfo> getAllVoucherInfo(){

        Map<String, VoucherInfo> voucherList = new HashMap<>();
        String sql = "select * from voucher";

        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                VoucherInfo voucher = new VoucherInfo(rs.getString("maVoucher"), rs.getString("thongTin"), rs.getInt("giamGia"), rs.getInt("soLuong"));
                voucherList.put(voucher.getVoucherCode(), voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voucherList;
    }

    public void updateVoucher(Order order){
        String sql = "update voucher set soLuong = soLuong - ? where maVoucher = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, order.getVoucherCode());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
