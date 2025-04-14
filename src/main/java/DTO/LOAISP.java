package DTO;

public class LOAISP {
    private int categoryID;
    private int groupID;
    private String name;
    private String brand;
    private String status;

    public LOAISP() {
    }

    public LOAISP(int categoryID, int groupID, String name, String brand, String status) {
        this.categoryID = categoryID;
        this.groupID = groupID;
        this.name = name;
        this.brand = brand;
        this.status = status;
    }

    public int getCategoryID() {
        return categoryID;
    }
    
    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
