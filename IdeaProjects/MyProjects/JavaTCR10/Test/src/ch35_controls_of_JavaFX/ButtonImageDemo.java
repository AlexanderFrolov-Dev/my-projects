package ch35_controls_of_JavaFX;

// Применить изображение в экранной кнопке.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.image.*;

public class ButtonImageDemo extends Application {

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Использовать изображения в кнопках.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10 по
        // вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 250, 450);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку.
        response = new Label("Нажать кнопку.");

        // Создать две экранные кнопки с текстовыми надписями
        // и соответствующими изображениями часов.
        Button btnHourglass = new Button("Песочные часы.",
                new ImageView("F:\\Projects\\Frolov\\LEARNING\\IdeaProjects\\MyProjects\\JavaTCR10\\HourGlass.png"));
//        Button btnHourglass = new Button("",
//                new ImageView("F:\\Projects\\Frolov\\LEARNING\\IdeaProjects\\MyProjects\\JavaTCR10\\HourGlass.png"));
        Button btnAnalogClock = new    Button("Аналоговые часы.",
                new ImageView("F:\\Projects\\Frolov\\LEARNING\\IdeaProjects\\MyProjects\\JavaTCR10\\Analog.png"));
//        Button btnAnalogClock = new Button("",
//                new ImageView("F:\\Projects\\Frolov\\LEARNING\\IdeaProjects\\MyProjects\\JavaTCR10\\Analog.png"));

        // Расположить текст под изображением.
        btnHourglass.setContentDisplay(ContentDisplay.TOP);
        btnAnalogClock.setContentDisplay(ContentDisplay.TOP);
//        btnHourglass.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//        btnAnalogClock.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        // Обработать события действия от экранной кнопки
        // с изображением песочных часов.
        btnHourglass.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Нажата кнопка с изображением песочных часов.");
            }
        });

        // Обработать события действия от экранной кнопки
        // с изображением аналоговых часов.
        btnAnalogClock.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Нажата кнопка с изображением аналоговых часов.");
            }
        });

        // Ввести метку и экранные кнопки в граф сцены.
        rootNode.getChildren().addAll(btnHourglass, btnAnalogClock, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
