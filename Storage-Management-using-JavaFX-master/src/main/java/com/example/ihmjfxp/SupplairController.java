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

public class SupplairController implements Initializable {


    @FXML
    private TableView<suppleir> table_suppleir;

    @FXML
    private TableColumn<suppleir, Integer> col_id;

    @FXML
    private TableColumn<suppleir, String>col_firstname;

    @FXML
    private TableColumn<suppleir, String> col_lastname;

    @FXML
    private TableColumn<suppleir, String> col_email;

    @FXML
    private TableColumn<suppleir, String> col_phone;

    @FXML
    private TextField txt_firstname;

    @FXML
    private TextField txt_lastname;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_phone;

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

    ObservableList<suppleir> listM;
    ObservableList<suppleir> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public static ObservableList<suppleir> getDatasuppleir(){
        Connection conn = mysqlconnect.ConnectDb();
        ObservableList<suppleir> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from supplier");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new suppleir(Integer.parseInt(rs.getString("id")),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("phone")));
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return list;
    }

    public void Add_Suppleir (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into supplier (firstname,lastname,email,phone)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_firstname.getText());
            pst.setString(2, txt_lastname.getText());
            pst.setString(3, txt_email.getText());
            pst.setString(4, txt_phone.getText());
            pst.execute();

            System.out.println("Supplier Added successfully ");
            UpdateTable();
            search_Suppleir ();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }


    //////// methode select suppleir ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_suppleir.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_firstname.setText(col_firstname.getCellData(index).toString());
        txt_lastname.setText(col_lastname.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
        txt_phone.setText(col_phone.getCellData(index).toString());

    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_firstname.getText();
            String value3 = txt_lastname.getText();
            String value4 = txt_email.getText();
            String value5 = txt_phone.getText();
            String sql = "update supplier set id= '"+value1+"',firstname= '"+value2+"',lastname= '"+
                    value3+"',email= '"+value4+"',phone= '"+value5+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            System.out.println("Supplier updated Successfully ");
            UpdateTable();
            search_Suppleir();
            txt_id.setText("");
            txt_firstname.setText("");
            txt_lastname.setText("");
            txt_email.setText("");
            txt_phone.setText("");

        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from supplier where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            System.out.println("Supplier deleted Successfully");
            UpdateTable();
            search_Suppleir();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<suppleir,Integer>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<suppleir,String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<suppleir,String>("lastname"));
        col_email.setCellValueFactory(new PropertyValueFactory<suppleir,String>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<suppleir,String>("phone"));

        listM = getDatasuppleir();
        table_suppleir.setItems(listM);
    }




    @FXML
    void  search_Suppleir() {
        col_id.setCellValueFactory(new PropertyValueFactory<suppleir,Integer>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<suppleir,String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<suppleir,String>("lastname"));
        col_email.setCellValueFactory(new PropertyValueFactory<suppleir,String>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<suppleir,String>("phone"));

        dataList = getDatasuppleir();
        table_suppleir.setItems(dataList);
        FilteredList<suppleir> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches firstname
                } else if (person.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches lastname
                }else if (person.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches phone
                }
                else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<suppleir> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_suppleir.comparatorProperty());
        table_suppleir.setItems(sortedData);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_Suppleir();
        // Code Source in description
    }

}
