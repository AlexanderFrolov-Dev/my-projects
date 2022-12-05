package ch35_controls_of_JavaFX;

// Продемонстрировать применение переключателя.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class ToggleButtonDemo extends Application {

    ToggleButton tbOnOff;
    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Продемонстрировать переключатель.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10 по
        // вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 220, 120);

        // Установить с цену на подмостках.
        myStage.setScene(myScene);

        // Создать метку.
        response = new Label("Нажать кнопку.");

        // Создать переключатель.
        tbOnOff = new ToggleButton("Включить/Выключить");

        // Обработать события действия от переключателя.
        tbOnOff.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if(tbOnOff.isSelected()) {
                    response.setText("Переключатель нажат.");
                }
                else {
                    response.setText("Переключатель отпущен.");
                }
            }
        });

        // Ввести метку и переключатель в граф сцены.
        rootNode.getChildren().addAll(tbOnOff, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
