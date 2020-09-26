package factorysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class FinancialController implements Initializable {
      @FXML public AnchorPane rootPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
public void computeWagesButtonAction (MouseEvent event) throws IOException
{
    rootPane.getChildren().clear();
    Parent root = FXMLLoader.load(getClass().getResource("computewage.fxml"));
    rootPane.getChildren().add(root);

}
public void orderRowMaterialButtonAction (MouseEvent event) throws IOException
{
 rootPane.getChildren().clear();
Parent root =  FXMLLoader.load(getClass().getResource("orderrow.fxml"));
rootPane.getChildren().add(root);
}
public void sellProductMaterialButtonAction (MouseEvent event) throws IOException
{
 rootPane.getChildren().clear();
Parent root =  FXMLLoader.load(getClass().getResource("sell.fxml"));
rootPane.getChildren().add(root);
}
public void computeProfitMaterialButtonAction (MouseEvent event) throws IOException
{
 rootPane.getChildren().clear();
Parent root =  FXMLLoader.load(getClass().getResource("computeprofit.fxml"));
rootPane.getChildren().add(root);
}

public void ExitActionButton (MouseEvent event){
System.exit(0);
}

public void ReportActionButton (MouseEvent event){
    try {
         Stage stage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("report.fxml"));
         root.setId("ROOTNODE");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Report");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene().getStylesheets().setAll(getClass().getResource("Main.css").toString());
        stage.sizeToScene();
        Main.reportStage = stage;
        Main.reportStage.showAndWait();
    }
    catch (Exception ex){
     Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Frame error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
    }
}
 
}
