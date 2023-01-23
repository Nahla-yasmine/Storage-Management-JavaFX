package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Storage Management App - IHM-JFX-P");
            stage.setScene(scene); //stage.initStyle(StageStyle.UTILITY);
        /*stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                stage.setMaximized(false);
        });*/
            stage.setResizable(false);
            stage.show();
        } catch (IOException e){System.err.println("Error" + e);}


    }
    public static void main(String[] args) {
        launch();
    }
}