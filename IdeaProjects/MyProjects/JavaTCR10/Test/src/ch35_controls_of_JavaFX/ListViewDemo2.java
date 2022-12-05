package ch35_controls_of_JavaFX;

// Продемонстрировать применение представления списка.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.beans.value.*;
import javafx.collections.*;

public class ListViewDemo2 extends Application {

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("ListView Demo");
        // "Демонстрация представления списка."

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 200, 120);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку.
        response = new Label("Select Transport Type");
        // "Выбрать вид транспортного средства."

        // Создать список типа ObservableList из
        // элементов для представления списка.
        ObservableList<String> transportTypes =
                FXCollections.observableArrayList( "Train",
                        "Car",
                        "Airplane",
                        "Bicycle",
                        "Walking" );

        // Создать представление списка.
        ListView<String> lvTransport = new ListView<String>(transportTypes);

        // Задать предпочтительную высоту и ширину
        // представления списка.
        lvTransport.setPrefSize(80, 80);

        // Получить модель выбора для представления списка.
        MultipleSelectionModel<String> lvSelModel =
                lvTransport.getSelectionModel();

        lvTransport.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Ввести приемник событий изменения,
        // чтобы реагировать на выбор элементов
        // в представлении списка.
        lvSelModel.selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> changed,
                                        String oldVal, String newVal) {

                        String selItems = "";
                        ObservableList<String> selected =
                                lvTransport.getSelectionModel().getSelectedItems();

                        // Display the selections.
                        for(int i=0; i < selected.size(); i++)
                            selItems += "\n      " + selected.get(i);

                        response.setText("All transports selected: " + selItems);
                    }
                });

        // Ввести метку и представление списка в граф сцены.
        rootNode.getChildren().addAll(lvTransport, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}

