package ch34_introduction_to_JavaFX;

// Скелет JаvаFХ-приложения.

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class JavaFXSkel extends Application {

    public static void main(String[] args) {

        System.out.println("Зaпycк JаvаFХ-приложения.");

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод init().
    public void init() {
        System.out.println("B теле метода init().");
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        System.out.println("B теле метода start().");

        // Присвоить заголовок подмосткам.
        myStage.setTitle("JavaFX Skeleton.");
        // Скелет JаvаFХ-приложения.

        // Создать корневой узел. В данном случае используется
        // панель поточной компоновки, хотя возможны и другие
        // варианты компоновки.
        FlowPane rootNode = new FlowPane();

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 200);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Показать подмостки и сцену на них.
        myStage.show();

    }

    // Переопределить метод stop().
    public void stop() {
        System.out.println("B теле метода stop().");
    }
}
