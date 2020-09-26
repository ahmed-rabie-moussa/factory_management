package factorysystem;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
//////Attaching elements with the Controller
      @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    protected static String xuserName;
    protected static String xpassword;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    ///////Login Button Action
      public void loginButtonAction (ActionEvent event) throws IOException
    {
        if (userName.getText().equals(""))
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No user name value !");
          alert.setContentText("Enter the User Name");
          alert.showAndWait(); 
          return;
        }
        if (!Validation.validateName(userName.getText()))
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not valid");
          alert.setContentText("it isn't a valid user name");
          alert.showAndWait(); 
          return;
        }
         if (password.getText().equals(""))
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No password value !");
          alert.setContentText("Enter the password");
          alert.showAndWait(); 
          return;
        }
        Connection conn = ConnectDataBase.connect();
        try {
        PreparedStatement ps = conn.prepareStatement("Select JobID from users where userName = ? AND userpassword = ? ");
        ps.setString(1, userName.getText());
        ps.setString(2, password.getText());
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
        if (rs.getString("JobID").equals("worker")){
            xuserName = userName.getText();
            xpassword = password.getText();
       Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("Worker.fxml"));
       root.setId("ROOTNODE");
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setResizable(false);
       stage.setTitle("Worker");
       stage.initStyle(StageStyle.UNDECORATED);
       stage.getScene().getStylesheets().setAll(getClass().getResource("Main.css").toString());
       Main.stage.close();
       Main.stage = stage;
       Main.stage.sizeToScene();
       Main.stage.show();
        }
        else if (rs.getString("JobID").equals("financial")){
            xuserName = userName.getText();
            xpassword = password.getText();
        Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("financial.fxml"));
       root.setId("ROOTNODE");
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setResizable(false);
       stage.setTitle("Financial");
       stage.initStyle(StageStyle.UNDECORATED);
       stage.getScene().getStylesheets().setAll(getClass().getResource("Main.css").toString());
       Main.stage.close();
       Main.stage = stage;
       Main.stage.sizeToScene();
       Main.stage.show();
        }
         else if (rs.getString("JobID").equals("supervisor")){
            xuserName = userName.getText();
            xpassword = password.getText();
        Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("supervisor.fxml"));
       root.setId("ROOTNODE");
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setResizable(false);
       stage.setTitle("Supervisor");
       stage.initStyle(StageStyle.UNDECORATED);
       stage.getScene().getStylesheets().setAll(getClass().getResource("Main.css").toString());
       Main.stage.close();
       Main.stage = stage;
       Main.stage.sizeToScene();
       Main.stage.show();
        }
          else if (rs.getString("JobID").equals("adminstrator")){
            xuserName = userName.getText();
            xpassword = password.getText();
        Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("adminstrator.fxml"));
       root.setId("ROOTNODE");
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setResizable(false);
       stage.setTitle("Adminstrator");
       stage.initStyle(StageStyle.UNDECORATED);
       stage.getScene().getStylesheets().setAll(getClass().getResource("Main.css").toString());
       Main.stage.close();
       Main.stage = stage;
       Main.stage.sizeToScene();
       Main.stage.show();
        }
        }
     
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Not found");
          alert.setContentText("Sorry, No one of the users of this (FMS)system has this identification data");
          alert.showAndWait(); 
          return;
        
        }
        }
        catch (SQLException ex){
        Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Your SQL syntax not good");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
        }
    }   
    ///////Exit Button Action
    public void exitButtonAction (ActionEvent event)
    {
    System.exit(0);
    }
    
}
