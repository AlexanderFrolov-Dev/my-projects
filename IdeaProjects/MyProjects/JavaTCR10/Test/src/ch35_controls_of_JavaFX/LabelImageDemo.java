package ch35_controls_of_JavaFX;

// Продемонстрировать изображение на месте метки.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.*;

public class LabelImageDemo extends Application {

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Использовать изображение в метке.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        FlowPane rootNode = new FlowPane();

        // Выполнить выравнивание по центру.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 200);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать представление указанного изображения.
        ImageView hourglassIV = new ImageView("F:\\Projects\\Frolov\\LEARNING\\IdeaProjects\\MyProjects\\JavaTCR10\\HourGlass.png");

        // Создать метку, содержащую изображение и текст.
        Label hourglassLabel = new Label("Hourglass", hourglassIV);

        // Ввести метку в граф сцены.
        rootNode.getChildren().add(hourglassLabel);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
