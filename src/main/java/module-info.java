module com.diploma.nuop_diploma {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;

    opens com.diploma.nuop_diploma to javafx.fxml;
    exports com.diploma.nuop_diploma;
    exports com.diploma.controllers;
    opens com.diploma.controllers to javafx.fxml;
    opens com.diploma.models to javafx.base;
}