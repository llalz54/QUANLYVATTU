package DTO;

public class PHIEUXUAT {
    private int idpx;
    private int userID;
    private int categoryID;
    private int quantity;
    private int price;
    private String ngayXuat;
    private String customer;
    private String tenLoai;
    private int tongTien;

    public PHIEUXUAT() {
    }

    public PHIEUXUAT(int idpx, int userID, int categoryID, int quantity, int price, String ngayXuat, String customer) {
        this.idpx = idpx;
        this.userID = userID;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
        this.ngayXuat = ngayXuat;
        this.customer = customer;
    }

    public int getTongTien() {
        return quantity * price;
    }

    
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    

    public int getIdpx() {
        return idpx;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    
}
