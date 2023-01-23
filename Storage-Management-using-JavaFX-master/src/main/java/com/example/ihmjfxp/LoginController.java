package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public void LoginController (){}
    @FXML
    private Label lblErrors ;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btn_signin;
    @FXML
    private Button cancelButton;


    /// --
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LoginController() {
        con = mysqlconnect.ConnectDb();
    }

    @FXML
    public void cancelButtonOnAction(ActionEvent event) {
        Stage Stage = (Stage) cancelButton.getScene().getWindow();
        Stage.close();
    }


    //---------------------------------------------------------------------------
   public void btn_signin_clicked (  ActionEvent event) throws Exception {
        if (logIn().equals("Success")) {
            switch(istype()) {
                case "admin":
                    util.changeScene(event,"AdminPage.fxml","Storage Management App - IHM-JFX-P /Admin-Home");
                break;
                case "user":
                    util.changeScene(event,"SellerPage.fxml","Storage Management App - IHM-JFX-P /Seller Page");
                    break;
            }

        }

    }
    //---------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    private String logIn() {
        String status = "Success";
        String type   = " " ;
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM users Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting ..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
System.out.println(status);
        return status;
    }
    public String istype ( ) throws SQLException {
        return  (String )resultSet.getString("type")  ;
    }
    public String isname ( ) throws SQLException {
        return  (String )resultSet.getString("username")  ;
    }
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }


}

