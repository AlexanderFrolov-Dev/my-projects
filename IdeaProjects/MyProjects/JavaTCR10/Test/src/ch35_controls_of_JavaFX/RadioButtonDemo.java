package ch35_controls_of_JavaFX;

// Простой пример, демонстрирующий применение.
// кнопок-переключателей. Это JаvаFХ-приложение
// реагирует на события действия, генерируемые
// выбираемыми кнопками-переключателями.
// В нем демонстрируется также активизация
// кнопки-переключателя под управлением программы.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.beans.value.*;

public class RadioButtonDemo extends Application {

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Продемонстрировать кнопки-переключатели.");

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10 по
        // вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 220, 120);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку, извещающую о сделанном выборе.
        response = new Label("");

        // Создать кнопки-переключатели.
        RadioButton rbTrain = new RadioButton("Поезд");
        RadioButton rbCar = new RadioButton("Автомобиль");
        RadioButton rbPlane = new RadioButton("Самолет");

        // Создать группу кнопок-переключателей.
        ToggleGroup tg = new ToggleGroup();

        // Ввести каждую кнопку-переключатель в группу.
        rbTrain.setToggleGroup(tg);
        rbCar.setToggleGroup(tg);
        rbPlane.setToggleGroup(tg);

        // Обработать события действия от кнопок-переключателей.
        rbTrain.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Выбранным транспортным средством является поезд.");
            }
        });

        rbCar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Выбранным транспортным средством является автомобиль.");
            }
        });

        rbPlane.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Выбранным транспортным средством является самолет.");
            }
        });

        // Инициировать событие от первой выбранной
        // кнопки-переключателя. В итоге кнопка-переключатель
        // выбирается и наступает событие действия от
        // этой кнопки-переключателя.
        rbTrain.fire();

//        // Использовать приемник событий изменения,
//        // чтобы реагировать на изменения при выборе
//        // кнопок-переключателей из группы.
//        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            public void changed(ObservableValue<? extends Toggle> changed,
//                                Toggle oldVal, Toggle newVal) {
//
//                // Привести новое значение к типу RadioButton.
//                RadioButton rb = (RadioButton) newVal;
//
//                // Отобразить результат выбора.
//                response.setText("Выбран указанный вид транспорта " + rb.getText());
//            }
//        });
//
//        // Выбрать первую кнопку-переключатель, чтобы
//        // инициировать событие изменения в группе.
//        rbTrain.setSelected(true);

        // Ввести метку и кнопки-переключатели в граф сцены.
        rootNode.getChildren().addAll(rbTrain, rbCar, rbPlane, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
