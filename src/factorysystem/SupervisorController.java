
package factorysystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SupervisorController implements Initializable {
    @FXML private ComboBox<String> comboType;
    @FXML private ComboBox<String> comboSearch;
    @FXML private TextField txtKey;
    @FXML private TableView<User> supervisorTable;
    @FXML private TableColumn<User , Integer> IDColumn;
    @FXML private TableColumn<User , String> NameColumn;
    @FXML private TableColumn<User , String> passwordColumn;
    @FXML private TableColumn<User , String> AddressColumn;
    @FXML private TableColumn<User , Double> hoursWorkedColumn;
    @FXML private TableColumn<User , Integer> sectionColumn;
    @FXML private TableColumn<User , Integer> phoneNumberColumn;
    @FXML private TableColumn<User , Double> salaryColumn;
     @FXML private TableColumn<User , String> jobIDColumn;
     @FXML private TableColumn<User , Double> ratingColumn;
     @FXML private TableView<RawMaterial> rawTable;
     @FXML private TableColumn<RawMaterial , Integer> rowMaterialIDColumn;
     @FXML private TableColumn<RawMaterial , String> rowMaterialNameColumn;
     @FXML private TableColumn<RawMaterial , Integer> supplierIDColumn;
     @FXML private TableColumn<RawMaterial , Double> unitPriceColumn;
     @FXML private TableColumn<RawMaterial , Double> rateColumn;
     @FXML private TableColumn<RawMaterial , Double> quantityColumn;
     @FXML private TableColumn<RawMaterial , String> storageMethodColumn;
      @FXML private TableView<Product> productTable;
     @FXML private TableColumn<Product , Integer> productIDColumn;
     @FXML private TableColumn<Product , String> productNameColumn;
     @FXML private TableColumn<Product , Integer> distributorIDColumn;
     @FXML private TableColumn<Product , Double> pUnitPriceColumn;
     @FXML private TableColumn<Product , Double> pRatingColumn;
     @FXML private TableColumn<Product , Double> pQuantityColumn;
     @FXML private TextField txtNewRate;
     @FXML private Button RateButton;
     public ObservableList<User> data = FXCollections.observableArrayList();
     public ObservableList<RawMaterial> RawMaterialData = FXCollections.observableArrayList();
      public ObservableList<Product> ProductData = FXCollections.observableArrayList();
     private ObservableList list ;
     private String table;
     private String Column;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> typeList = FXCollections.observableArrayList("Person" , "Raw Material" , "Product");
        comboType.setItems(typeList);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        supervisorTable.setEditable(true);
        IDColumn.setCellValueFactory(new PropertyValueFactory <User , Integer> ("userID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("userpassword"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("Address"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory <User , Double> ("hoursWorked"));
       sectionColumn.setCellValueFactory(new PropertyValueFactory <User , Integer> ("sectionID"));
       phoneNumberColumn.setCellValueFactory(new PropertyValueFactory <User , Integer> ("phoneNumber"));
       salaryColumn.setCellValueFactory(new PropertyValueFactory <User , Double> ("Salary"));
       jobIDColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("JobID"));
       ratingColumn.setCellValueFactory(new PropertyValueFactory <User , Double> ("Rating"));
       ratingColumn.setEditable(true);
      
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////
       rowMaterialIDColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , Integer> ("rowmaterialID"));
         rowMaterialNameColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , String> ("rowmaterialName"));
           supplierIDColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , Integer> ("supplierID"));
            unitPriceColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , Double> ("unitPrice"));
               rateColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , Double> ("rate"));
                 quantityColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , Double> ("quantity"));
                   storageMethodColumn.setCellValueFactory(new PropertyValueFactory<RawMaterial , String> ("storageMethod"));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
                   productIDColumn.setCellValueFactory(new PropertyValueFactory<Product , Integer> ("productID"));
         productNameColumn.setCellValueFactory(new PropertyValueFactory<Product , String> ("productName"));
          distributorIDColumn.setCellValueFactory(new PropertyValueFactory<Product , Integer> ("distributorID"));
            pUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<Product , Double> ("unitPrice"));
               pRatingColumn.setCellValueFactory(new PropertyValueFactory<Product , Double> ("rate"));
                 pQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product , Double> ("quantity"));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
                  supervisorTable.setVisible(false);
                   rawTable.setVisible(false);
                   productTable.setVisible(false);
                   RateButton.setVisible(false);
                   txtNewRate.setVisible(false);
    }    
 
    public void comboTypeAction (ActionEvent event)
 {
     if (comboType.getSelectionModel().getSelectedItem().equals("Person")) {
         table = "users";
         list = FXCollections.observableArrayList("userID" , "userName" , "JobID" , "SectionID");
         comboSearch.setItems(list);
     }
     else if (comboType.getSelectionModel().getSelectedItem().equals("Raw Material")) {
         table = "rowmaterial";
         list = FXCollections.observableArrayList("rowmaterialID" , "rowmaterialName" , "supplierID" , "quantity" , "unitPrice");
          comboSearch.setItems(list);
     } else if (comboType.getSelectionModel().getSelectedItem().equals("Product")) {
         table = "product";
         list = FXCollections.observableArrayList("productID" , "productName" , "distributorID" , "quantity" , "unitPrice");
          comboSearch.setItems(list);
     }
 }
    /////////////////////////////////////////////////////////////////////////////////////////
    public void ExitActionButton (ActionEvent event){
    System.exit(0);
    }
 ///////////////////////////////////////////////////////////////////////////////////////////
    public void SearchActionButton (ActionEvent event) throws InterruptedException{
    if (comboType.getSelectionModel().isEmpty())
    {
    Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Type Selected");
          alert.setContentText("Please, choose type first to search for data");
          alert.showAndWait(); 
          return;
    }
      if (comboSearch.getSelectionModel().isEmpty())
    {
    Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No key Selected");
          alert.setContentText("Please, choose key first to search for data");
          alert.showAndWait(); 
          return;
    }
         if (txtKey.getText().equals(""))
    {
    Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No key entered");
          alert.setContentText("Please, Enter a key first to search for data");
          alert.showAndWait(); 
          return;
    }
         String x = comboSearch.getSelectionModel().getSelectedItem();
         if (x.equals("userName")||x.equals("rowmaterialName")||x.equals("productName")||x.equals("JobID"))
         {
           try{
               Connection conn = ConnectDataBase.connect();
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+x+"` = ? ");
           ps.setString(1, txtKey.getText());
           ResultSet rs = ps.executeQuery();
             while (rs.next())
             {
                 RateButton.setVisible(true);
                 txtNewRate.setVisible(true);
                 txtKey.setEditable(false);
                 
                 if (table.equals("users")){
                   
                data.add(new User(
                                        rs.getInt("userID") ,
                                           rs.getString("userName"),
                                             null,
                                              rs.getString("address"),
                                                rs.getDouble("hoursWorked"),
                                                  rs.getInt("sectionID"),
                                                    rs.getDouble("rating"),
                                                       rs.getInt("phoneNumber"),
                                                         rs.getDouble("salary"),
                                                           rs.getString("JobID")
                                                         ));
        supervisorTable.setItems(data);
         supervisorTable.setLayoutY(100);
                supervisorTable.setLayoutX(0);
                supervisorTable.setMinWidth(1086);
                 supervisorTable.setMinHeight(415);
        rawTable.setVisible(false);
        supervisorTable.setVisible(true);
       
        IDColumn.setEditable(false);
        NameColumn.setEditable(false);
        AddressColumn.setEditable(false);
        hoursWorkedColumn.setEditable(false);
        sectionColumn.setEditable(false);
        phoneNumberColumn.setEditable(false);
        salaryColumn.setEditable(false);
        jobIDColumn.setEditable(false);
        ratingColumn.setEditable(true);
        IDColumn.setVisible(false);
        passwordColumn.setVisible(false);
             }
             else if (table.equals("rowmaterial"))
             {
                 RawMaterialData.add(new RawMaterial(
                                        rs.getInt("rowmaterialID") ,
                                           rs.getString("rowmaterialName"),
                                              rs.getInt("supplierID"),
                                                rs.getDouble("unitPrice"),
                                                  rs.getDouble("rate"),
                                                    rs.getDouble("quantity"),
                                                       rs.getString("storageMethod")                                                     
                                                         )); 

                  rawTable.setItems(RawMaterialData);
                  rowMaterialIDColumn.setVisible(false);
                 rowMaterialNameColumn.setEditable(false);
                 supplierIDColumn.setEditable(false);
                 unitPriceColumn.setEditable(false);
                 rateColumn.setEditable(true);
                 quantityColumn.setEditable(false);
                 storageMethodColumn.setEditable(false);
                rawTable.setLayoutY(100);
                rawTable.setLayoutX(0);
                 rawTable.setMinWidth(1086);
                 rawTable.setMinHeight(415);
                 supervisorTable.setVisible(false);
                 rawTable.setVisible(true);
                
                 
               
                 
                 
             }
                 else if (table.equals("product"))
             {
                 ProductData.add(new Product(
                                        rs.getInt("productID") ,
                                           rs.getString("productName"),
                                              rs.getInt("distributorID"),
                                                rs.getDouble("unitPrice"),
                                                  rs.getDouble("rate"),
                                                    rs.getDouble("quantity")                                          
                                                         )); 

                 productTable.setItems(ProductData);
                  productIDColumn.setVisible(false);
                productNameColumn.setEditable(false);
                distributorIDColumn.setEditable(false);
                 pUnitPriceColumn.setEditable(false);
                 pRatingColumn.setEditable(true);
                 pQuantityColumn.setEditable(false);
                productTable.setLayoutY(100);
                productTable.setLayoutX(0);
                 productTable.setMinWidth(1086);
                 productTable.setMinHeight(415);
                 supervisorTable.setVisible(false);
                 rawTable.setVisible(false);
                 productTable.setVisible(true);
             }
         }}
         catch (SQLException ex)
         {
              Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
         }
        
         }
         else {
         try {
             
         Connection conn = ConnectDataBase.connect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+x+"` = ? ");
             ps.setString(1, txtKey.getText());
            ResultSet rs;
             try {
                 Double.parseDouble(txtKey.getText());
                 rs = ps.executeQuery();
             }
             catch (Exception ex)
             {
              Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Invalid Data");
          alert.setContentText("Please, Enter a valid key first to search for data");
          alert.showAndWait(); 
          return;
             }
             
             while (rs.next())
             {
              RateButton.setVisible(true);
              txtNewRate.setVisible(true);
               txtKey.setEditable(false);
                    if (table.equals("users")){
                data.add(new User(
                                        rs.getInt("userID") ,
                                           rs.getString("userName"),
                                             null,
                                              rs.getString("address"),
                                                rs.getDouble("hoursWorked"),
                                                  rs.getInt("sectionID"),
                                                    rs.getDouble("rating"),
                                                       rs.getInt("phoneNumber"),
                                                         rs.getDouble("salary"),
                                                           rs.getString("JobID")
                                                         ));
             
        rawTable.setVisible(false);
         supervisorTable.setLayoutY(100);
         supervisorTable.setLayoutX(0);
         supervisorTable.setMinWidth(1086);
         supervisorTable.setMinHeight(415);
        supervisorTable.setVisible(true);
        supervisorTable.setItems(data);
        IDColumn.setEditable(false);
        NameColumn.setEditable(false);
        AddressColumn.setEditable(false);
        hoursWorkedColumn.setEditable(false);
        sectionColumn.setEditable(false);
        phoneNumberColumn.setEditable(false);
        salaryColumn.setEditable(false);
        jobIDColumn.setEditable(false);
        ratingColumn.setEditable(true);
        IDColumn.setVisible(false);
        passwordColumn.setVisible(false);
                    } 
             else if (table.equals("rowmaterial"))
             {
                 RawMaterialData.add(new RawMaterial(
                                        rs.getInt("rowmaterialID") ,
                                           rs.getString("rowmaterialName"),
                                              rs.getInt("supplierID"),
                                                rs.getDouble("unitPrice"),
                                                  rs.getDouble("rate"),
                                                    rs.getDouble("quantity"),
                                                       rs.getString("storageMethod")                                                     
                                                         )); 

                  rawTable.setItems(RawMaterialData);
                  rowMaterialIDColumn.setVisible(false);
                 rowMaterialNameColumn.setEditable(false);
                 supplierIDColumn.setEditable(false);
                 unitPriceColumn.setEditable(false);
                 rateColumn.setEditable(true);
                 quantityColumn.setEditable(false);
                 storageMethodColumn.setEditable(false);
                rawTable.setLayoutY(100);
                rawTable.setLayoutX(0);
                 rawTable.setMinWidth(1086);
                 rawTable.setMinHeight(415);
              
                 supervisorTable.setVisible(false);
                 rawTable.setVisible(true);  
             }
                 else if (table.equals("product"))
             {
                 ProductData.add(new Product(
                                        rs.getInt("productID") ,
                                           rs.getString("productName"),
                                              rs.getInt("distributorID"),
                                                rs.getDouble("unitPrice"),
                                                  rs.getDouble("rate"),
                                                    rs.getDouble("quantity")                                          
                                                         )); 

                 productTable.setItems(ProductData);
                  productIDColumn.setVisible(false);
                productNameColumn.setEditable(false);
                distributorIDColumn.setEditable(false);
                 pUnitPriceColumn.setEditable(false);
                 pRatingColumn.setEditable(true);
                 pQuantityColumn.setEditable(false);
                productTable.setLayoutY(100);
                productTable.setLayoutX(0);
                 productTable.setMinWidth(1086);
                 productTable.setMinHeight(415);
                 supervisorTable.setVisible(false);
                 rawTable.setVisible(false);
                 productTable.setVisible(true);
             }
             
         }}
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void ClearActionButton (ActionEvent event){
      txtKey.setText("");
      txtKey.setEditable(true);
      data.clear();
      RawMaterialData.clear();
      ProductData.clear();
      supervisorTable.setVisible(false);
      rawTable.setVisible(false);
      productTable.setVisible(false);
      RateButton.setVisible(false);
      txtNewRate.setVisible(false);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void RateActionButton (ActionEvent event)
{
if (table.equals("users"))
{
int x = supervisorTable.getSelectionModel().getSelectedIndex();
if (x != -1){
User xx = data.get(x);
if (txtNewRate.getText().equals(""))
{
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No new rate entered");
          alert.setContentText("Enter a new rate to be updated.");
          alert.showAndWait(); 
          return;
}
if (!Validation.validateFractionNumber(txtNewRate.getText()))
{
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not valid rate");
          alert.setContentText("Enter a valid rate to be replaced.");
          alert.showAndWait(); 
          return;
}
xx.setRating(Double.parseDouble(txtNewRate.getText()));
try {
Connection conn = ConnectDataBase.connect();
PreparedStatement ps = conn.prepareStatement("update `" + table + "` set `rating` = ? where `userID` = ? ");
ps.setDouble(1, xx.getRating());
ps.setInt(2, xx.getUserID());
ps.execute();
data.set(x, xx);
supervisorTable.setItems(data);
}
catch (SQLException ex){
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
}
}}
else if (table.equals("rowmaterial"))
{
int x = rawTable.getSelectionModel().getSelectedIndex();
if (x != -1){
RawMaterial xx = RawMaterialData.get(x);
if (txtNewRate.getText().equals(""))
{
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No new rate entered");
          alert.setContentText("Enter a new rate to be updated.");
          alert.showAndWait(); 
          return;
}
if (!Validation.validateFractionNumber(txtNewRate.getText()))
{
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not valid rate");
          alert.setContentText("Enter a valid rate to be replaced.");
          alert.showAndWait(); 
          return;
}
xx.setRate(Double.parseDouble(txtNewRate.getText()));
try {
Connection conn = ConnectDataBase.connect();
PreparedStatement ps = conn.prepareStatement("update `" + table + "` set `rate` = ? where `rowmaterialID` = ? ");
ps.setDouble(1, xx.getRate());
ps.setInt(2, xx.getRowmaterialID());
ps.execute();
RawMaterialData.set(x, xx);
rawTable.setItems(RawMaterialData);
}
catch (SQLException ex){
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
}
}
}
else if (table.equals("product"))
{
int x = productTable.getSelectionModel().getSelectedIndex();
if (x != -1){
Product xx = ProductData.get(x);
if (txtNewRate.getText().equals(""))
{
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No new rate entered");
          alert.setContentText("Enter a new rate to be updated.");
          alert.showAndWait(); 
          return;
}
if (!Validation.validateFractionNumber(txtNewRate.getText()))
{
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Not valid rate");
          alert.setContentText("Enter a valid rate to be replaced.");
          alert.showAndWait(); 
          return;
}
xx.setRate(Double.parseDouble(txtNewRate.getText()));
try {
Connection conn = ConnectDataBase.connect();
PreparedStatement ps = conn.prepareStatement("update `" + table + "` set `rate` = ? where `productID` = ? ");
ps.setDouble(1, xx.getRate());
ps.setInt(2, xx.getProductID());
ps.execute();
ProductData.set(x, xx);
productTable.setItems(ProductData);
}
catch (SQLException ex){
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
}
}
}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
