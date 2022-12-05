package ch35_controls_of_JavaFX;

// Продемонстрировать применение комбинированного списка.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.collections.*;
import javafx.event.*;


public class ComboBoxDemo extends Application {

    ComboBox<String> cbTransport;
    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("ComboBox Demo");
        // "Демонстрация комбинированного списка."

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выполнить выравнивание по центру.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 280, 120);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку.
        response = new Label();

        // Создать список типа ObservableList из элементов,
        // предназначенных для комбинированного списка.
        ObservableList<String> transportTypes =
                FXCollections.observableArrayList( "Train", "Car", "Airplane" );

        // Создать комбинированный список.
        cbTransport = new ComboBox<String>(transportTypes);

        // Установить значение по умолчанию.
        cbTransport.setValue("Train");

//        cbTransport.setEditable(true);

        // Установить метку ответной реакции для
        // отображения результата выбора по умолчанию.
        response.setText("Selected Transport is " + cbTransport.getValue());
        // "Выбрано указанное транспортное средство."

        // Принимать события действия от
        // комбинированного списка.
        cbTransport.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Selected Transport is " + cbTransport.getValue());
                // "Выбрано указанное транспортное средство."
            }
        });

        // Ввести метку и комбинированный список в граф сцены.
        rootNode.getChildren().addAll(cbTransport, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}