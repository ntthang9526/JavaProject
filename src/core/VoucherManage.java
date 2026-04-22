package Project.src.core;

import java.util.HashMap;
import java.util.Map;

import Project.src.dao.VoucherInfoDAO;

public class VoucherManage {
    private Map<String, VoucherInfo> voucherList = new HashMap<>();

    public void addVoucher(VoucherInfo c){
        voucherList.put(c.getVoucherCode(), c);
    }
    public VoucherInfo findVoucher(String s){
        return voucherList.get(s);
    }
    public void loadData(){
        VoucherInfoDAO voucherInfoDAO = new VoucherInfoDAO();
        voucherList = voucherInfoDAO.getAllVoucherInfo();
    }
}
