package Home_page;

import Home_Page.doccontroller2;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Home_page.Patient;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class admincontroller implements Initializable {

    @FXML
    private TableColumn<doctor, String> coldemail;

    @FXML
    private TableColumn<doctor, String> colddep;

    @FXML
    private TableColumn<doctor, Integer> coldid;

    @FXML
    private TableColumn<doctor, String> coldname;
    @FXML   
    private TableColumn<doctor, Integer> coldno;
    @FXML
    private TableColumn<doctor, Integer> coldnum;
@FXML
    private TableColumn<Patient, String> colpid;

    @FXML
    private TableColumn<Patient, Integer> colno;

    @FXML
    private TableColumn<Patient, String> colpadid;

    @FXML
    private TableColumn<Patient, Date> colpby;

    @FXML
    private TableColumn<Patient, Integer> colpcn;

    @FXML
    private TableColumn<Patient, Date> colpdate;

    @FXML
    private TableColumn<Patient, String> colpemail;

    @FXML
    private TableColumn<Patient, String> colpgen;

    @FXML
    private TableColumn<Patient, String> colpname;

    @FXML
    private TableColumn<Patient, String> colpstatus;

    @FXML
    private TableView<Patient> patienttable;
    @FXML
    private TableView<doctor> table;

    private ObservableList<doctor> list;
    private ObservableList<Patient> show;
    private Database db;

    private Stage stage;
    private Scene scene;

    public void initializeColumns() {
        if (coldnum != null) {
            coldnum.setCellValueFactory(new PropertyValueFactory<>("contact_Number"));
            coldid.setCellValueFactory(new PropertyValueFactory<>("doctor_Id"));
            coldname.setCellValueFactory(new PropertyValueFactory<>("name"));
            coldemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colddep.setCellValueFactory(new PropertyValueFactory<>("department"));
            coldno.setCellValueFactory(new PropertyValueFactory<>("Number"));
        }
    }

    public void populateTable() {
        if (table != null) {
            try {

                Connection conn = db.connectDB();
                list = FXCollections.observableArrayList();

                ResultSet set = conn.createStatement().executeQuery("SELECT Doctor_Id, Name, Department, Email,Contact_Number, id FROM Doctor");
                while (set.next()) {
                    list.add(new doctor(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5),set.getString(6)));
                }

            } catch (SQLException ex) {
                Logger.getLogger(admincontroller.class.getName()).log(Level.SEVERE, null, ex);

            }
            table.setItems(null);
            table.setItems(list);
        }
    }
     public void initializeColumnsp() {
        if (colpid != null) {
            colpid.setCellValueFactory(new PropertyValueFactory<>("Patient_Id"));
           colpname.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colno.setCellValueFactory(new PropertyValueFactory<>("id"));
            colpdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colpby.setCellValueFactory(new PropertyValueFactory<>("Birthyear"));
            colpgen.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            colpstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            colpadid.setCellValueFactory(new PropertyValueFactory<>("Assign DID"));
            colpemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            colpcn.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));
     
        }
    }

    public void populatepatTable() {
      if(patienttable!=null){
            try {

                Connection conn = db.connectDB();
                show = FXCollections.observableArrayList();

                ResultSet set = conn.createStatement().executeQuery("SELECT id, Patient_id, Full_name, Gender, Birthyear, Contact_Number, email, date, status, Assigned_Doctor_Id FROM patient");
                while (set.next()) {
                    show.add(new Patient(set.getString(1),set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6),set.getString(7), set.getString(8), set.getString(9), set.getString(10)));
                }

            } catch (SQLException ex) {
                Logger.getLogger(doccontroller2.class.getName()).log(Level.SEVERE, null, ex);

            }
            patienttable.setItems(null);
            patienttable.setItems(show);
        
    }}


    public void switchtodoc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Doc.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtopat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_pat.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Adminman.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoapp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_app.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewhis(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("viewhis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void assign(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Assignapp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   @Override
public void initialize(URL url, ResourceBundle rb) {
    db = new Database(); // Handle the exception or display an error message to the user
    
    initializeColumns();
    populateTable();
   }}
        /* try {
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM doctor");
            if (rs.next()) {
                int count = rs.getInt(1);
                // active_doc.setText("Active Doctors: " + count);
            }
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    