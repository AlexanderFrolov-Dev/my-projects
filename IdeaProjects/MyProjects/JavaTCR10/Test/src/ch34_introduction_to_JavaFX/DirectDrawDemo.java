package ch34_introduction_to_JavaFX;

// Продемонстрировать рисование на холсте.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class DirectDrawDemo extends Application {

    GraphicsContext gc;

    Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.BLACK };
    int colorIdx = 0;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Рисование прямо на холсте.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        FlowPane rootNode = new FlowPane();

        // Расположить узлы по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 450, 450);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать холст.
        Canvas myCanvas = new Canvas(400, 400);

        // Получить графический контекст для холста.
        gc = myCanvas.getGraphicsContext2D();

        // Создать экранную кнопку.
        Button btnChangeColor = new Button("Change Color");

        // Обработать события действия от кнопки Change Color.
        btnChangeColor.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {

                // Задать цвет обводки и заливки.
                gc.setStroke(colors[colorIdx]);
                gc.setFill(colors[colorIdx]);

                // Перерисовать линию, текст и заполненный
                // прямоугольник новым цветом. При этом цвет
                // остальных узлов графа сцены останется
                // без изменения.
                gc.strokeLine(0, 0, 200, 200);
                gc.fillText("Это рисуется на холсте.", 60, 50);
                gc.fillRect(100, 320, 300, 40);

                // Изменить цвет.
                colorIdx++;
                if(colorIdx == colors.length) colorIdx= 0;
            }
        });

        // Нарисовать на холсте графические объекты
        // первоначально выводимые на экран.
        gc.strokeLine(0, 0, 200, 200);
        gc.strokeOval(100, 100, 200, 200);
        gc.strokeRect(0, 200, 50, 200);
        gc.fillOval(0, 0, 20, 20);
        gc.fillRect(100, 320, 300, 40);

        // Задать шрифт размером 20 и воспроизвести текст.
        gc.setFont(new Font(20));
        gc.fillText("Это рисуется на холсте.", 60, 50);

        // Ввести холст и кнопку в граф сцены.
        rootNode.getChildren().addAll(myCanvas, btnChangeColor);

        // показать подмостки и сцену на них.
        myStage.show();
    }
}
