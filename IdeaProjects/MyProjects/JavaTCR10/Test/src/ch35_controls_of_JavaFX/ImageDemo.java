package ch35_controls_of_JavaFX;

// Загрузить и воспроизвести изображение.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.*;


public class ImageDemo extends Application {

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Display an Image");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        FlowPane rootNode = new FlowPane();

        // Выполнить выравнивание по центру.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 200);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать объект изображения.
        Image hourglass = new Image("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\HourGlass.png");

        // Создать представление этого изображения.
        ImageView hourglassIV = new ImageView(hourglass);

        // Ввести изображение в граф сцены.
        rootNode.getChildren().add(hourglassIV);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
