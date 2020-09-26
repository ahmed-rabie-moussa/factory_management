
package factorysystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleIntegerProperty productID;
    private SimpleStringProperty productName;
    private SimpleIntegerProperty distributorID;
    private SimpleDoubleProperty unitPrice;
    private SimpleDoubleProperty rate;
    private SimpleDoubleProperty quantity;

    public Product(int ID , String name , int dID, double unitPrice, double rate , double quantity ) {
        this.productID = new SimpleIntegerProperty(ID);
        this.productName= new SimpleStringProperty(name);
        this.distributorID = new SimpleIntegerProperty(dID);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.rate = new SimpleDoubleProperty(rate);
        this.quantity = new SimpleDoubleProperty(quantity);
    }

    public int getProductID() {
        return productID.get();
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getDistributorID() {
        return distributorID.get();
    }

    public void setSupplierID(int distributorID) {
        this.distributorID.set(distributorID);
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

}
