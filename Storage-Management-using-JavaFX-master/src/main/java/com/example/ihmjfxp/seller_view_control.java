package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;

    public  class seller_view_control implements  Initializable {
        //---------------------------------------------------------------------------
        @FXML
        Label  labelaffichagemessages ;
        @FXML
        TextField txtspcode;
        @FXML
        Spinner spquant;
        @FXML
        Button sbtnshowpl, sbtneditp ,logout_btn ,btn_logout;

        //---------------------------------------------------------------------------
        @FXML
        TableView<ProductModel> tables;
        @FXML
        TableColumn<ProductModel, Integer> tbsid;
        @FXML
        TableColumn<ProductModel, String > tbsname;
        @FXML
        TableColumn<ProductModel, Integer> tbsprice;
        @FXML
        TableColumn<ProductModel, String> tbscat;
        @FXML
        TableColumn<ProductModel, Integer> tbsdis;
        @FXML
        TableColumn<ProductModel, Integer> tbsquant;
        @FXML
        TableColumn<ProductModel, String> tbsexp;

        //---------------------------------------------------------------------------
        public void btn_logout_clicked ( ActionEvent event) throws Exception {

            util.changeScene(event,"LoginPage.fxml","Storage Management App - IHM-JFX-P /Login");

        }
        //---------------------------------------------------------------------------
//---------------------------------------------------------------------------

        public void Showproductlists_clicked (Event e) throws Exception {
            tables.setVisible(true);
        }

//---------------------------------------------------------------------------

        public void editproquants_clicked (Event e) throws Exception {

            Product prdtuq = new Product();
            prdtuq.updatequant(Integer.parseInt(txtspcode.getText()), Integer.parseInt(spquant.getValue().toString() ) ) ;
            labelaffichagemessages.setText("Product quantity edited in the DB successfully !");
            if (labelaffichagemessages.isVisible() != true) {
                labelaffichagemessages.setVisible(true);
            }
        }
   //---------------------------------------------------------------------------
 /*  public void logoutbtn_clicked (Event e) throws Exception {

      Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml")) ;
      Stage window =(Stage) logout_btn.getScene().getWindow() ;
      window.setScene(new Scene(root)) ;
   } */
//---------------------------------------------------------------------------
            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                String dbUsername = "root";
                String dbPassword = "";
                String dbURL = "jdbc:mysql://localhost:3306/xxxx";
                ObservableList<Object> data = null;
                try {
                    Connection conn = getConnection(dbURL, dbUsername, dbPassword);
                } catch (SQLException ex) {
                    System.err.println("Error" + ex);
                }


            //------------------------------------------------TO SHOW THE PRODUCTS TABLE ---------------------------
            ObservableList<ProductModel> listview = FXCollections.observableArrayList();
            tbsid.setCellValueFactory(new PropertyValueFactory<>("product_id"));
            tbsname.setCellValueFactory(new PropertyValueFactory<>("product_name"));
            tbsprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            tbscat.setCellValueFactory(new PropertyValueFactory<>("category"));
            tbsdis.setCellValueFactory(new PropertyValueFactory<>("discount"));
            tbsquant.setCellValueFactory(new PropertyValueFactory<>("product_quantity"));
            tbsexp.setCellValueFactory(new PropertyValueFactory<>("exp_date"));


            String sql = "SELECT * FROM product";
            try {
                Connection conn = getConnection(dbURL, dbUsername, dbPassword);
                Statement s = conn.createStatement();
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    listview.add(new ProductModel(
                            r.getInt("product_id"),
                            r.getString("product_name"),
                            r.getInt("discount"),
                            r.getInt("price"),
                            r.getString("category"),
                            r.getInt("product_quantity"),
                            r.getString("exp_date")));
                }
                tables.setItems(listview);
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
//---------------------------------------------------------------------------
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
                valueFactory.setValue(10);
                spquant.setValueFactory(valueFactory);
                spquant.getValueFactory().wrapAroundProperty().set(true);



//---------------------------------------------------------------------------
        }
    }
