package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class util {

    public static void changeScene (ActionEvent event, String fxml, String title) {
        Parent root=null;
        try {
            FXMLLoader loader =new FXMLLoader(util.class.getResource(fxml));
            root=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();

    }
}
