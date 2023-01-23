package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import java.security.KeyStore;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.ihmjfxp.ProductModel ;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Product {

        Connection connection = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        //PreparedStatement stmt2 = null;
        ResultSet resultSet = null;

        public void product () { }

        private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory.getInstance().getConnection();
            return conn;

        }
//---------------------------------------------------------------------------


        public void AddP(ProductModel ProductModel)
        {
            System.out.print("Adding Product... \n");
            try {
                String queryString = "INSERT INTO product(product_id, product_name, price, category, discount , product_quantity , exp_date ,datec) VALUES(?,?,?,?,?,?,?,?)";
                connection  = getConnection();
                stmt = connection.prepareStatement(queryString);
                stmt.setInt(1, ProductModel.getProduct_id());
                stmt.setString(2, ProductModel.getProduct_name());
                stmt.setInt(3, ProductModel.getPrice());
                stmt.setString(4, ProductModel.getCategory());
                stmt.setInt(5, ProductModel.getDiscount());
                stmt.setInt(6, ProductModel.getProduct_quantity());
                stmt.setString(7, ProductModel.getExp_date());
                stmt.setString(8, ProductModel.getExp_date());
                stmt1=connection.prepareStatement("UPDATE storage_management.product SET datec = CAST(exp_date AS Date ) WHERE product_id <> 0") ;
                //stmt2 = connection.prepareStatement( "UPDATE storage_management.product SET datediff= DATEDIFF( datec, CURDATE() ) WHERE product_id != 0" );
                stmt.executeUpdate();
                System.out.println("Product Added !");
            } catch (SQLException e) {System.err.println(e.getMessage());}
        }

//---------------------------------------------------------------------------

        public void FindByName(String name)
        {
            System.out.print("Finding Product with the Name : " + name +" . . . \n");
            try {
                String queryString = "SELECT * FROM product WHERE Product_name=?";
                connection = getConnection();
                stmt = connection.prepareStatement(queryString);
                stmt.setString(1, name);
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    System.out.println("Product Number :  " + resultSet.getInt("product_id")
                            + " | Product Name : " + resultSet.getString("Product_name") + " | Price : "
                            + resultSet.getInt("Price") + " | Category : "
                            + resultSet.getString("category")+ " | Discount : "
                            + resultSet.getInt("discount") +" | Product quantity : "
                            + resultSet.getInt("Product_quantity") + " | Expiration Date : "
                            + resultSet.getString("Exp_date") );

                }}
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }

//---------------------------------------------------------------------------
        public void findAll() {

            try {
                String queryString = "SELECT * FROM product";
                connection = getConnection();
                stmt = connection.prepareStatement(queryString);
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    System.out.println(  "Product Number :  " + resultSet.getInt("product_id")
                            + " | Product Name : " + resultSet.getString("Product_name") + " | Price : "
                            + resultSet.getInt("Price") + " | Category : "
                            + resultSet.getString("category")+ " | Discount : "
                            + resultSet.getInt("discount") +" | Product quantity : "
                            + resultSet.getInt("Product_quantity") + " | Expiration Date : "
                            + resultSet.getString("Exp_date") );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
          public void updatep (int product_id , ProductModel product) {
            System.out.print("Updating Product... \n");
            try {
                String queryString = "UPDATE Product SET Product_name=? , Price=? , category=? , Discount=? , Product_quantity=?, Exp_date=?  WHERE product_id =?";
                connection = getConnection();
                stmt = connection.prepareStatement(queryString);
                stmt.setString(1, product.getProduct_name());
                stmt.setInt(2, product.getPrice());
                stmt.setString(3, product.getCategory());
                stmt.setInt(4, product.getDiscount());
                stmt.setInt(5, product.getProduct_quantity());
                stmt.setString(6, product.getExp_date());
                stmt.setInt(7, product.getProduct_id());
                stmt.executeUpdate();
                stmt1=connection.prepareStatement("UPDATE storage_management.product SET datec = CAST(exp_date AS Date ) WHERE product_id<> 0") ;

                System.out.println("Product Updated Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            } }
        //---------------------------------------------------------------------------
        public void updatequant (int product_id , int quant ) {
            System.out.print("Updating Product Quantity... \n");
            try {
                String queryString = "UPDATE Product SET  Product_quantity=? WHERE product_id =?";
                connection = getConnection();
                stmt = connection.prepareStatement(queryString);
                stmt.setInt(1,quant );
                stmt.setInt(2,product_id );
                stmt.executeUpdate();
                System.out.println("Product quantity Updated Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            } }
    //---------------------------------------------------------------------------
        public void delete_P (int product_id) {

            try {
                String queryString = "DELETE FROM product WHERE product_id=?";
                connection = getConnection();
                stmt = connection.prepareStatement(queryString);
                stmt.setInt(1, product_id);
                stmt.executeUpdate();
                System.out.println("Product deleted Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }}
//---------------------------------------------------------------------------
    }

