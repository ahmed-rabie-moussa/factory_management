
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminstratorController implements Initializable {
    @FXML private ComboBox<String> fieldComboBox;
    @FXML private ComboBox<String> sectionCombo;
    @FXML private ComboBox<String> jobIDCombo;
    @FXML private TextField txtKeySearch;
    @FXML private TextField txtUserName;
    @FXML private TextField txtPassword;
    @FXML private TextField txtAddress;
    @FXML private TextField txtHoursWorked;
    @FXML private TextField txtPhoneNumber;
    @FXML private TextField txtSalary;
    @FXML private TableView<User> Table;
    @FXML private TableColumn<User , String> NameColumn;
    @FXML private TableColumn<User , String> passwordColumn;
    @FXML private TableColumn<User , String> AddressColumn;
    @FXML private TableColumn<User , Double> hoursWorkedColumn;
    @FXML private TableColumn<User , Integer> sectionColumn;
    @FXML private TableColumn<User , Integer> phoneNumberColumn;
    @FXML private TableColumn<User , Double> salaryColumn;
     @FXML private TableColumn<User , String> jobIDColumn;
     public ObservableList<User> data = FXCollections.observableArrayList();
     public ObservableList<String> JobIDs = FXCollections.observableArrayList("worker" , "adminstrator" , "supervisor" , "financial");
     public ObservableList<String> Sections = FXCollections.observableArrayList();
     public ObservableList<String> Fields = FXCollections.observableArrayList("userID" , "userName" ,"address" , "hoursWorked" , "sectionID" , "phoneNumber", "salary" , "JobID"  );
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NameColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("userpassword"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("Address"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory <User , Double> ("hoursWorked"));
       sectionColumn.setCellValueFactory(new PropertyValueFactory <User , Integer> ("sectionID"));
       phoneNumberColumn.setCellValueFactory(new PropertyValueFactory <User , Integer> ("phoneNumber"));
       salaryColumn.setCellValueFactory(new PropertyValueFactory <User , Double> ("Salary"));
       jobIDColumn.setCellValueFactory(new PropertyValueFactory <User , String> ("JobID"));
      jobIDCombo.setItems(JobIDs);
      fieldComboBox.setItems(Fields);
      try {
          Connection conn = ConnectDataBase.connect();
          PreparedStatement ps = conn.prepareStatement("select sectionName from section");
          ResultSet rs = ps.executeQuery();
          while (rs.next()){
          Sections.add(rs.getString("sectionName"));
          }
          sectionCombo.setItems(Sections);
          ps = conn.prepareStatement("select * from users");
          rs = ps.executeQuery();
          while (rs.next()){
           data.add(new User(
                                        rs.getInt("userID") ,
                                           rs.getString("userName"),
                                            rs.getString("userpassword"),
                                              rs.getString("address"),
                                                rs.getDouble("hoursWorked"),
                                                  rs.getInt("sectionID"),
                                                    rs.getDouble("rating"),
                                                       rs.getInt("phoneNumber"),
                                                         rs.getDouble("salary"),
                                                           rs.getString("JobID")
                                                         ));
          }
          Table.setItems(data);
       Table.setRowFactory( tv -> {
    TableRow<User> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
            User rowData = row.getItem();
            txtUserName.setText(rowData.getUserName());
            txtPassword.setText(rowData.getUserpassword());
            txtAddress.setText(rowData.getAddress());
            txtHoursWorked.setText(""+rowData.getHoursWorked());
            txtPhoneNumber.setText("" + rowData.getPhoneNumber());
            txtSalary.setText(""+ rowData.getSalary());
            sectionCombo.getSelectionModel().select(rowData.getSectionID()-1);
            jobIDCombo.getSelectionModel().select(""+rowData.getJobID());
        }
    });
    return row ;
   });
      }
      catch (SQLException ex){
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
      }
       
      
    }    
    public void ExitAction (MouseEvent event){
    System.exit(0);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addAction (MouseEvent event){
    if (txtUserName.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("UserName not defined");
          alert.setHeaderText("No user name");
          alert.setContentText("Please, enter a user name");
          alert.showAndWait(); 
          return;
    }
     if (txtPassword.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Password not defined");
          alert.setHeaderText("No password");
          alert.setContentText("Please, enter a password");
          alert.showAndWait(); 
          return;
    }
      if (txtAddress.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Address not defined");
          alert.setHeaderText("No address");
          alert.setContentText("Please, enter an address");
          alert.showAndWait(); 
          return;
    }
       if (txtHoursWorked.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("hoursWorked not defined");
          alert.setHeaderText("No Hours Wroked");
          alert.setContentText("Please, enter hours Worked");
          alert.showAndWait(); 
          return;
    }
    if (txtPhoneNumber.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Phone Number not defined");
          alert.setHeaderText("No Phone Number");
          alert.setContentText("Please, enter a phone number");
          alert.showAndWait(); 
          return;
    }
    if (txtSalary.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Salary not defined");
          alert.setHeaderText("No Salary");
          alert.setContentText("Please, enter a salary");
          alert.showAndWait(); 
          return;
    }
   if (!Validation.validateName(txtUserName.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("UserName not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid UserName");
          alert.showAndWait(); 
          return;
   }

     if (!Validation.validateFractionNumber(txtHoursWorked.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("HoursWorked not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid Hours");
          alert.showAndWait(); 
          return;
   }
      if (!Validation.validateIntegerNumber(txtPhoneNumber.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Phone Number not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid phone number");
          alert.showAndWait(); 
          return;
   }
       if (!Validation.validateFractionNumber(txtSalary.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Salary not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid Salary");
          alert.showAndWait(); 
          return;
   }
       if (jobIDCombo.getSelectionModel().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("JobID Error");
          alert.setHeaderText("Not Selected");
          alert.setContentText("Please, select a JobID");
          alert.showAndWait(); 
          return;
       }
        if (sectionCombo.getSelectionModel().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Section Error");
          alert.setHeaderText("Not Selected");
          alert.setContentText("Please, select a Section");
          alert.showAndWait(); 
          return;
       }
       try {
       Connection conn = ConnectDataBase.connect();
       PreparedStatement ps = conn.prepareStatement("INSERT INTO `users`( `userName`, `userpassword`, `address`, `hoursWorked`, `sectionID`,  `phoneNumber`, `salary`, `JobID`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )");
       ps.setString(1, txtUserName.getText());
       ps.setString(2, txtPassword.getText());
       ps.setString(3, txtAddress.getText());
       ps.setString(4, txtHoursWorked.getText());
       ps.setInt(5, sectionCombo.getSelectionModel().getSelectedIndex()+1);
       ps.setString(6, txtPhoneNumber.getText());
       ps.setString(7, txtSalary.getText());
       ps.setString(8, jobIDCombo.getSelectionModel().getSelectedItem());
       ps.execute();
       ps = conn.prepareStatement("select * from users where userName = ? AND userpassword = ? ");
        ps.setString(1, txtUserName.getText());
       ps.setString(2, txtPassword.getText());
       ResultSet rs = ps.executeQuery();
       if (rs.next())
       {
        data.add(new User(
                                        rs.getInt("userID") ,
                                           rs.getString("userName"),
                                            rs.getString("userpassword"),
                                              rs.getString("address"),
                                                rs.getDouble("hoursWorked"),
                                                  rs.getInt("sectionID"),
                                                    rs.getDouble("rating"),
                                                       rs.getInt("phoneNumber"),
                                                         rs.getDouble("salary"),
                                                           rs.getString("JobID")
                                                         ));
       }
       Table.setItems(data);
       }
   catch (SQLException ex){
       Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
   }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void SearchAction (MouseEvent event){
    if (fieldComboBox.getSelectionModel().isEmpty())
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Selected Field");
          alert.setHeaderText("Message");
          alert.setContentText("Select a field to search with");
          alert.showAndWait(); 
          return;
    }
    String x = fieldComboBox.getSelectionModel().getSelectedItem();
    if (x.equals("userName") || x.equals("address") || x.equals("JobID"))
    {
    if (!Validation.validateName(txtKeySearch.getText()))
    {
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Validation");
          alert.setHeaderText("Not Valid input");
          alert.setContentText("Please, enter a Valid input as names");
          alert.showAndWait(); 
          return;
    }
    }
    else if (x.equals("userID") || x.equals("sectionID") || x.equals("phoneNumber")){
        if (!Validation.validateIntegerNumber(txtKeySearch.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Validation");
          alert.setHeaderText("Not Valid input");
          alert.setContentText("Please, enter a Valid input as Integer numbers");
          alert.showAndWait(); 
          return;
        }
    }
    else if (!x.equals("userpassword")){
    if (!Validation.validateFractionNumber(txtKeySearch.getText())){
    Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Validation");
          alert.setHeaderText("Not Valid input");
          alert.setContentText("Please, enter a Valid input as Integer numbers or Fraction numbers");
          alert.showAndWait(); 
          return;
    }
    }
    try {
    Connection conn = ConnectDataBase.connect();
    PreparedStatement ps = conn.prepareStatement("select * from users where `"+x+"` = ? ");
    ps.setString(1, txtKeySearch.getText());
    ResultSet rs = ps.executeQuery();
    data.clear();
    while (rs.next()){
    data.add(new User(
                                        rs.getInt("userID") ,
                                           rs.getString("userName"),
                                            rs.getString("userpassword"),
                                              rs.getString("address"),
                                                rs.getDouble("hoursWorked"),
                                                  rs.getInt("sectionID"),
                                                    rs.getDouble("rating"),
                                                       rs.getInt("phoneNumber"),
                                                         rs.getDouble("salary"),
                                                           rs.getString("JobID")
                                                         ));
    }
    Table.setItems(data);
    }
    catch (SQLException ex){
     Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("DataBase error");
          alert.setContentText(ex.getMessage());
          alert.showAndWait(); 
          return;
    }  
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteAction(MouseEvent event){
        int index = Table.getSelectionModel().getSelectedIndex();
    if (index != -1){
    User selectedUser = data.get(index);
       try {
       Connection conn = ConnectDataBase.connect();
       PreparedStatement ps = conn.prepareStatement("DELETE FROM `users` WHERE userID = ? ");
       ps.setInt(1, selectedUser.getUserID());
       ps.execute();
       data.remove(index);
       Table.setItems(data);
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
    else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Selected user");
          alert.setContentText("Please, Select row to be deleted.");
          alert.showAndWait(); 
          return;
    }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateAction (MouseEvent event){
    int index = Table.getSelectionModel().getSelectedIndex();
    if (index != -1){
        if (txtUserName.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("UserName not defined");
          alert.setHeaderText("No user name");
          alert.setContentText("Please, enter a user name");
          alert.showAndWait(); 
          return;
    }
     if (txtPassword.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Password not defined");
          alert.setHeaderText("No password");
          alert.setContentText("Please, enter a password");
          alert.showAndWait(); 
          return;
    }
      if (txtAddress.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Address not defined");
          alert.setHeaderText("No address");
          alert.setContentText("Please, enter an address");
          alert.showAndWait(); 
          return;
    }
       if (txtHoursWorked.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("hoursWorked not defined");
          alert.setHeaderText("No Hours Wroked");
          alert.setContentText("Please, enter hours Worked");
          alert.showAndWait(); 
          return;
    }
    if (txtPhoneNumber.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Phone Number not defined");
          alert.setHeaderText("No Phone Number");
          alert.setContentText("Please, enter a phone number");
          alert.showAndWait(); 
          return;
    }
    if (txtSalary.getText().isEmpty())
    {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Salary not defined");
          alert.setHeaderText("No Salary");
          alert.setContentText("Please, enter a salary");
          alert.showAndWait(); 
          return;
    }
   if (!Validation.validateName(txtUserName.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("UserName not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid UserName");
          alert.showAndWait(); 
          return;
   }
   
     if (!Validation.validateFractionNumber(txtHoursWorked.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("HoursWorked not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid Hours");
          alert.showAndWait(); 
          return;
   }
      if (!Validation.validateIntegerNumber(txtPhoneNumber.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Phone Number not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid phone number");
          alert.showAndWait(); 
          return;
   }
       if (!Validation.validateFractionNumber(txtSalary.getText()))
   {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Salary not valid");
          alert.setHeaderText("Not Valid");
          alert.setContentText("Please, enter a Valid Salary");
          alert.showAndWait(); 
          return;
   }
       if (jobIDCombo.getSelectionModel().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("JobID Error");
          alert.setHeaderText("Not Selected");
          alert.setContentText("Please, select a JobID");
          alert.showAndWait(); 
          return;
       }
        if (sectionCombo.getSelectionModel().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Section Error");
          alert.setHeaderText("Not Selected");
          alert.setContentText("Please, select a Section");
          alert.showAndWait(); 
          return;
       }
        
    User selectedUser = data.get(index);
       try {
       Connection conn = ConnectDataBase.connect();
       PreparedStatement ps = conn.prepareStatement("UPDATE `users` SET `userName`= ? ,`userpassword`= ? ,`address`= ? ,`hoursWorked`= ? ,`sectionID`= ? ,`phoneNumber`= ? ,`salary`= ? ,`JobID`= ? WHERE userID = ?");
       ps.setString(1, txtUserName.getText());
       ps.setString(2, txtPassword.getText());
       ps.setString(3, txtAddress.getText());
       ps.setString(4, txtHoursWorked.getText());
       ps.setInt(5, sectionCombo.getSelectionModel().getSelectedIndex()+1);
       ps.setString(6, txtPhoneNumber.getText());
       ps.setString(7, txtSalary.getText());
       ps.setString(8, jobIDCombo.getSelectionModel().getSelectedItem());
       ps.setInt(9, selectedUser.getUserID());
       ps.execute();
       selectedUser.setUserName(txtUserName.getText());
       selectedUser.setUserpassword(txtPassword.getText());
       selectedUser.setAddress(txtAddress.getText());
       selectedUser.setHoursWorked(Double.parseDouble(txtHoursWorked.getText()));
       selectedUser.setSectionID(sectionCombo.getSelectionModel().getSelectedIndex()+1);
       selectedUser.setPhoneNumber(Integer.parseInt(txtPhoneNumber.getText()));
       selectedUser.setSalary(Double.parseDouble(txtSalary.getText()));
       selectedUser.setJobID( jobIDCombo.getSelectionModel().getSelectedItem());
       data.set(index, selectedUser);
       Table.setItems(data);
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
    else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("No Selected Row");
          alert.setContentText("Please, select the row you want to modify");
          alert.showAndWait(); 
          return;
    }
    }
}
