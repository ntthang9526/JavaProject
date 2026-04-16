package Project.src.core;

import java.util.HashMap;
import java.util.Map;

public class VoucherManage {
    private Map<String, VoucherInfo> voucherList = new HashMap<>();

    public void addVoucher(VoucherInfo c){
        voucherList.put(c.getVoucherCode(), c);
    }
    public VoucherInfo findVoucher(String s){
        return voucherList.get(s);
    }
    public void loadData(){
        addVoucher(new VoucherInfo("1","Voucher giảm giá 100000đ" , 10000));
        addVoucher(new VoucherInfo("2","Voucher giảm giá 300000đ" , 300000));
        addVoucher(new VoucherInfo("3","Thang3" , 10000));
    }
}
