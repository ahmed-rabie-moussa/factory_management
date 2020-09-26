
package factorysystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RawMaterial {
    private SimpleIntegerProperty rowmaterialID;
    private SimpleStringProperty rowmaterialName;
    private SimpleIntegerProperty supplierID;
    private SimpleDoubleProperty unitPrice;
    private SimpleDoubleProperty rate;
    private SimpleDoubleProperty quantity;
    private SimpleStringProperty storageMethod;

    public RawMaterial(int ID , String name , int sID, double unitPrice, double rate , double quantity , String storageMethod) {
        this.rowmaterialID = new SimpleIntegerProperty(ID);
        this.rowmaterialName= new SimpleStringProperty(name);
        this.supplierID = new SimpleIntegerProperty(sID);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.rate = new SimpleDoubleProperty(rate);
        this.quantity = new SimpleDoubleProperty(quantity);
        this.storageMethod = new SimpleStringProperty(storageMethod);
    }

    public int getRowmaterialID() {
        return rowmaterialID.get();
    }

    public void setRowmaterialID(int rowmaterialID) {
        this.rowmaterialID.set(rowmaterialID);
    }

    public String getRowmaterialName() {
        return rowmaterialName.get();
    }

    public void setRowmaterialName(String rowmaterialName) {
        this.rowmaterialName.set(rowmaterialName);
    }

    public int getSupplierID() {
        return supplierID.get();
    }

    public void setSupplierID(int supplierID) {
        this.supplierID.set(supplierID);
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public double getRate() {
        return rate.get();
    }

    public void setRate(double rate) {
        this.rate.set(rate);
    }

    public double getQuantity() {
        return quantity.get();
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    public String getStorageMethod() {
        return storageMethod.get();
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod.set(storageMethod);
    }
}
