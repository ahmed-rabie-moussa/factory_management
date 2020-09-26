
package factorysystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReadReportsController implements Initializable {
@FXML private TableView<Report> ReportTable;
    @FXML private TableColumn<Report , String> fromColumn;
    @FXML private TableColumn<Report , String> toColumn;
    @FXML private TableColumn<Report , String> descriptionColumn;
    public ObservableList<Report>  data= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      fromColumn.setCellValueFactory(new PropertyValueFactory <Report , String> ("from"));
      toColumn.setCellValueFactory(new PropertyValueFactory <Report , String> ("to"));
      descriptionColumn.setCellValueFactory(new PropertyValueFactory <Report , String> ("description"));
      try {
          String JobID = null;
          Connection conn = ConnectDataBase.connect();
          PreparedStatement ps = conn.prepareStatement("select JobID from users where userName = ? AND userpassword = ? ");
          ps.setString(1, LoginController.xuserName);
          ps.setString(2, LoginController.xpassword);
          ResultSet rs = ps.executeQuery();
          if (rs.next())
              JobID = rs.getString("JobID");
          ps = conn.prepareStatement("select comingFrom , sendTo , Description from report where sendTo = ? ");
          ps.setString(1, JobID);
          rs = ps.executeQuery();
          while (rs.next())
          {
          data.add(new Report(rs.getString("comingFrom") , rs.getString("sendTo") , rs.getString("Description")));
          }
          ReportTable.setItems(data);
          ReportTable.setRowFactory( tv -> {
    TableRow<Report> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            Report rowData = row.getItem();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Messege");
          alert.setHeaderText("Description");
          alert.setContentText(rowData.getDescription());
          alert.showAndWait(); 
          return;
        }
    });
    return row ;
});
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
