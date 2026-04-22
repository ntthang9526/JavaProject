package Project.src.core;

public class VoucherInfo {
    private String voucherCode;
    private String voucherNote;
    private int voucherDiscount;
    private int voucherQuantity;

    public VoucherInfo(String code, String note, int discount, int quantity){
        this.voucherCode = code;
        this.voucherNote = note;
        this.voucherDiscount = discount;
        this.voucherQuantity = quantity;
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
    public int getVoucherQuantity(){
        return this.voucherQuantity;
    }
    public void setVoucherDiscount(int d){
        this.voucherDiscount = d;
    }
    public void setVoucherQuantity(int q){
        this.voucherQuantity = q;
    }
    
}
