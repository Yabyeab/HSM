package Home_Page;

import Home_page.Database;
import Home_page.alert;
import Home_page.Patient;
import Home_page.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class doccontroller2 implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Database db = new Database();
    private final alert alert = new alert();

    private Stage stage;
    private Scene scene;

    @FXML
    private TableColumn<Patient, String> colpid1;

    @FXML
    private TableColumn<Patient, Integer> colno1;

    @FXML
    private TableColumn<Patient, String> colpadid1;

    @FXML
    private TableColumn<Patient, Integer> colpby1;

    @FXML
    private TableColumn<Patient, Integer> colpcn1;

    @FXML
    private TableColumn<Patient, Date> colpdate1;

    @FXML
    private TableColumn<Patient, String> colpemail1;

    @FXML
    private TableColumn<Patient, String> colpgen1;

    @FXML
    private TableColumn<Patient, String> colpname1;

    @FXML
    private TableColumn<Patient, String> colpstatus1;

    @FXML
    private TableView<Patient> patienttable1;

    @FXML
    private TableColumn<Patient, Year> colpbyearinfo;
    @FXML
    private TextField cancelapp;

    @FXML
    private TableColumn<Patient, Date> colpdateinfo;

    @FXML
    private TableColumn<Patient, Integer> colpidinfo;

    @FXML
    private TableColumn<Patient, String> colpnameinfo;

    @FXML
    private TableColumn<Patient, Integer> colpnuminfo;
    @FXML
    private TableColumn<Appointment, Integer> appdid;

    @FXML
    private TableColumn<Appointment, Integer> apppid;
    @FXML
    private TableColumn<Appointment, String> appstatus;
    @FXML
    private TableView<Appointment> appointmenttable;

    @FXML
    private TextField patientIdTextField;
    @FXML
    private TextField didassign;

    @FXML
    private TextField pidassign;
    @FXML
    private TextField showpat;

    @FXML
    private TableView<Patient> pattableinfo;

    private ObservableList<Patient> listpat;
    private ObservableList<Patient> show;
    private ObservableList<Appointment> showapp;

    public void cancelapp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cancelapp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewapp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("viewapp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void paybytelebirr(MouseEvent event) throws IOException {
        // closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("telebirr.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void paybyamole(MouseEvent event) throws IOException {
        // closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("amole.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void paybycbebirr(MouseEvent event) throws IOException {
        // closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("cbe.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void home(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backdoc(ActionEvent event) throws IOException {
        closeCurrentStage(event);
        Parent root = FXMLLoader.load(getClass().getResource("Docinfo.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void closeCurrentStage(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void cancelAppointment() {
        // alert alert = new alert();
        try {
            int appointmentId = Integer.parseInt(cancelapp.getText());
            Connection conn = db.connectDB();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Appointment SET Status = 'Cancelled' WHERE Patient_id = ?");
            stmt.setInt(1, appointmentId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                // Update successful
                alert.successMessage("The appointment has been cancelled.");
            } else {
                // Appointment not found
                alert.errorMessage("Appointment not found. Please enter a valid appointment ID.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(doccontroller2.class.getName()).log(Level.SEVERE, null, ex);
            alert.errorMessage("Database error. An error occurred while cancelling the appointment.");
        }
    }

    public void showassignapp() {
        if (appointmenttable != null) {
            try {

                Connection conn = db.connectDB();
                showapp = FXCollections.observableArrayList();
                ResultSet set = conn.createStatement().executeQuery("SELECT * FROM appointment WHERE Status = 'pending'");

                while (set.next()) {
                    showapp.add(new Appointment(set.getString(2), set.getString(3), set.getString(4)));

                    // Patient not found
                    // alert.errorMessage("Patient not found. Please enter a valid patient ID.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(doccontroller2.class.getName()).log(Level.SEVERE, null, ex);
            }
            appointmenttable.setItems(showapp);
            /* alert.errorMessage("Database error. An error occurred while retrieving patient data.");
        } catch (NumberFormatException ex) {
            alert.errorMessage("Invalid patient ID. Please enter a valid patient ID.");
        }
    }
             */
        }
    }

    public void assignapp() {
        try {
            // Connect to the database
            Connection conn = db.connectDB();

            // Insert the appointment into the database
            String insertData = "INSERT INTO appointment (patient_id, doctor_id,  status) VALUES (?,?,?)";
            PreparedStatement prepare = conn.prepareStatement(insertData);
            prepare.setString(1, pidassign.getText());
            prepare.setString(2, didassign.getText());
            prepare.setString(3, "pending");

            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeColumnsapp() {
        if (apppid != null) {
            apppid.setCellValueFactory(new PropertyValueFactory<>("Patient_Id"));
            appdid.setCellValueFactory(new PropertyValueFactory<>("Doctor_Id"));
            apppid.setCellValueFactory(new PropertyValueFactory<>("Status"));
        }
    }

    /* public void displayPatientData() {
        try {
            int patientId = Integer.parseInt(patientIdTextField.getText());
            Connection conn = db.connectDB();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patient WHERE patient_Id = ?");
            stmt.setInt(1, patientId);

            ResultSet set = stmt.executeQuery();
            if (set.next()) {
              //  Patient patient = new Patient(set.getString(2), set.getString(3), set.getString(4),
                      //  set.getString(5), set.getString(6));
                // Update the table with patient data
                show = FXCollections.observableArrayList(patient);
                pattableinfo.setItems(show);
            } else {
                // Patient not found
                alert.errorMessage("Patient not found. Please enter a valid patient ID.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(doccontroller2.class.getName()).log(Level.SEVERE, null, ex);
            alert.errorMessage("Database error. An error occurred while retrieving patient data.");
        } catch (NumberFormatException ex) {
            alert.errorMessage("Invalid patient ID. Please enter a valid patient ID.");
        }
    }
     */
    public void initializeColumnsinfo() {
        if (colpidinfo != null) {
            colpidinfo.setCellValueFactory(new PropertyValueFactory<>("Patient_Id"));
            colpnameinfo.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colpnuminfo.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));
            colpdateinfo.setCellValueFactory(new PropertyValueFactory<>("date"));
            colpbyearinfo.setCellValueFactory(new PropertyValueFactory<>("Birthyear"));
        }
    }

    public void initializeColumnspat() {
        if (colpid1 != null) {
            colpid1.setCellValueFactory(new PropertyValueFactory<>("Patient_Id"));
            colpname1.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colno1.setCellValueFactory(new PropertyValueFactory<>("id"));
            colpdate1.setCellValueFactory(new PropertyValueFactory<>("date"));
            colpby1.setCellValueFactory(new PropertyValueFactory<>("Birthyear"));
            colpgen1.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            colpstatus1.setCellValueFactory(new PropertyValueFactory<>("Status"));
            colpadid1.setCellValueFactory(new PropertyValueFactory<>("Assigneddid"));
            colpemail1.setCellValueFactory(new PropertyValueFactory<>("email"));
            colpcn1.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));

        }
    }

    public void populatepatTable() {
        if 
                (patienttable1 != null) {
            try {

                Connection conn = db.connectDB();
                listpat = FXCollections.observableArrayList();

                ResultSet set = conn.createStatement().executeQuery("SELECT id, patient_id, Full_Name, Gender, Birthyear, Contact_Number, email, date, status, Assigned_Doctor_Id FROM patient");
                while (set.next()) {
                    listpat.add(new Patient(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10)));
                }

            } catch (SQLException ex) {
                Logger.getLogger(doccontroller2.class.getName()).log(Level.SEVERE, null, ex);

            }
            patienttable1.setItems(null);
            patienttable1.setItems(listpat);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        db = new Database();
        //patienttable.setItems(list);

        initializeColumnspat();
        populatepatTable();

        initializeColumnsapp();
        showassignapp();
//  initializeColumnsinfo();
        //initializeColumnsshow();

        // pattableinfo.setItems(show);
    }

}
