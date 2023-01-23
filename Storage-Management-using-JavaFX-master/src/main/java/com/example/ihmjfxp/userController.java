package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class userController implements Initializable {

    @FXML
    private TableView<users> table_users;

    @FXML
    private TableColumn<users, Integer> col_id;

    @FXML
    private TableColumn<users, String> col_username;

    @FXML
    private TableColumn<users, String> col_password;

    @FXML
    private TableColumn<users, String> col_email;

    @FXML
    private TableColumn<users, String> col_type;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField filterField;
    @FXML
    Button btn_product ,btn_supplier ,btn_stats ,btn_empset ,btn_logout , btn_adm_home;
    //---------------------------------------------------------------------------
    public void btn_product_clicked (  ActionEvent event) throws Exception {
        util.changeScene(event,"ProductsPage.fxml","Storage Management App - IHM-JFX-P /Products");
        btn_product.requestFocus();
    }
    //---------------------------------------------------------------------------
    public void btn_supplier_clicked (  ActionEvent event) throws Exception {

        util.changeScene(event,"SupplierPage.fxml","Storage Management App - IHM-JFX-P /Suppliers");
        btn_supplier.requestFocus();
    }
    //---------------------------------------------------------------------------
    public void btn_stats_clicked ( ActionEvent event) throws Exception {

        util.changeScene(event,"StatisticsPage.fxml","Storage Management App - IHM-JFX-P /Statistics");
        btn_stats.requestFocus();
    }
    //---------------------------------------------------------------------------
    public void btn_empset_clicked ( ActionEvent event) throws Exception {

        util.changeScene(event,"EmployeesSettings.fxml","Storage Management App - IHM-JFX-P /Employees Settings");
        btn_empset.requestFocus();
    }
    //---------------------------------------------------------------------------
    public void btn_logout_clicked ( ActionEvent event) throws Exception {

        util.changeScene(event,"LoginPage.fxml","Storage Management App - IHM-JFX-P /Login");

    }
    //---------------------------------------------------------------------------
    public void btn_adm_home_clicked ( ActionEvent event) throws Exception {

        util.changeScene(event,"AdminPage.fxml","Storage Management App - IHM-JFX-P /Admin-Home");
        btn_adm_home.requestFocus();
    }
    //---------------------------------------------------------------------------

    ObservableList<users> listM;
    ObservableList<users> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public static ObservableList<users> getDatausers(){
        Connection conn = mysqlconnect.ConnectDb();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new users(
                        Integer.parseInt(rs.getString("user_id")),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("type")));
            }
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
        return list;
    }

    public void Add_users (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into users (username,password,email,type)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, txt_email.getText());
            pst.setString(4, txt_type.getText());
            pst.execute();

            System.out.println("User Added Successfully ");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
        txt_type.setText(col_type.getCellData(index).toString());

    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_password.getText();
            String value4 = txt_email.getText();
            String value5 = txt_type.getText();
            String sql = "update users set user_id= '"+value1+"',username= '"+value2+"',password= '"+
                    value3+"',email= '"+value4+"',type= '"+value5+"' where user_id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            System.out.println("User updated Successfully ");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            System.err.println("Error" + e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from users where user_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            System.out.println("user deleted Successfully");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            System.err.println("Error" + e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));

        listM = getDatausers();
        table_users.setItems(listM);
    }




    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));

        dataList = getDatausers();
        table_users.setItems(dataList);
        FilteredList<users> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
        // Code Source in description
    }

}
