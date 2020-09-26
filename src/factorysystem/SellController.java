
package factorysystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SellController implements Initializable {
@FXML private ComboBox comboBox;
@FXML private TextField txtQuantity;   
 private  ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
        Connection conn = ConnectDataBase.connect();
        PreparedStatement ps = conn.prepareStatement("select productName from product");
        ResultSet rs = ps.executeQuery();
           while (rs.next()){
           list.add(rs.getString("productName"));
           }
           comboBox.setItems(list);
           comboBox.setValue(list.get(1));
        }
        catch (SQLException ex)
        {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Database error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
        }
    }    
     public void clearActionButton (ActionEvent event){
    txtQuantity.setText("");
    }
     ///////////////////////////////////////
      public void AddActionButton (ActionEvent event){
    if (txtQuantity.getText().equals(""))
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No value entered for quantity");
          alert.setContentText("Enter a quantity");
          alert.showAndWait(); 
          return;
    }
    if (!Validation.validateFractionNumber(txtQuantity.getText()))
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("the value entered for quantity is invalid");
          alert.setContentText("Enter a valid quantity");
          alert.showAndWait(); 
          return;
    }
    double quantity = 0;
    try {
    Connection conn = ConnectDataBase.connect();
    PreparedStatement ps = conn.prepareStatement("select quantity from product where productName = ?");
    ps.setString(1, comboBox.getValue().toString());
    ResultSet rs = ps.executeQuery();
    if (rs.next())
    {
    quantity = rs.getDouble("quantity");
    }
    quantity += Double.parseDouble(txtQuantity.getText());
    ps = conn.prepareStatement("update product set quantity = ? where productName = ?");
    ps.setDouble(1, quantity);
    ps.setString(2, comboBox.getValue().toString());
    ps.execute();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Adding Has been Completed");
    alert.setContentText("You added Quantity = " + txtQuantity.getText() + " of product --> " + comboBox.getValue() +"\n now, the factory has a total quantity = "+ quantity);
    alert.showAndWait(); 
     txtQuantity.setText("");
    }
    catch(SQLException ex)
    {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Database error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
    }
    }
      public void sellActionButton (ActionEvent event){
     if (txtQuantity.getText().equals(""))
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No value entered for quantity");
          alert.setContentText("Enter a quantity");
          alert.showAndWait(); 
          return;
    }
    if (!Validation.validateFractionNumber(txtQuantity.getText()))
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("the value entered for quantity is invalid");
          alert.setContentText("Enter a valid quantity");
          alert.showAndWait(); 
          return;
    }
    double quantity = 0;
    double unitPrice = 0;
     try {
    Connection conn = ConnectDataBase.connect();
    PreparedStatement ps = conn.prepareStatement("select quantity , unitPrice from product where productName = ?");
    ps.setString(1, comboBox.getValue().toString());
    ResultSet rs = ps.executeQuery();
    if (rs.next())
    {
    quantity = rs.getDouble("quantity");
    unitPrice = rs.getDouble("unitPrice");
    }
     if (quantity >= Double.parseDouble(txtQuantity.getText()))
     {
       
      ps = conn.prepareStatement("update product set quantity = ? where productName = ?");
         ps.setDouble(1, (quantity - Double.parseDouble(txtQuantity.getText())));
         ps.setString(2, comboBox.getValue().toString());
         ps.execute();
         ps = conn.prepareStatement("select sells from totalcosts");
         rs = ps.executeQuery();
         double sells = 0;
         if (rs.next()) sells = rs.getDouble("sells");
          ps = conn.prepareStatement("update totalcosts set sells = ?");
          ps.setDouble(1, (sells + Double.parseDouble( txtQuantity.getText())*unitPrice));
          ps.execute();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Complete successfully");
          alert.setContentText("The last quantity  = " + quantity + "\nThe quantity Sold = " + txtQuantity.getText() + "\nThe Current Quantity = " +  (quantity - Double.parseDouble(txtQuantity.getText())));
          alert.showAndWait(); 
          txtQuantity.setText("");
     }
     else {
       Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not Available quantity");
          alert.setContentText("There is at most " + quantity + "in your warehouse");
          alert.showAndWait(); 
     }
     
     }
     catch (SQLException ex)
     {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Database error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
         return;
     }
    }
}
