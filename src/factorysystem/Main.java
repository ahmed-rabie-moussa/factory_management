
package factorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage stage;
    public static Stage reportStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
       Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
       root.setId("ROOTNODE");
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setResizable(false);
       stage.setTitle("Login");
       stage.initStyle(StageStyle.UNDECORATED);
       stage.getScene().getStylesheets().setAll(getClass().getResource("Main.css").toString());
       stage.show();
    }
    public static void main(String[] args) {
        launch(args);
       
    }
}
