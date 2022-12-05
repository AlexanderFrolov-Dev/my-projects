package ch35_controls_of_JavaFX;

// В этом примере применения кнопок-переключателей
// демонстрируется получение кнопки-переключателя,
// выбранной в текущий момент из группы, под управлением
// программы, когда в этом возникает потребность, вместо
// реагирования на события действия или изменения.
//
// В данном примере события, связанные с
// кнопками-переключателями, не обрабатываются.
// Вместо этого просто получается выбранная в
// данный момент кнопка-переключатель, когда
// нажимается экранная кнопка Confirm Transport Selection.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class RadioButtonDemo2 extends Application {

    Label response;
    ToggleGroup tg;

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
        // В данном случае - с промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 200, 140);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать две метки.
        Label choose = new Label("    Выбор транспортного средства    ");
        response = new Label("Выбор транспортного средства не подтвержден.");

        // Создать экранную кнопку для подтверждения
        // выбора транспортного средства.
        Button btnConfirm = new Button("Подтвердить выбор транспортного средства.");

        // Создать кнопки-переключатели.
        RadioButton rbTrain = new RadioButton("Поезд");
        RadioButton rbCar = new RadioButton("Автомобиль");
        RadioButton rbPlane = new RadioButton("Самолет");

        // Создать группу кнопок-переключателей.
        tg = new ToggleGroup();

        // Ввести каждую кнопку-переключатель в группу.
        rbTrain.setToggleGroup(tg);
        rbCar.setToggleGroup(tg);
        rbPlane.setToggleGroup(tg);

        // Первоначально выбрать одну из кнопок-переключателей.
        rbTrain.setSelected(true);

        // Обработать события действия от кнопки
        // подтверждения выбора транспортного средства.
        btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Получить выбранную в настоящий момент
                // кнопку-переключатель.
                RadioButton rb = (RadioButton) tg.getSelectedToggle();

                // Отобразить результат выбора
                // транспортного средства.
                response.setText("Подтверждено указанное транспортное средство " + rb.getText());
            }
        });

        // Использовать разделитель, чтобы улучшить порядок
        // расположения элементов управления.
        Separator separator = new Separator();
        separator.setPrefWidth(180);

        // Ввести метку и все виды кнопок в граф сцены.
        rootNode.getChildren().addAll(choose, rbTrain, rbCar, rbPlane,
                separator, btnConfirm, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
