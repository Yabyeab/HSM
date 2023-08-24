//* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
/* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package Home_page;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class admintablecontroller implements Initializable {

    @FXML
    private TableColumn<doctor, String> coldemail;

    @FXML
    private TableColumn<doctor, String> colddep;

    @FXML
    private TableColumn<doctor, Integer> coldid;

    @FXML
    private TableColumn<doctor, String> coldname;
    @FXML
    private TableColumn<doctor, String> colstatus;

    @FXML
    private TableColumn<doctor, Integer> coldnum;
    @FXML
    private TableColumn<doctor, String> colddeppend;

    @FXML
    private TableColumn<doctor, String> coldemailpend;

    @FXML
    private TableColumn<doctor, Integer> coldidpend;

    @FXML
    private TableColumn<doctor, String> coldnamepend;

    @FXML
    private TableColumn<doctor, Integer> coldnumpend;

    @FXML
    private TableColumn<doctor, String> colstatuspend;
    @FXML
    private TableColumn<doctor, Void> approveColumn;

    @FXML
    private TableColumn<doctor, Void> rejectColumn;

    @FXML
    private TableView<doctor> pendingRequestsTable;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Database db = new Database();
    private Stage stage;
    private Scene scene;
    doctor doctor = null;
    @FXML
    private TableView<doctor> table;

    private ObservableList<doctor> list;

    public void populateTable() {
        try {

            Connection conn = db.connectDB();
            list = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery("SELECT * FROM Doctor");
            while (set.next()) {
                list.add(new doctor(set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(admincontroller.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(null);
        table.setItems(list);

    }
    private ObservableList<doctor> lis;

    public void pendTable() {
        try {
            Connection conn = db.connectDB();
            lis = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery("SELECT * FROM doctor ");
            while (set.next()) {
                lis.add(new doctor(set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(admincontroller.class.getName()).log(Level.SEVERE, null, ex);

        }

        pendingRequestsTable.setItems(null);
        pendingRequestsTable.setItems(lis);
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

    public void initialize(URL url, ResourceBundle rb) {
        db = new Database();
        initialize_col();
        pendTable();
        initialize_colpend();

    }

    public void switchtodoc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Doc.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        initialize_col();
    }

    @FXML
    public void initialize_col() {
        if (coldnum != null) { // add null check
            coldnum.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));
            coldid.setCellValueFactory(new PropertyValueFactory<>("Doctor_Id"));
            coldname.setCellValueFactory(new PropertyValueFactory<>("Name"));
            coldemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            colddep.setCellValueFactory(new PropertyValueFactory<>("Department"));
            colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        }
    }

    public void initialize_colpend() {
        if (coldnumpend != null) { // 
            coldnumpend.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));
            coldidpend.setCellValueFactory(new PropertyValueFactory<>("Doctor_Id"));
            coldnamepend.setCellValueFactory(new PropertyValueFactory<>("Name"));
            coldemailpend.setCellValueFactory(new PropertyValueFactory<>("Email"));
            colddeppend.setCellValueFactory(new PropertyValueFactory<>("Department"));
            colstatuspend.setCellValueFactory(new PropertyValueFactory<>("Status"));

        }
    }

/*

        Callback<TableColumn<doctor, String>, TableCell<doctor, String>> cellFoctory = (TableColumn<doctor, String> param) -> {
            // make cell containing buttons
            final TableCell<doctor, String> cell = new TableCell<doctor, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView();
                        deleteIcon.setIcon(FontAwesomeIcon.TRASH);
                        deleteIcon.setStyle(
                                "-fx-cursor: hand ;"
                                + "-glyph-size: 28px;"
                                + "-fx-fill: #ff1744;"
                        );

                        FontAwesomeIconView editIcon = new FontAwesomeIconView();
                        editIcon.setIcon(FontAwesomeIcon.PENCIL_SQUARE);
                        editIcon.setStyle(
                                "-fx-cursor: hand ;"
                                + "-glyph-size: 28px;"
                                + "-fx-fill: #00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                doctor = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `student` WHERE id  =" + doctor.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                             //   refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
    

 populateTable();
// Define the "Approve" button column
          approveColumn.setCellFactory(col -> {
            ButtonCell<doctor> buttonCell = new ButtonCell<>("Approve", (doctor doctor) -> {
                // Update the Doctor table to set the status of the corresponding row to "Approved"
                String updateStatusQuery = "UPDATE Doctor SET Status = 'Approved' WHERE Name = ?";
                connect = db.connectDB();
                try {
                    prepare = connect.prepareStatement(updateStatusQuery);
                    prepare.setString(1, doctor.getName());
                    prepare.executeUpdate();
                    // Refresh the TableView to remove the approved request
                    initialize(null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return buttonCell;
        });

        // Define the "Reject" button column
        rejectColumn.setCellFactory(col -> {
            ButtonCell<doctor> buttonCell = new ButtonCell<>("Reject", (doctor doctor) -> {
                // Delete the corresponding row from the Doctor table
                String deleteRequestQuery = "DELETE FROM Doctor WHERE Name =?";
                connect = db.connectDB();
                try {
                    prepare = connect.prepareStatement(deleteRequestQuery);
                    prepare.setString(1, doctor.getName());
                    prepare.executeUpdate();
                    // Refresh the TableView to remove the rejected request
                    initialize(null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return buttonCell;
        });

}

@FXML
public void initialize_col() {
    coldnumpend.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));
    coldidpend.setCellValueFactory(new PropertyValueFactory<>("Doctor_Id"));
    coldnamepend.setCellValueFactory(new PropertyValueFactory<>("Name"));
    coldemailpend.setCellValueFactory(new PropertyValueFactory<>("Email"));
    colddeppend.setCellValueFactory(new PropertyValueFactory<>("Department"));
    colstatuspend.setCellValueFactory(new PropertyValueFactory<>("Status"));
}
*/
