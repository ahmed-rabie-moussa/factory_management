package factorysystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class WorkerController implements Initializable {
    @FXML private Label time;
   private static int second;
   private static int minute;
   private static int hour;
   private static Timeline timeline;
    public WorkerController() {
         second = 0; minute=0; hour = 0;
         timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ae -> { 
        String seconds , minutes , hours;
        if (second<10)seconds = "0"+second;
        else seconds = ""+second;
        if (minute<10) minutes = "0"+minute;
        else minutes = ""+minute;
        if (hour<10)hours = "0"+hour;
        else hours = ""+hour;
        time.setText( hours + ":" + minutes + ":" + seconds);
        second++;
        if (second == 60)
        {
        minute++;
        second = 0;
        }
        if (minute == 60)
        {
        hour++;
        minute = 0;
        }
        }));
timeline.setCycleCount(Animation.INDEFINITE);
timeline.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    
    public void LogoutButtonAction (ActionEvent event) throws IOException
    {
         timeline.stop();
        double totaltime = hour + (double)(minute/60) + (double)(second/(3600)) ;
        try {
            Connection conn = ConnectDataBase.connect();
           PreparedStatement ps = conn.prepareStatement("Select hoursWorked from users where userName = ? AND userpassword = ?");
        ps.setString(1, LoginController.xuserName);
        ps.setString(2, LoginController.xpassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            totaltime += rs.getDouble("hoursWorked");
            }
            ps = conn.prepareStatement("update users set hoursWorked = ? where userName = ? AND userpassword = ?");
            ps.setDouble(1, totaltime);
            ps.setString(2, LoginController.xuserName);
            ps.setString(3, LoginController.xpassword);
            ps.execute();
        }
        catch (SQLException ex)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Your Syntax is wrong");
          alert.setContentText("please check your statements");
          alert.showAndWait(); 
          return;
        }
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
