package Home_page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Doc.fxml"));
             
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hospital Management System");
        stage.setResizable(false);
      
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}