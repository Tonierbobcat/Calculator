module javafx.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.loficostudios.javafxtest to javafx.fxml;
    exports com.loficostudios.javafxtest;
}