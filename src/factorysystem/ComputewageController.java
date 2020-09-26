
package factorysystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ComputewageController implements Initializable {
@FXML private TextField txtUserID;
@FXML private TextField txtName;
@FXML private TextField txtSalary;
@FXML private TextField txtImpulses;
@FXML private TextField txtHoursNumber;
@FXML private TextField txtHourPrice;
@FXML private Label lbltotal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void ComputeButtonAction (ActionEvent event){
    txtName.setText("");
    txtSalary.setText("");
    txtImpulses.setText("");
    txtHoursNumber.setText("");
    txtHourPrice.setText("");
    if (txtUserID.getText().equals("")){
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No data inserted");
          alert.setContentText("Enter the user ID to compute salary/wage.");
          alert.showAndWait(); 
          return;
    }
    if(!Validation.validateIntegerNumber(txtUserID.getText()))
    {
    Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not valid data");
          alert.setContentText("Enter a valid user ID to compute salary/wage.");
          alert.showAndWait(); 
          return;
    }
   
    try {
    Connection conn = ConnectDataBase.connect();
        PreparedStatement ps = conn.prepareStatement("select userName , salary , rating , hoursWorked , hourCost , JobID from users natural join section where userID = ?");
        ps.setString(1, txtUserID.getText());
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
        txtName.setText(rs.getString("userName"));
        double salary = rs.getDouble("salary");
        double rate = rs.getDouble("rating");
        double bonus;
        if (rate >= 0 && rate <3)
        {
            bonus = -0.3;
        }
        else  if (rate >= 3 && rate < 5)
        {
            bonus = -0.1;
        }
        else  if (rate >= 5 && rate <8)
        {
            bonus = 0.1;
        }
        else  if (rate >= 8 && rate <= 10)
        {
            bonus = 0.3;
        }
        else {bonus = 0;}
        txtImpulses.setText("" + (bonus*100) + "%");
        double hoursWorked = rs.getDouble("hoursWorked");
        double hourPrice = rs.getDouble("hourCost");
        if (rs.getString("JobID").equals("worker"))
        {
        txtSalary.setText("N/A");
        txtHoursNumber.setText(""+ hoursWorked);
        txtHourPrice.setText("" + hourPrice);
        lbltotal.setText(""+ (hoursWorked*hourPrice + (hoursWorked*hourPrice*bonus)));
        try {
        Connection conn2 = ConnectDataBase.connect();
        PreparedStatement ps2 = conn2.prepareStatement("update users set hoursWorked = 0 where userID = ?");
        ps2.setString(1, txtUserID.getText());
        ps2.execute();
        ps2 = conn2.prepareStatement("select wages from totalcosts");
        ResultSet rs2 = ps2.executeQuery();
        double totalwages = 0;
        if (rs2.next()){
        totalwages = rs2.getDouble("wages");
        }
        totalwages +=  (hoursWorked*hourPrice + (hoursWorked*hourPrice*bonus));
        ps2 = conn2.prepareStatement("update totalcosts set wages = ?");
        ps2.setDouble(1, totalwages);
        ps2.execute();
        }
        catch (SQLException ex){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Database error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
        }
        }
        else {
         txtSalary.setText("" + salary);
         txtHoursNumber.setText("N/A");
         txtHourPrice.setText("N/A");
         lbltotal.setText("" + (salary + (salary*bonus)));
        }
        }
    }
    catch (SQLException ex){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Database error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
    }
    }
}
