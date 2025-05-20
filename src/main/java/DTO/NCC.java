
package DTO;

/**
 *
 * @author Admin
 */
public class NCC {
    private int supplier_id;
    private String name;
    private String MST;
    private String diaChi;

    public NCC() {
    }

    
    public NCC(int supplier_id, String name, String MST, String diaChi) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.MST = MST;
        this.diaChi = diaChi;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMST() {
        return MST;
    }

    public void setMST(String MST) {
        this.MST = MST;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return  name;
    }
    
    
}
