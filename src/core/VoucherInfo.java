package Project.src.core;

public class VoucherInfo {
    private String voucherCode;
    private String voucherNote;
    private int voucherDiscount;

    public VoucherInfo(String code, String note, int discount){
        this.voucherCode = code;
        this.voucherNote = note;
        this.voucherDiscount = discount;
    }
    public String getVoucherCode(){
        return this.voucherCode;
    }
    public String getVoucherNote(){
        return this.voucherNote;
    }
    public int getVoucherDiscount(){
        return this.voucherDiscount;
    }
    
}
