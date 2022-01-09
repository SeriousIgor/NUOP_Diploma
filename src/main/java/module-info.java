module com.diploma.nuop_diploma {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.diploma.nuop_diploma to javafx.fxml;
    exports com.diploma.nuop_diploma;
}