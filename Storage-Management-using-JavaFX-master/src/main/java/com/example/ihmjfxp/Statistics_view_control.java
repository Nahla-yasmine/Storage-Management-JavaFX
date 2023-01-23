package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;

public class Statistics_view_control implements Initializable {
    @FXML
    BarChart  <?,?> barchart;
    @FXML
    CategoryAxis xAxis ;
    @FXML
    NumberAxis yAxis ;
    @FXML
    PieChart piechart ;
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


    public void initialize(URL url, ResourceBundle resourceBundle) {
        String dbUsername = "root";
        String dbPassword = " ";
        String dbURL = "jdbc:mysql://localhost:3306/xxxx";
        String queryString = "SELECT product_name , Product_quantity FROM product";

        try {
            Connection conn = getConnection(dbURL, dbUsername, dbPassword);
            Statement s = conn.createStatement();
            ResultSet rst = s.executeQuery(queryString);
            ArrayList<String> products = new ArrayList<String>() ;
            ArrayList<Integer> quantity = new ArrayList<Integer>() ;
           //ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
            while (rst.next()) {
                products.add(rst.getString(1)) ;
                quantity.add(rst.getInt(2)) ;
                //pieData.add(new PieChart.Data(rst.getString(1) ,rst.getInt(2) ) ) ;

        }
            //piechart.setData(pieData );
           // piechart.setTitle("The share of each product of the occupied storage");
        rst.close();

            //CategoryAxis xAxis = new CategoryAxis() ;
            xAxis.setLabel("Products");
            //NumberAxis yAxis = new NumberAxis() ;
            yAxis.setLabel("Quantity");

           // piechart = new PieChart(pieData) ;

           // BarChart barchart = new BarChart<>(xAxis,yAxis) ;
            XYChart.Series dataSeries1 = new XYChart.Series() ;
            dataSeries1.setName("Products & their Quantities Bar Chart");
             for(int i =0 ; i< products.size(); i++ ){

                 dataSeries1.getData().add(new XYChart.Data<>(products.get(i) , quantity.get(i)) ) ;
             }
             barchart.getData().add(dataSeries1) ;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





    }
}