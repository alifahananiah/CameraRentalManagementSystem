/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package camerarentalmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    @FXML
    private Button close;
    
    private Connection connect;
    private PreparedStatement pst;
    private ResultSet rs;
    
    private double x = 0;
    private double y = 0;
    
    public void loginAdmin() {
        String sql = "SELECT * FROM ADMIN where username = ? and password = ?";
        connect = database.connectDb();
        try {
            pst = connect.prepareStatement(sql);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            
            rs = pst.executeQuery();
            Alert alert;
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert (AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (rs.next()) {
                    
                    getData.username = username.getText();
                    alert = new Alert (AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    btnLogin.getScene().getWindow().hide();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene (root);
                    
                    root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                    
                    root.setOnMouseDragged ((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                }else {
                    alert = new Alert (AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username or Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {e.printStackTrace(); }
    }
    
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
