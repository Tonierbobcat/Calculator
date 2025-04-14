package com.loficostudios.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private Operation cachedOperation;
    private Double cachedNumber;

    private boolean clear;

    public CalculatorApp() {
    }

    @Override
    public void start(Stage stage) {
        var root = new BorderPane();
        var scene = new Scene(root, 300, 350);
        var grid = new GridPane();

        stage.setResizable(false);

        var field = new TextField();

        var css = getClass().getResource("/style.css");
        if (css != null)
            scene.getStylesheets().add(css.toExternalForm());

        createNumberButtons(grid, field);

        createOperationButtons(grid, field);

        root.setCenter(field);

        root.setBottom(grid);

        stage.setScene(scene);
        stage.show();
    }

    private void createNumberButtons(GridPane grid, TextField field) {
        int[][] layout = {
                {7, 8, 9},
                {4, 5, 6},
                {1, 2, 3}
        };

        for (int rows = 0; rows < layout.length; rows++) {
            for (int col = 0; col < layout[rows].length; col++) {
                grid.add(createNumberButton(layout[rows][col], field), col,rows);
            }
        }

        grid.add(createNumberButton(0, field), 1,3);
    }

    private void createOperationButtons(GridPane grid, TextField field) {
        Operation[] layout = {
                Operation.ADD,
                Operation.SUBTRACT,
                Operation.MULTIPLY,
                Operation.DIVIDE,
                Operation.EQUAL
        };

        for (int i = 0; i < layout.length; i++) {
            grid.add(createOperationButton(layout[i], field), 4, i);
        }
    }

    public void clear() {
        clear = true;
        cachedOperation = null;
        cachedNumber = null;
    }

    public double handleOperation(Operation operation, double a, double b) {
        return switch (operation) {
            case ADD -> a + b;
            case DIVIDE -> a / b;
            case MULTIPLY -> a * b;
            case SUBTRACT -> a - b;
            default -> 0.0;
        };
    }

    public Button createNumberButton(int i, TextField field) {
        var button = new Button("" + i);
        button.setOnAction(event -> {
            if (clear) {
                clear = false;
                field.setText("");
            }
            var isZero = (field.getText().equals("0") || field.getText().equals("0.0"));
            if (i == 0 && isZero)
                return;
            if (isZero) {
                field.setText("" + i);
            }
            field.setText(field.getText() + ("" + i));
        });
        return button;
    }

    public Button createOperationButton(Operation operation, TextField field) {
        var button = new Button("" + operation.symbol);
        button.setOnAction(event -> {
            Double number = null;
            try {
                number = Double.parseDouble(field.getText());
            } catch (NumberFormatException ignore) {
            }

            if (number != null && cachedNumber != null && cachedOperation != null) {
                cachedNumber = handleOperation(cachedOperation, cachedNumber, number);

                if (operation == Operation.EQUAL) {
                    field.setText("" + cachedNumber);
                    clear();
                } else {
                    field.setText("");
                    field.setPromptText("" + cachedNumber + operation.symbol);
                    cachedOperation = operation;
                }

                return;
            }

            if (operation == Operation.EQUAL)
                return;

            if (number != null) {
                cachedNumber = number;
                cachedOperation = operation;
                System.out.println(cachedNumber + " " + operation);
                field.setText("");
                field.setPromptText("" + cachedNumber + operation.symbol);
            } else {
                cachedOperation = operation;
                field.setText("");
                field.setPromptText("" + cachedNumber + operation.symbol);
            }
        });
        return button;
    }

    public enum Operation {
        EQUAL('='),
        ADD('+'),
        SUBTRACT('-'),
        MULTIPLY('*'),
        DIVIDE('/');

        private final char symbol;

        Operation(char symbol) {
            this.symbol = symbol;
        }

        public char symbol() {
            return symbol;
        }
    }
}