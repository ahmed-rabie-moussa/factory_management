
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AddReportController implements Initializable {
@FXML private Label fromLabel;
@FXML private ComboBox<String> toComboBox;
@FXML private TextArea text;
private ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
    fromLabel.setText(LoginController.xuserName);
    Connection conn = ConnectDataBase.connect();
    PreparedStatement ps = conn.prepareStatement("select distinct JobID from users where JobID <> (select JobID from users where userName = ? AND userpassword = ? )");
    ps.setString(1, LoginController.xuserName);
    ps.setString(2, LoginController.xpassword);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
    list.add(rs.getString("JobID"));
    }
    toComboBox.setItems(list);
    }
      catch (SQLException ex)
      {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
      }
    }    
    public void SendActionButton (ActionEvent event)
    {
    if (toComboBox.getSelectionModel().isEmpty())
    {
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Selected Destination");
          alert.setContentText("Please, Select the destination.");
          alert.showAndWait(); 
          return;
    }
    if (text.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No data entered in Report");
          alert.setContentText("Please, type your problem to be send.");
          alert.showAndWait(); 
          return;
    }
    try {
        Connection conn = ConnectDataBase.connect();
        PreparedStatement ps = conn.prepareStatement("insert into report (`comingFrom`, `sendTo`, `Description`) values( ? , ? , ? )");
        ps.setString(1, fromLabel.getText());
        ps.setString(2, toComboBox.getSelectionModel().getSelectedItem());
        ps.setString(3, text.getText());
        ps.execute();
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Submitted Successfully");
          alert.setHeaderText("Sent");
          alert.setContentText("Your report has been send successfully.");
          alert.showAndWait(); 
       Main.reportStage.close();
    }
    catch (SQLException ex)
    {
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
    }
    }
}
