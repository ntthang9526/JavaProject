package Project.src.core;

public class CustomerInfo {
    private String ID;
    private String name;
    private int point;
    private int availablePoint;
    private String rank;

    public CustomerInfo(String ID, String name, int point, int availablePoint, String rank){
        this.ID = ID;
        this.name = name;
        this.point = point;
        this.availablePoint = availablePoint;
        this.rank = rank;
    }
    public String getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public int getPoint(){
        return this.point;
    }
    public int getAvailablePoint(){
        return this.availablePoint;
    }
    public String getRank(){
        return this.rank;
    }

    public void updatePoint(int p){
        this.point += p;
    }
    public void updateAvailablePoint(int p){
        this.availablePoint += p;
    }
    public void setRank(){
        if (this.point >= 0 && this.point < 50000){
            this.rank = "Đồng";
        }
        else if(this.point >= 50000 && this.point < 100000){
            this.rank = "Bạc";
        }
        else if(this.point >= 100000 && this.point < 200000){
            this.rank = "Vàng";
        }
        else{
            this.rank = "Kim cương";
        }
    }
}
