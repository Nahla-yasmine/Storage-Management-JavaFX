package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView ;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;

public  class product_view_control implements  Initializable {
    //---------------------------------------------------------------------------
    @FXML
    Label labelexpdate, labelstockrapture, labelaffichagemessage, labelproductlist;
    @FXML
    TextField txtpname, txtpcode, txtprice ,txtdiscount;
    @FXML
    ComboBox category;
    @FXML
    DatePicker expdate;
    @FXML
    Spinner pquant;
    @FXML
    Button btnaddp, btneditp, btndelp, btneditpq, btnshowpl;
    @FXML
    Button btn_product ,btn_supplier ,btn_stats ,btn_empset ,btn_logout, btn_adm_home ;

    //---------------------------------------------------------------------------
    @FXML
    TableView<ProductModel> table;
    @FXML
    TableColumn<ProductModel, Integer> tbid;
    @FXML
    TableColumn<ProductModel, String > tbname;
    @FXML
    TableColumn<ProductModel, Integer> tbprice;
    @FXML
    TableColumn<ProductModel, String> tbcat;
    @FXML
    TableColumn<ProductModel, Integer> tbdis;
    @FXML
    TableColumn<ProductModel, Integer> tbquant;
    @FXML
    TableColumn<ProductModel, String> tbexp;
    //---------------------------------------------------------------------------
    @FXML
    TableView<ProductModel> tableexp;
    @FXML
    TableColumn<ProductModel, Integer> tb1id;
    @FXML
    TableColumn<ProductModel, String > tb1name;
    @FXML
    TableColumn<ProductModel, Integer> tb1exp;
    //---------------------------------------------------------------------------
    @FXML
    TableView<ProductModel> tablerapstock;
    @FXML
    TableColumn<ProductModel, Integer> tb2id;
    @FXML
    TableColumn<ProductModel, String > tb2name;
    @FXML
    TableColumn<ProductModel, Integer> tb2quant;

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
    public void addproduct_clicked(Event e) throws Exception {
        ProductModel Produit = new ProductModel(
                Integer.parseInt(txtpcode.getText()),
                txtpname.getText(),
                Integer.parseInt(txtdiscount.getText()),
                Integer.parseInt(txtprice.getText()),
                (String)  category.getValue(),
                Integer.parseInt(pquant.getValue().toString()),
                expdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd") ) ) ;

        Product prdt = new Product();
        prdt.AddP(Produit);
        labelaffichagemessage.setText( "Product added to the DB successfully !" );
        if (labelaffichagemessage.isVisible() != true ) {
            labelaffichagemessage.setVisible(true);
        }

    }

//---------------------------------------------------------------------------

    public void Showproductlist_clicked (Event e) throws Exception {
       table.setVisible(true);table.refresh();
    }
//---------------------------------------------------------------------------
    public void deleteproduct_clicked (Event e) throws Exception {

        Product prdtd = new Product();
        prdtd.delete_P(Integer.parseInt(txtpcode.getText()));
        labelaffichagemessage.setText( "Product deleted from the DB successfully !" );
        if (labelaffichagemessage.isVisible() != true ){
            labelaffichagemessage.setVisible(true); }

    }
//---------------------------------------------------------------------------

    public void editproduct_clicked (Event e) throws Exception {

        ProductModel proup = new ProductModel(
                Integer.parseInt(txtpcode.getText()),
                txtpname.getText(),
                Integer.parseInt(txtdiscount.getText()),
                Integer.parseInt(txtprice.getText()),
                (String) category.getValue(),
                Integer.parseInt(pquant.getValue().toString()),
                expdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Product prdte = new Product();
        prdte.updatep(Integer.parseInt(txtpcode.getText()), proup);
        labelaffichagemessage.setText("Product Updated in the DB successfully !");
        if (labelaffichagemessage.isVisible() != true) {
            labelaffichagemessage.setVisible(true);
        }
    }
//---------------------------------------------------------------------------

    public void editproquant_clicked (Event e) throws Exception {

        Product prdtuq = new Product();
        prdtuq.updatequant(Integer.parseInt(txtpcode.getText()), Integer.parseInt(pquant.getValue().toString() ) ) ;
        labelaffichagemessage.setText("Product quantity edited in the DB successfully !");
        if (labelaffichagemessage.isVisible() != true) {
            labelaffichagemessage.setVisible(true);
        }
    }

//---------------------------------imported categories for the category combobox ------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/xxxx";
        ObservableList<Object> data = null;
        try {

            Connection conn = getConnection(dbURL, dbUsername, dbPassword);
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT categorie_name FROM categories;");
            while (rs.next()) {
                //get stringcategory name from db
                data.add(rs.getString("categorie_name"));
            }
            //----------------------------------------- Set the quantity spinner value ----------------------------------
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
            valueFactory.setValue(10);
            pquant.setValueFactory(valueFactory);
            pquant.getValueFactory().wrapAroundProperty().set(true);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        category.setItems(null);
        category.setItems(data);
        //------------------------------------------------TO SHOW THE PRODUCTS TABLE ---------------------------

            ObservableList<ProductModel> listview = FXCollections.observableArrayList();
            tbid.setCellValueFactory(new PropertyValueFactory<>("product_id"));
            tbname.setCellValueFactory(new PropertyValueFactory<>("product_name"));
            tbprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            tbcat.setCellValueFactory(new PropertyValueFactory<>("category"));
            tbdis.setCellValueFactory(new PropertyValueFactory<>("discount"));
            tbquant.setCellValueFactory(new PropertyValueFactory<>("product_quantity"));
            tbexp.setCellValueFactory(new PropertyValueFactory<>("exp_date"));


            String sql = "SELECT * FROM product ";//ORDER BY exp_date
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
                table.setItems(listview);
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }

        //---------------------------------------TO SHOW EXPIRING PRODUCTS------------------------------------
       ObservableList<ProductModel> proexp = FXCollections.observableArrayList();
        tb1id.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tb1name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        tb1exp.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
     // String ma = "UPDATE product SET datediff= DATEDIFF( datec, CURDATE() ) WHERE product_id != 0;";
        String sql1 = " SELECT* FROM product WHERE datediff < 10 ORDER BY datec; ";
        try {
            Connection conn = getConnection(dbURL, dbUsername, dbPassword);
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql1);
            while (r.next()) {
                proexp.add(new ProductModel(
                        r.getInt("product_id"),
                        r.getString("product_name"),
                        r.getInt("discount"),
                        r.getInt("price"),
                        r.getString("category"),
                        r.getInt("product_quantity"),
                        r.getString("exp_date")));

            }
            tableexp.setItems(proexp);
        } catch (SQLException ex) {
            System.err.println("Error   " + ex);
        }

        //----------------------------------TO SHOW STOCK RAPTURE PRODUCTS-----------------------------------------
        ObservableList<ProductModel> prorap = FXCollections.observableArrayList();
        tb2id.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tb2name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        tb2quant.setCellValueFactory(new PropertyValueFactory<>("product_quantity"));

        String sql3 = "SELECT * FROM product WHERE (product_quantity < 10)";
        try {
            Connection conn = getConnection(dbURL, dbUsername, dbPassword);
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql3);
            while (r.next()) {
                prorap.add(new ProductModel(
                        r.getInt("product_id"),
                        r.getString("product_name"),
                        r.getInt("discount"),
                        r.getInt("price"),
                        r.getString("category"),
                        r.getInt("product_quantity"),
                        r.getString("exp_date")));
            }
            tablerapstock.setItems(prorap);
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }


    }
}
