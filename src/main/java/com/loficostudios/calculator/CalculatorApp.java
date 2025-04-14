package com.loficostudios.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    public CalculatorApp() {
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        var controller = new CalculatorController();
        var scene = new Scene(controller.getView(), 300, 350);

        var css = getClass().getResource("/style.css");
        if (css != null)
            scene.getStylesheets().add(css.toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}