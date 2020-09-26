
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

public class ComputeprofitController implements Initializable {
    @FXML private Label lblWages;
     @FXML private Label lblPurchases;
      @FXML private Label lblSells;
       @FXML private Label lblTotal;
        @FXML private Label lblStatus;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void ComputeActionButton (ActionEvent event){
    try {
        Connection conn = ConnectDataBase.connect();
            PreparedStatement ps = conn.prepareStatement("select wages , purchase , sells from totalcosts");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
             double wages = rs.getDouble("wages");
             double purchases = rs.getDouble("purchase");
             double sells = rs.getDouble("sells");
            lblWages.setText("" + wages);
            lblPurchases.setText("" + purchases);
            lblSells.setText("" + sells);
            lblTotal.setText("" + (sells - (purchases+wages)));
            if ( (sells - (purchases+wages)) > 0)
            {
                lblStatus.setText("Gainer");
            }
            else 
            {
            lblStatus.setText("Loser");
            }
            }
        }
        catch(SQLException ex){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Database error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
        }
    }
    
}
