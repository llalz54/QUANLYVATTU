package DTO;

public class SANPHAM {
    private int productID;
    private int categoryID;
    private String serial;
    private String status;
    private String startDate;
    private String endDate;

    public SANPHAM() {
    }

    public SANPHAM(int productID, int categoryID, String serial, String status, String startDate, String endDate) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.serial = serial;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    

    public int getProductID() {
        return productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
