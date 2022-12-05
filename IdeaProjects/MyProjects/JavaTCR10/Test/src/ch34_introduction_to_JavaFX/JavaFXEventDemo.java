package ch34_introduction_to_JavaFX;

// Продемонстрировать применение экранных кнопок
// и обработку событий в JavaFX.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class JavaFXEventDemo extends Application {

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Продемонстрировать экранные кнопки и события в JavaFX.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // Установить промежутки между элементами
        // управления по горизонтали и по вертикали
        // равными 10.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 100);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку.
        response = new Label("Нажать кнопку.");

        // Создать две экранные кнопки.
        Button btnAlpha = new Button("Alpha");
        Button btnBeta = new Button("Beta");

        // Обработать события действия от кнопки Alpha.
        btnAlpha.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Нажата кнопка Alpha.");
            }
        });
//        btnAlpha.setOnAction( (ae) ->
//                response.setText("Alpha was pressed.")
//        );

        // Обработать события действия от кнопки Beta.
        btnBeta.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Нажата кнопка Beta.");
            }
        });

        // Ввести метку и экранные кнопки в граф сцены.
        rootNode.getChildren().addAll(btnAlpha, btnBeta, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
