
package factorysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class ReportController implements Initializable {
    @FXML private AnchorPane root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
public void BackActionButton (MouseEvent event){
Main.reportStage.close();
}
public void ReadAction (MouseEvent event) throws IOException
{
    root.getChildren().clear();
    Parent rootx = FXMLLoader.load(getClass().getResource("ReadReports.fxml"));
    root.getChildren().add(rootx);
}
   public void WriteAction (MouseEvent event) throws IOException
{
    root.getChildren().clear();
    Parent rootx = FXMLLoader.load(getClass().getResource("AddReport.fxml"));
    root.getChildren().add(rootx);
}
    
}
