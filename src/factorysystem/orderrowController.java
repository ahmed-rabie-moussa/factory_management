
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



public class orderrowController implements Initializable {
@FXML private ComboBox comboBox;
@FXML private TextField txtQuantity;
 private  ObservableList<String> list = FXCollections.observableArrayList();
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        Connection conn = ConnectDataBase.connect();
        PreparedStatement ps = conn.prepareStatement("select rowmaterialName from rowmaterial");
        ResultSet rs = ps.executeQuery();
           while (rs.next()){
           list.add(rs.getString("rowmaterialName"));
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
    /////////////////////////////////////////////////////
    public void submitActionButton (ActionEvent event){
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
    PreparedStatement ps = conn.prepareStatement("select quantity , unitPrice from rowmaterial where rowmaterialName = ?");
    ps.setString(1, comboBox.getValue().toString());
    ResultSet rs = ps.executeQuery();
    if (rs.next())
    {
    quantity = rs.getDouble("quantity");
    unitPrice = rs.getDouble("unitPrice");
    }
    quantity += Double.parseDouble(txtQuantity.getText());
    ps = conn.prepareStatement("update rowmaterial set quantity = ? where rowmaterialName = ?");
    ps.setDouble(1, quantity);
    ps.setString(2, comboBox.getValue().toString());
    ps.execute();
    ps = conn.prepareStatement("select purchase from totalcosts");
    rs = ps.executeQuery();
    double purchases= 0;
    if (rs.next())purchases = rs.getDouble("purchase");
    ps = conn.prepareStatement("update totalcosts set purchase = ?");
    ps.setDouble(1, (purchases + Double.parseDouble(txtQuantity.getText())*unitPrice));
    ps.execute();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Order Has been Completed");
    alert.setContentText("We ordered Quantity = " + txtQuantity.getText() + "From the raw material --> " + comboBox.getValue() +"\n now, the factory has a total quantity = "+ quantity);
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
    ///////////////////////////////////////////////
    public void subtractActionButton (ActionEvent event){
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
    PreparedStatement ps = conn.prepareStatement("select quantity from rowmaterial where rowmaterialName = ?");
    ps.setString(1, comboBox.getValue().toString());
    ResultSet rs = ps.executeQuery();
    if (rs.next())
    {
    quantity = rs.getDouble("quantity");
    }
     if (quantity >= Double.parseDouble(txtQuantity.getText()))
     {
       
      ps = conn.prepareStatement("update rowmaterial set quantity = ? where rowmaterialName = ?");
    ps.setDouble(1, (quantity - Double.parseDouble(txtQuantity.getText())));
    ps.setString(2, comboBox.getValue().toString());
    ps.execute();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Complete successfully");
          alert.setContentText("The last quantity  = " + quantity + "\nThe quantity subtracted = " + txtQuantity.getText() + "\nThe Current Quantity = " +  (quantity - Double.parseDouble(txtQuantity.getText())));
          alert.showAndWait(); 
          return;
     }
     else {
       Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not Available quantity");
          alert.setContentText("There is at most " + quantity + "in your warehouse");
          alert.showAndWait(); 
          return;
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
