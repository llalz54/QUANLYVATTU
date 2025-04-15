package DTO;

public class PHIEUNHAP {
    private int idpn;
    private int userID;
    private int categoryID;
    private int quantity;
    private int price;
    private String ngayNhap;
    private String supplier;
    private int group_id;
    private int tongTien;
    
    public PHIEUNHAP() {
    }

    public PHIEUNHAP(int idpn, int userID, int categoryID,int group_id, int quantity, int price, String ngayNhap, String supplier) {
        this.idpn = idpn;
        this.userID = userID;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
        this.ngayNhap = ngayNhap;
        this.supplier = supplier;
        this.group_id = group_id;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setIdpn(int idpn) {
        this.idpn = idpn;
    }
    

    public void setTongTien(int tongTien) {
        this.tongTien = quantity*price;
    }

    
    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    
    public int getIdpn() {
        return idpn;
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
    
    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    
}
