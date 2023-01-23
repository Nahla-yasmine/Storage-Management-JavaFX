module com.example.ihmjfxp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ihmjfxp to javafx.fxml;
    exports com.example.ihmjfxp;
}