package ch35_controls_of_JavaFX;

// Продемонстрировать применение панели прокрутки.
// В данном JavaFX-приложении прокручивается содержимое
// многострочной метки, хотя прокручиваться может любой
// узел графа сцены.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class ScrollPaneDemo extends Application {

    ScrollPane scrlPane;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Demonstrate a ScrollPane");
        // "Продемонстрировать элемент
        // управления типа ScrollPane."

        // Использовать панель поточной компоновки типа FlowPane.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 200, 200);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать многострочную прокручиваемую метку,
        // где отмечаются преимущества элемента управления
        // типа ScrollPane над отдельными элементами
        // управления полосами прокрутки.
        Label scrlLabel = new Label(
                "A ScrollPane streamlines the process of\n" +
                        "adding scroll bars to a window whose\n" +
                        "contents exceed the window's dimensions.\n" +
                        "It also enables a control to fit in a\n" +
                        "smaller space than it otherwise would.\n" +
                        "As such, it often provides a superior\n" +
                        "approach over using individual scrollbars.");

        // Создать панель прокрутки, установив в качестве
        // содержимого метку типа scrlLabel.
        scrlPane = new ScrollPane(scrlLabel);

        // Задать ширину и высоту окна просмотра.
        scrlPane.setPrefViewportWidth(130);
        scrlPane.setPrefViewportHeight(80);

        // Разрешить панорамирование прокручиваемого
        // содержимого.
        scrlPane.setPannable(true);


        // Создать кнопку сброса.
        Button btnReset = new Button("Reset Scroll Bar Positions");
        // "Установить полосы прокрутки в исходное положение."

        // Обработать события действия от кнопки сброса.
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Установить полосы прокрутки на нулевые позиции.
                scrlPane.setVvalue(0);
                scrlPane.setHvalue(0);
            }
        });

        // Ввести метку и кнопку сброса в граф сцены.
        rootNode.getChildren().addAll(scrlPane, btnReset);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
