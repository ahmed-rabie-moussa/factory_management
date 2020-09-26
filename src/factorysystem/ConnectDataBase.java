package factorysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class ConnectDataBase {
   static Connection conn = null;
   ///////////////////////////////////////////////////////
    static Connection connect ()
    {
        try
        { 
       Class.forName("com.mysql.jdbc.Driver"); 
     conn = DriverManager.getConnection("jdbc:mysql://localhost/factory", "root", "123456789");
    return conn;
    }
    catch (Exception ex){
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error in Connection");
          alert.setHeaderText("we're sorry to aknowledge you that");
          alert.setContentText(ex.getMessage());
          alert.showAndWait();   
        return null;
    }}
/////////////////////////////////////////////////////////       
    
    
        public static void main(String[] args) {
            ConnectDataBase.connect();
    }

}
