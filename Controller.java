package Home_page;

import javafx.scene.control.Label;
import java.net.URI;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {

    @FXML
    private Hyperlink forget;

    @FXML
    private Button login;

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
    private ComboBox<String> login_user;
    @FXML
    private ComboBox<?> register_select;
    @FXML
    private TextField login_username;
    @FXML
    private CheckBox register_checkbox;

    @FXML
    private TextField register_email;
    @FXML
    private TextField register_username;


    @FXML
    private Hyperlink register_log;
    @FXML
    private Label welcome;

    @FXML
    private PasswordField register_password;
@FXML
    private TextField register_by1;
    @FXML
    private TextField register_showpassword;
     @FXML
    private PasswordField register_email1;


    @FXML
    private PasswordField register_gender1;

   
    @FXML
    private PasswordField register_passwordpat;

    @FXML
    private TextField register_patcn1;

    @FXML
    private TextField register_patfn;

    @FXML
    private TextField register_patid;

   
    @FXML
    private Button register_signup;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button doctors;

    @FXML
    private Button hospital_info;

    @FXML
    private Button patients;

    @FXML
    private Button payment_info;
    TextArea tf1 = new TextArea();
    TextArea tf2 = new TextArea();
    //Database Tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Database db;
    private final alert alert = new alert();
    public void loginAccount(ActionEvent event) throws IOException {
        alert alert = new alert();
        if (login_password.getText().isEmpty() || login_username.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");
        } else {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
             connect = db.connectDB();
            try {
                if (!login_showpassword.isVisible()) {
                    if (!login_showpassword.getText().equals(login_password.getText())) {
                        login_showpassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showpassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showpassword.getText());
                    }
                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();
                if (result.next()) {
                    alert.successMessage("Login Successfully!");
                   
                   
                } else {
                    alert.errorMessage("Incorrect username/password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
       
        }
         Adhome(event);
        loginclear();
        //closeCurrentStage(event);
    }

    public void Adhome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Adminman.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
     
    }

    public void registerpat() throws SQLException {
        alert alert = new alert();
        if (register_email1.getText().isEmpty()
                || register_patfn.getText().isEmpty()
                || register_patid.getText().isEmpty()||
        register_passwordpat.getText().isEmpty()
                || register_gender1.getText().isEmpty()
                || register_by1.getText().isEmpty() || register_patcn1.getText().isEmpty()){
            alert.errorMessage("All fields are necessary to be fielld");

        } else if (register_passwordpat.getText().length() < 8) {
            alert.errorMessage("At least 8 charachters needed");
        } else  {
        String checkDID = "SELECT * FROM patient WHERE patient_id ='"
                + "PID - "+register_patid.getText() + "'";
       
        connect = db.connectDB();
        try {
            prepare = connect.prepareStatement(checkDID);
            result = prepare.executeQuery();
            if (result.next()) {
                alert.errorMessage("PID - "+register_patid.getText() + " is already exist");
            } else if (register_passwordpat.getText().length() < 8) {
                alert.errorMessage("Invalid password, At least 8 characters are needed");
            } else {
                
                String insertData = "INSERT INTO Patient (patient_id, Full_Name,Contact_Number, email, Gender , Birthyear,Password) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, "PID - "+register_patid.getText());
                prepare.setString(2, register_patfn.getText());
                prepare.setString(3, register_patcn1.getText()); // You can add a Department field to the UI and retrieve its value here
                prepare.setString(4, register_email1.getText());
                prepare.setString(5, register_gender1.getText()); // You can add a Contact_Number field to the UI and retrieve its value here
                 prepare.setString(6, register_by1.getText());
                  prepare.setString(7, register_passwordpat.getText());
                prepare.executeUpdate();
                alert.confitmationMessage("Registered Successfully! ");

                // Clear the registration form fields
                register_patid.setText("");
                register_passwordpat.setText("");
                register_email1.setText("");
                register_passwordpat.setText("");
                register_by1.setText("");
                register_gender1.setText("");
                register_patfn.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

    public void loginclear() {

        login_username.clear();
        login_password.clear();
        login_showpassword.clear();
    }

    public void registerclear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showpassword.clear();
    }

    public void switchtologin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//         admpage(event);
    }

    public void switchtologinp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Patient_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 public void pay(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Billpayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoregister(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

  

    public void switchtoregisterp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Patient_Register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void appoint(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Patient_Login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Elshadi Hospital");
        stage.setScene(new Scene(root));
        stage.show();
    }
/*
    public void admpage(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Adminman.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Elshadi Hospital");
        stage.setScene(new Scene(root));
        stage.show();
    }
*/
    public void main(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Elshadi Hospital");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void patpage(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Elshadi Hospital");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void docpage(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Doctor_Login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Elshadi Hospital");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void home(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void tip(ActionEvent event) throws IOException {

        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Health_tip.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Elshadi Hospital");
        stage.setScene(new Scene(root));
        stage.show();

    }

    private void closeCurrentStage(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void backadm(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void doadm(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Doc.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void addDoc(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Doctor_Register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 public void veiwDoc(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Veiw_Doctor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
 }  
  @FXML
    private TextField makeappid;
  @FXML
    private DatePicker datePicker;

  @FXML
private void saveAppointment() {
    String id = makeappid.getText();
    String date = datePicker.getValue().toString();

    // Update the SQL table with appointment data
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "")) {
        String query = "UPDATE appointment SET date = ? WHERE patient_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, date);
        statement.setString(2, id);
        int rowsUpdated = statement.executeUpdate();
        
        if (rowsUpdated > 0) {
            alert.successMessage("Appointment made successfully!");
        } else {
            alert.errorMessage("Please wait a moment until you are assigned to a doctor");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any errors that occur during the database update
    }
}

@FXML
    public void initialize(URL location, ResourceBundle resource) {
     db = new Database();
     tf1.setEditable(false);
     tf2.setEditable(false);
    }
}
