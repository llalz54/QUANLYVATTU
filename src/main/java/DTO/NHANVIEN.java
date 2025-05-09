package DTO;

public class NHANVIEN {
    private int userId;
    private String hoTen;
    private String status;
    private String userName;
    private String passWord;
    
    public NHANVIEN() {   
    }

    public NHANVIEN(int userId, String hoTen, String status, String userName, String passWord) {
        this.userId = userId;
        this.hoTen = hoTen;
        this.status = status;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getUserId() {
        return userId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
       
}
