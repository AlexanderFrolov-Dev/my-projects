package ch35_controls_of_JavaFX;

// Продемонстрировать вращение, масштабирование,
// свечение и внутреннюю тень.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.transform.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;

public class EffectsAndTransformsDemo extends Application {

    double angle = 0.0;
    double glowVal = 0.0;
    boolean shadow = false;
    double scaleFactor = 1.0;

    // Сформировать первоначальные эффекты и преобразования.
    Glow glow = new Glow(0.0);
    InnerShadow innerShadow = new InnerShadow(10.0, Color.RED);
    Rotate rotate = new Rotate();
    Scale scale = new Scale(scaleFactor, scaleFactor);

    // Создать четыре экранные кнопки.
    Button btnRotate = new Button("Rotate");
    Button btnGlow = new Button("Glow");
    Button btnShadow = new Button("Shadow off");
    Button btnScale = new Button("Scale");

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Effects and Transforms Demo");
        // "демонстрация эффектов и преобразований."

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 100);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Задать первоначальный эффект свечения.
        btnGlow.setEffect(glow);

        // Ввести вращение в список преобразований
        // для экранной кнопки Rotate.
        btnRotate.getTransforms().add(rotate);

        // Ввести масштабирование в список преобразований
        // для экранной кнопки Scale.
        btnScale.getTransforms().add(scale);

        // Обработать события действия от кнопки Rotate.
        btnRotate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Всякий раз, когда кнопка нажимается,
                // она поворачивается на 30 градусов
                // вокруг своего центра.
                angle += 30.0;

                rotate.setAngle(angle);
                rotate.setPivotX(btnRotate.getWidth()/2);
                rotate.setPivotY(btnRotate.getHeight()/2);
            }
        });

        // Обработать события действия от кнопки Scale.
        btnScale.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Всякий раз, когда кнопка нажимается,
                // изменяется ее масштаб.
                scaleFactor += 0.1;
                if(scaleFactor > 1.0) scaleFactor = 0.4;

                scale.setX(scaleFactor);
                scale.setY(scaleFactor);

            }
        });


        // Обработать события действия от кнопки Glow.
        btnGlow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Всякий раз, когда кнопка нажимается,
                // изменяется степень ее свечения.
                glowVal += 0.1;
                if(glowVal > 1.0) glowVal = 0.0;

                // Установить новое значение свечения.
                glow.setLevel(glowVal);
            }
        });

        // Обработать события действия от кнопки Shadow.
        btnShadow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Всякий раз, когда кнопка нажимается,
                // изменяется состояние ее затенения.
                shadow = !shadow;
                if(shadow) {
                    btnShadow.setEffect(innerShadow);
                    btnShadow.setText("Shadow on");
                } else {
                    btnShadow.setEffect(null);
                    btnShadow.setText("Shadow off");
                }
            }
        });

        // Ввести метку и кнопки в граф сцены.
        rootNode.getChildren().addAll(btnRotate, btnScale, btnGlow, btnShadow);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
