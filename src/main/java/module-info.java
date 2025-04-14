module javafx.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.loficostudios.calculator to javafx.fxml;
    exports com.loficostudios.calculator;
}