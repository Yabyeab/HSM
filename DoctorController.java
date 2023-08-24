package Home_page;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import Home_page.Controller;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorController {
    
    @FXML
    private Button login;
    @FXML
    private Label active_doc;

    @FXML
    private CheckBox login_checkbox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_register;

    @FXML
    private TextField login_showpassword;

    @FXML
    private TextField login_username;

   
    @FXML
    private Button dlogin;

    @FXML
 
    private TextField login_docid;

    @FXML
    private CheckBox register_checkbox;

    @FXML
    private TextField register_docfn;

    @FXML
    private TextField register_docid;
@FXML
    private TextField register_docdep;
@FXML
    private TextField register_doccontact;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_log;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showpassword;
  
    @FXML
    private Button register_signup;
    

    //Database Tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Database  db = new Database();
    private final alert alert = new alert();
    
    private Stage stage;
    private Scene scene;
   
   public void docreg(ActionEvent event) {
    if (register_docfn.getText().isEmpty()
            || register_email.getText().isEmpty()
            || register_docid.getText().isEmpty()
            || register_password.getText().isEmpty()) {
        alert.errorMessage("Please fill all the fields");
    } else  {
        String checkDID = "SELECT * FROM Doctor WHERE Doctor_id ='"
                + "DID - "+register_docid.getText() + "'";
       
        connect = db.connectDB();
        try {
            prepare = connect.prepareStatement(checkDID);
            result = prepare.executeQuery();
            if (result.next()) {
                alert.errorMessage("DID - "+register_docid.getText() + " is already exist");
            } else if (register_password.getText().length() < 8) {
                alert.errorMessage("Invalid password, At least 8 characters are needed");
            } else {
                
                String insertData = "INSERT INTO Doctor (Doctor_Id, Name, Department, Email, Contact_Number, Password) " + "VALUES (?, ?, ?, ?, ?, ?)";
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, "DID - "+register_docid.getText());
                prepare.setString(2, register_docfn.getText());
                prepare.setString(3, register_docdep.getText()); // You can add a Department field to the UI and retrieve its value here
                prepare.setString(4, register_email.getText());
                prepare.setString(5, register_doccontact.getText()); // You can add a Contact_Number field to the UI and retrieve its value here
                 prepare.setString(6, register_password.getText());
                prepare.executeUpdate();
                alert.confitmationMessage("Registered Successfully! ");

                // Clear the registration form fields
                register_docid.setText("");
                register_docfn.setText("");
                register_email.setText("");
                register_showpassword.setText("");
                register_doccontact.setText("");
                register_docdep.setText("");
                register_password.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 public void home(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
    private void closeCurrentStage(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
    @FXML
    public void doclogin(ActionEvent event){
           if (login_password.getText().isEmpty()
                || login_username.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be fielld");
        }
           else{
               String sql ="SELECT * FROM doctor WHERE Doctor_Id =? AND Password =?";
               connect = db.connectDB();
                try {
                if (!login_showpassword.isVisible()) {
                    if (!login_showpassword.getText().equals(login_password.getText()));
                    login_showpassword.setText(login_password.getText());
                } else {
                    if (!login_showpassword.getText().equals(login_password.getText()));
                    login_password.setText(login_showpassword.getText());

                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();
                if (result.next()) {
                    alert.successMessage("Login Succesfully!");
                switchtodocinfo(event);
                } else {
                    alert.errorMessage("Incorrect username/password");
                }
           }catch(Exception e){
               e.printStackTrace();
           }
    }}
    public void switchtologin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Doctor_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 public void switchtodocinfo(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Docinfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoregister(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Doctor_Register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void register_showpassword() {
        if (register_checkbox.isSelected()) {
            register_showpassword.setText(register_password.getText());
            register_showpassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showpassword.getText());
            register_showpassword.setVisible(false);
            register_password.setVisible(true);
        }
    }

    public void login_showpassword() {
        if (login_checkbox.isSelected()) {
            login_showpassword.setText(login_password.getText());
            login_showpassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showpassword.getText());
            login_showpassword.setVisible(false);
            login_password.setVisible(true);
        }
    }
   
    @FXML
    public void initialize(URL location, ResourceBundle resource) {
       
        db = new Database();
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM doctor");
        if (rs.next()) {
            int count = rs.getInt(1);
            active_doc.setText("Active Doctors: " + count);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}



