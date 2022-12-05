package ch35_controls_of_JavaFX;

// Продемонстрировать применение флажков

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class CheckboxDemo2 extends Application {

    CheckBox cbWeb;
    CheckBox cbDesktop;
    CheckBox cbMobile;

    Label response;
    Label allTargets;

    String targets = "";

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Demonstrate Checkboxes");
        // "Продемонстрировать флажки."

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае с промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 230, 140);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        Label heading = new Label("Select Deployment Options");
        // "Выбрать вариант развертывания приложения."

        // Создать метку, извещающую о состоянии
        // установленного флажка.
        response = new Label("No Deployment Selected");
        // "Ни один из вариантов развертывания не выбран."

        // Создать метку, извещающую обо всех
        // установленных флажках.
        allTargets = new Label("Target List: <none>");
        // "Список целевых флажков."

        // Создать флажки.
        cbWeb = new CheckBox("Web");  // "Веб"
        cbDesktop = new CheckBox("Desktop");  // "Настольная система"
        cbMobile = new CheckBox("Mobile");  // "Мобильное устройство"

        cbWeb.setTooltip(new Tooltip("Deploy to Web"));
        // Развернуть приложение в веб.
        cbDesktop.setTooltip(new Tooltip("Deploy to Desktop"));
        // Развернуть приложение в настольной системе.
        cbMobile.setTooltip(new Tooltip("Deploy to Mobile"));
        // Развернуть приложение на мобильном устройстве.

        // Обработать события действия от флажков.
        cbWeb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if(cbWeb.isSelected())
                    response.setText("Web deployment selected.");
                    // "Выбрано развертывание приложения в веб."
                else
                    response.setText("Web deployment cleared.");
                // "Развертывание приложения в веб отменено."

                showAll();
            }
        });

        cbDesktop.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if(cbDesktop.isSelected())
                    response.setText("Desktop deployment selected.");
                    // "Выбрано развертывание приложения в настольной системе."
                else
                    response.setText("Desktop deployment cleared.");
                // "Развертывание приложения в настольной системе отменено."

                showAll();
            }
        });

        cbMobile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if(cbMobile.isSelected())
                    response.setText("Mobile deployment selected.");
                    // "Выбрано развертывание приложения
                    // на мобильном устройстве."
                else
                    response.setText("Mobile deployment cleared.");
                // "Развертывание приложения на
                // мобильном устройстве отменено."

                showAll();
            }
        });

        // Использовать разделитель, чтобы улучшить
        // порядок расположения элементов управления.
        Separator separator = new Separator();
        separator.setPrefWidth(200);

        // Ввести элементы управления в граф сцены.
        rootNode.getChildren().addAll(heading,
                separator,
                cbWeb,
                cbDesktop,
                cbMobile,
                response,
                allTargets);

        // Показать подмостки и сцену на них.
        myStage.show();
    }

    // Обновить и показать список целевых флажков.
    void showAll() {
        targets = "";
        if(cbWeb.isSelected()) targets = "Web ";
        if(cbDesktop.isSelected()) targets += "Desktop ";
        if(cbMobile.isSelected()) targets += "Mobile";

        if(targets.equals("")) targets = "<none>";

        allTargets.setText("Target List: " + targets);
    }
}

