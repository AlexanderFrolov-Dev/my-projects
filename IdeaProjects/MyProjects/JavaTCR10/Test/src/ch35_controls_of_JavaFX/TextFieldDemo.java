package ch35_controls_of_JavaFX;

// Продемонстрировать применение текстового поля.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class TextFieldDemo extends Application {

    TextField tf;
    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Demonstrate a TextField");
        // "Продемонстрировать элемент управления типа TextField."

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае с - промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 230, 140);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку, извещающую о содержимом
        // текстового поля.
        response = new Label("Search String: ");
        // "Строка запроса на поиск информации."


        // Создать кнопку для получения текста.
        Button btnGetText = new Button("Get Search String");
        // "Получить строку запроса на поиск информации."

        // Создать текстовое поле.
        tf = new TextField();

        // Задать подсказку.
        tf.setPromptText("Enter Search String");
        // "Ввести строку запроса на поиск информации."

        // Задать предпочтительное количество столбцов.
        tf.setPrefColumnCount(15);

        // Обработать события действия от текстового поля.
        // События действия генерируются при нажатии клавиши
        // <ENTER>, когда фокус ввода находится в текстовом
        // поле. В таком случае получается и отображается
        // текст, введенный в текстовом поле.
        tf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Search String: " + tf.getText());
                // "Строка запроса на поиск информации."
            }
        });

        // Получить текст из текстового поля, если нажата
        // клавиша <ENTER>, а затем отобразить его.
        btnGetText.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Search String: " + tf.getText());
                // "Строка запроса на поиск информации."
            }
        });

        // Использовать разделитель, чтобы улучшить
        // порядок расположения элементов управления.
        Separator separator = new Separator();
        separator.setPrefWidth(180);

        // Ввести все элементы управления в граф сцены.
        rootNode.getChildren().addAll(tf, btnGetText, separator, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
