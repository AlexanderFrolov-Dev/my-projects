package ch34_introduction_to_JavaFX;

// Продемонстрировать применение элемента управления меткой в JavaFX.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class JavaFXLabelDemo extends Application {

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Продемонстрировать метку в JavaFX.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        FlowPane rootNode = new FlowPane();

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 200);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку.
        Label myLabel = new Label("Это метка в JavaFX.");

        // Ввести метку в граф сцены.
        rootNode.getChildren().add(myLabel);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
