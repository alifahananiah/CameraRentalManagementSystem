/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camerarentalmanagementsystem;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class dashboardController implements Initializable{
     @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAvailableCameras;

    @FXML
    private Button btnRentCamera;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private Label home_AvailableCameras;

    @FXML
    private Label home_TotalIncome;

    @FXML
    private Label homeTotalCustomers;

    @FXML
    private BarChart<?, ?> homeIncomeChart;

    @FXML
    private LineChart<?, ?> homeCustomerData;

    @FXML
    private AnchorPane availableCamerasForm;

    @FXML
    private TextField availableCameras_CameraID;

    @FXML
    private TextField availableCameras_Brand;

    @FXML
    private TextField availableCameras_Model;

    @FXML
    private ImageView availableCameras_ImageView;

    @FXML
    private Button availableCameras_btnImport;

    @FXML
    private ComboBox<?> availableCameras_Status;

    @FXML
    private Button availableCameras_btnInsert;

    @FXML
    private Button availableCameras_btnUpdate;

    @FXML
    private Button availableCameras_btnClear;

    @FXML
    private Button availableCameras_btnDelete;

    @FXML
    private TextField availableCameras_Price;

    @FXML
    private TableView<cameraData> availableCameras_TableView;

    @FXML
    private TableColumn<cameraData, String> availableCameras_col_CameraID;

    @FXML
    private TableColumn<cameraData, String> availableCameras_col_Brand;

    @FXML
    private TableColumn<cameraData, String> availableCameras_col_Model;

    @FXML
    private TableColumn<cameraData, String> availableCameras_col_Price;

    @FXML
    private TableColumn<cameraData, String> availableCameras_col_Status;

    @FXML
    private TextField availableCameras_Search;

    @FXML
    private AnchorPane rentForm;

    @FXML
    private ComboBox<?> rentCameraID;

    @FXML
    private ComboBox<?> rentBrand;

    @FXML
    private ComboBox<?> rentModel;

    @FXML
    private TextField rentFirstName;

    @FXML
    private TextField rentLastName;

    @FXML
    private ComboBox<?> rentGender;

    @FXML
    private DatePicker rentDateRent;

    @FXML
    private DatePicker rentDateReturn;

    @FXML
    private Button btnRent;

    @FXML
    private Label rentTotal;

    @FXML
    private TextField rentAmount;

    @FXML
    private Label rentBalance;

    @FXML
    private TableView<cameraData> rentTableView;

    @FXML
    private TableColumn<cameraData, String> rentColCameraID;

    @FXML
    private TableColumn<cameraData, String> rentColBrand;

    @FXML
    private TableColumn<cameraData, String> rentColModel;

    @FXML
    private TableColumn<cameraData, String> rentColPrice;

    @FXML
    private TableColumn<cameraData, String> rentColStatus;


//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Image image;

    public void homeAvailableCameras(){
        
        String sql = "SELECT COUNT(id) FROM camera WHERE status = 'Available'";
        
        connect = database.connectDb();
        int countAC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countAC = result.getInt("COUNT(id)");
            }
            
            home_AvailableCameras.setText(String.valueOf(countAC));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeTotalIncome(){
        String sql = "SELECT SUM(total) FROM customer";
        
        double sumIncome = 0;
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                sumIncome = result.getDouble("SUM(total)");
            }
            home_TotalIncome.setText("RM" + String.valueOf(sumIncome));
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    public void homeTotalCustomers(){
        
        String sql = "SELECT COUNT(id) FROM customer";
        
        int countTC = 0;
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countTC = result.getInt("COUNT(id)");
            }
            homeTotalCustomers.setText(String.valueOf(countTC));
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeIncomeChart(){
        
        homeIncomeChart.getData().clear();
        String sql = "SELECT date_rented, SUM(total) FROM customer GROUP BY date_rented ORDER BY TIMESTAMP(date_rented) ASC LIMIT 6";
        connect = database.connectDb();
        try{
            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }   
            homeIncomeChart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void homeCustomerChart(){
        homeCustomerData.getData().clear();
        String sql = "SELECT date_rented, COUNT(id) FROM customer GROUP BY date_rented ORDER BY TIMESTAMP(date_rented) ASC LIMIT 4";
        connect = database.connectDb();
        try{
            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            homeCustomerData.getData().add(chart);
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void availableCameraAdd() {

        String sql = "INSERT INTO camera (camera_id, brand, model, price, status, image, date) "
                + "VALUES(?,?,?,?,?,?,?)";
        connect = database.connectDb();
        try {
            Alert alert;
            if (availableCameras_CameraID.getText().isEmpty()
                    || availableCameras_Brand.getText().isEmpty()
                    || availableCameras_Model.getText().isEmpty()
                    || availableCameras_Status.getSelectionModel().getSelectedItem() == null
                    || availableCameras_Price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, availableCameras_CameraID.getText());
                prepare.setString(2, availableCameras_Brand.getText());
                prepare.setString(3, availableCameras_Model.getText());
                prepare.setString(4, availableCameras_Price.getText());
                prepare.setString(5, (String) availableCameras_Status.getSelectionModel().getSelectedItem());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");
                prepare.setString(6, uri);
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(7, String.valueOf(sqlDate));
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                availableCameraShowListData();
                availableCameraClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCameraUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE camera SET brand = '" + availableCameras_Brand.getText() + "', model = '"
                + availableCameras_Model.getText() + "', status ='"
                + availableCameras_Status.getSelectionModel().getSelectedItem() + "', price = '"
                + availableCameras_Price.getText() + "', image = '" + uri
                + "' WHERE camera_id = '" + availableCameras_CameraID.getText() + "'";
        connect = database.connectDb();
        try {
            Alert alert;
            if (availableCameras_CameraID.getText().isEmpty()
                    || availableCameras_Brand.getText().isEmpty()
                    || availableCameras_Model.getText().isEmpty()
                    || availableCameras_Status.getSelectionModel().getSelectedItem() == null
                    || availableCameras_Price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Camera ID: " + availableCameras_CameraID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableCameraShowListData();
                    availableCameraClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableCameraDelete() {

        String sql = "DELETE FROM camera WHERE camera_id = '" + availableCameras_CameraID.getText() + "'";
        connect = database.connectDb();
        try {
            Alert alert;
            if (availableCameras_CameraID.getText().isEmpty()
                    || availableCameras_Brand.getText().isEmpty()
                    || availableCameras_Model.getText().isEmpty()
                    || availableCameras_Status.getSelectionModel().getSelectedItem() == null
                    || availableCameras_Price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Camera ID: " + availableCameras_CameraID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableCameraShowListData();
                    availableCameraClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableCameraClear() {
        availableCameras_CameraID.setText("");
        availableCameras_Brand.setText("");
        availableCameras_Model.setText("");
        availableCameras_Status.getSelectionModel().clearSelection();
        availableCameras_Price.setText("");
        getData.path = "";
        availableCameras_ImageView.setImage(null);

    }

    private String[] listStatus = {"Available", "Not Available"};

    public void availableCameraStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCameras_Status.setItems(listData);
    }

    public void availableCameraImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(mainForm.getScene().getWindow());
        if (file != null) {
            getData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 169, 176, false, true);
            availableCameras_ImageView.setImage(image);
        }
    }

    public ObservableList<cameraData> availableCameraListData() {
        ObservableList<cameraData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM camera";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            cameraData camD;

            while (result.next()) {
                camD = new cameraData(result.getInt("camera_id"),
                         result.getString("brand"),
                         result.getString("model"),
                         result.getDouble("price"),
                         result.getString("status"),
                         result.getString("image"),
                         result.getDate("date"));
                listData.add(camD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<cameraData> availableCameraList;

    public void availableCameraShowListData() {
        availableCameraList = availableCameraListData();

        availableCameras_col_CameraID.setCellValueFactory(new PropertyValueFactory<>("cameraId"));
        availableCameras_col_Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        availableCameras_col_Model.setCellValueFactory(new PropertyValueFactory<>("model"));
        availableCameras_col_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        availableCameras_col_Status.setCellValueFactory(new PropertyValueFactory<>("status"));

        availableCameras_TableView.setItems(availableCameraList);
    }

    public void availableCameraSearch() {

        FilteredList<cameraData> filter = new FilteredList<>(availableCameraList, e -> true);

        availableCameras_Search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateCameraData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateCameraData.getCameraId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCameraData.getBrand().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCameraData.getModel().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCameraData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCameraData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<cameraData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(availableCameras_TableView.comparatorProperty());
        availableCameras_TableView.setItems(sortList);

    }

    public void availableCameraSelect() {
        cameraData camD = availableCameras_TableView.getSelectionModel().getSelectedItem();
        int num = availableCameras_TableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < - 1) {
            return;
        }

        availableCameras_CameraID.setText(String.valueOf(camD.getCameraId()));
        availableCameras_Brand.setText(camD.getBrand());
        availableCameras_Model.setText(camD.getModel());
        availableCameras_Price.setText(String.valueOf(camD.getPrice()));

        getData.path = camD.getImage();

        String uri = "file:" + camD.getImage();

        image = new Image(uri, 169, 176, false, true);
        availableCameras_ImageView.setImage(image);

    }
    
    public void rentPay(){
        rentCustomerId();
        
        String sql = "INSERT INTO customer "
                + "(customer_id, firstName, lastName, gender, camera_id, brand"
                + ", model, total, date_rented, date_return) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(rentFirstName.getText().isEmpty()
                    || rentLastName.getText().isEmpty()
                    || rentGender.getSelectionModel().getSelectedItem() == null
                    || rentCameraID.getSelectionModel().getSelectedItem() == null
                    || rentBrand.getSelectionModel().getSelectedItem() == null
                    || rentModel.getSelectionModel().getSelectedItem() == null
                    || totalP == 0 || rentAmount.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
            }else{
                
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, rentFirstName.getText());
                    prepare.setString(3, rentLastName.getText());
                    prepare.setString(4, (String)rentGender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String)rentCameraID.getSelectionModel().getSelectedItem());
                    prepare.setString(6, (String)rentBrand.getSelectionModel().getSelectedItem());
                    prepare.setString(7, (String)rentModel.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(totalP));
                    prepare.setString(9, String.valueOf(rentDateRent.getValue()));
                    prepare.setString(10, String.valueOf(rentDateReturn.getValue()));

                    prepare.executeUpdate();
                    
                    String updateCamera = "UPDATE camera SET status = 'Not Available' WHERE camera_id = '"
                            +rentCameraID.getSelectionModel().getSelectedItem()+"'";
                    
                    statement = connect.createStatement();
                    statement.executeUpdate(updateCamera);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();
                    
                    rentCameraShowListData();
                    
                    rentClear();
                }
            }
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void rentClear(){
        totalP = 0;
        rentFirstName.setText("");
        rentLastName.setText("");
        rentGender.getSelectionModel().clearSelection();
        amount = 0;
        balance = 0;
        rentBalance.setText("RM0.0");
        rentTotal.setText("RM0.0");
        rentAmount.setText("");
        rentCameraID.getSelectionModel().clearSelection();
        rentBrand.getSelectionModel().clearSelection();
        rentModel.getSelectionModel().clearSelection();
    }
    
    private int customerId;
    public void rentCustomerId(){
        String sql = "SELECT id FROM customer";
        connect = database.connectDb(); 
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                // GET THE LAST id and add + 1
                customerId = result.getInt("id") + 1;
            }           
        }catch(Exception e){e.printStackTrace();}
    }
    
    private double amount;
    private double balance;
    public void rentAmount(){
        Alert alert;
        if(totalP == 0 || rentAmount.getText().isEmpty()){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
            
            rentAmount.setText("");
        }else{
            amount = Double.parseDouble(rentAmount.getText());
            
            if(amount >= totalP){
                balance = (amount - totalP);
                rentBalance.setText("RM" + String.valueOf(balance));
            }else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
                
                rentAmount.setText("");
            }
        }
    }

    public ObservableList<cameraData> rentCameraListData() {

        ObservableList<cameraData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM camera";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            cameraData camD;

            while (result.next()) {
                camD = new cameraData(result.getInt("camera_id"), result.getString("brand"),
                         result.getString("model"), result.getDouble("price"),
                         result.getString("status"),
                         result.getString("image"),
                         result.getDate("date"));

                listData.add(camD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private int countDate;
    public void rentDate(){
        Alert alert;
        if(rentCameraID.getSelectionModel().getSelectedItem() == null
                || rentBrand.getSelectionModel().getSelectedItem() == null
                || rentModel.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something wrong :3");
            alert.showAndWait();
            
            rentDateRent.setValue(null);
            rentDateReturn.setValue(null);
            
        }else{
            
            if(rentDateReturn.getValue().isAfter(rentDateRent.getValue())){
                // COUNT THE DAY
                countDate = rentDateReturn.getValue().compareTo(rentDateRent.getValue());
            }else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
                // INCREASE OF 1 DAY ONCE THE USER CLICKED THE SAME DAY 
                rentDateReturn.setValue(rentDateRent.getValue().plusDays(1));
            }
        }
    }
    
    private double totalP;
    public void rentDisplayTotal(){
        rentDate();
        double price = 0;
        String sql = "SELECT price, model FROM camera WHERE model = '"
                +rentModel.getSelectionModel().getSelectedItem()+"'";
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                price = result.getDouble("price");
            }
            totalP = (price * countDate);
            // DISPLAY TOTAL
            rentTotal.setText("RM" + String.valueOf(totalP));
        }catch(Exception e){e.printStackTrace();}
        
    }

    private String[] genderList = {"Male", "Female"};

    public void rentCameraGender() {

        List<String> listG = new ArrayList<>();

        for (String data : genderList) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);

        rentGender.setItems(listData);

    }

    public void rentCameraCameraId() {

        String sql = "SELECT * FROM camera WHERE status = 'Available'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("camera_id"));
            }

            rentCameraID.setItems(listData);

            rentCameraBrand();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void rentCameraBrand() {

        String sql = "SELECT * FROM camera WHERE camera_id = '"
                + rentCameraID.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("brand"));
            }

            rentBrand.setItems(listData);

            rentCameraModel();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void rentCameraModel() {

        String sql = "SELECT * FROM camera WHERE brand = '"
                + rentBrand.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("model"));
            }

            rentModel.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ObservableList<cameraData> rentCameraList;
    public void rentCameraShowListData() {
        rentCameraList = rentCameraListData();

        rentColCameraID.setCellValueFactory(new PropertyValueFactory<>("cameraId"));
        rentColBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        rentColModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        rentColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        rentColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        rentTableView.setItems(rentCameraList);
    }

    public void displayUsername() {
        String user = getData.username;
        // TO SET THE FIRST LETTER TO BIG LETTER
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                // HIDE YOUR DASHBOARD FORM
                btnLogout.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == btnHome) {
            homeForm.setVisible(true);
            availableCamerasForm.setVisible(false);
            rentForm.setVisible(false);

            btnHome.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            btnAvailableCameras.setStyle("-fx-background-color:transparent");
            btnRentCamera.setStyle("-fx-background-color:transparent");

            homeAvailableCameras();
            homeTotalIncome();
            homeTotalCustomers();
            homeIncomeChart();
            homeCustomerChart();
            
        } else if (event.getSource() == btnAvailableCameras) {
            homeForm.setVisible(false);
            availableCamerasForm.setVisible(true);
            rentForm.setVisible(false);

            btnAvailableCameras.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            btnHome.setStyle("-fx-background-color:transparent");
            btnRentCamera.setStyle("-fx-background-color:transparent");

            availableCameraShowListData();
            availableCameraStatusList();
            availableCameraSearch();

        } else if (event.getSource() == btnRentCamera) {
            homeForm.setVisible(false);
            availableCamerasForm.setVisible(false);
            rentForm.setVisible(true);

            btnRentCamera.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            btnHome.setStyle("-fx-background-color:transparent");
            btnAvailableCameras.setStyle("-fx-background-color:transparent");

            rentCameraShowListData();
            rentCameraCameraId();
            rentCameraBrand();
            rentCameraModel();
            rentCameraGender();

        }

    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();

        homeAvailableCameras();
        homeTotalIncome();
        homeTotalCustomers();
        homeIncomeChart();
        homeCustomerChart();
        
        availableCameraShowListData();
        availableCameraStatusList();
        availableCameraSearch();

        rentCameraShowListData();
        rentCameraCameraId();
        rentCameraBrand();
        rentCameraModel();
        rentCameraGender();

    }

}