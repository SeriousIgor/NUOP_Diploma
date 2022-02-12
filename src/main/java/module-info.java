module com.diploma.nuop_diploma {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.diploma.nuop_diploma to javafx.fxml;
    exports com.diploma.nuop_diploma;
    exports com.diploma.controllers;
    opens com.diploma.controllers to javafx.fxml;
}