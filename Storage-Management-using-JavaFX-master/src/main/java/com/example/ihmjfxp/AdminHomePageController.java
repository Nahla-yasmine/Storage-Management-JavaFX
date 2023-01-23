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
import com.example.ihmjfxp.* ;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomePageController implements Initializable  {

    @FXML
    Label lbladminname ;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
