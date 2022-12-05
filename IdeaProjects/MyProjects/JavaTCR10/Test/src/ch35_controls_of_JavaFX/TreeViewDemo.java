package ch35_controls_of_JavaFX;

// Продемонстрировать применение элемента
// управления типа TreeView.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.beans.value.*;
import javafx.geometry.*;

public class TreeViewDemo extends Application {

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Demonstrate a TreeView");
        // "Продемонстрировать элемент
        // управления типа TreeView."

        // Использовать панель поточной компоновки
        // типа FlowPane в качестве корневого узла.
        // В данном случае - с промежутками 10
        // по вертикали и по горизонтали.
        FlowPane rootNode = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        rootNode.setAlignment(Pos.CENTER);

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 310, 460);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку, извещающую о состоянии
        // элемента, выбранного из дерева.
        response = new Label("No Selection");
        // "Ничего не выбрано."

        // Создать узлы дерева, начиная с корневого узла.
        TreeItem<String> tiRoot = new TreeItem<String>("Food");

        // Ввести поддеревья, начиная с узла фруктов.
        TreeItem<String> tiFruit = new TreeItem<String>("Fruit");

        // Построить узел яблок.
        TreeItem<String> tiApples = new TreeItem<String>("Apples");
        // Ввести порожденные узлы сортов яблок в узел яблок.
        tiApples.getChildren().add(new TreeItem<String>("Fuji"));
        tiApples.getChildren().add(new TreeItem<String>("Winesap"));
        tiApples.getChildren().add(new TreeItem<String>("Jonathan"));

        // Ввести порожденные узлы видов фруктов в узел фруктов.
        tiFruit.getChildren().add(tiApples);
        tiFruit.getChildren().add(new TreeItem<String>("Pears"));
        tiFruit.getChildren().add(new TreeItem<String>("Oranges"));

        // И наконец, ввести узел фруктов в корневой узел.
        tiRoot.getChildren().add(tiFruit);

        // А теперь ввести аналогичным образом узел овощей.
        TreeItem<String> tiVegetables = new TreeItem<String>("Vegetables");
        tiVegetables.getChildren().add(new TreeItem<String>("Corn"));
        tiVegetables.getChildren().add(new TreeItem<String>("Peas"));
        tiVegetables.getChildren().add(new TreeItem<String>("Broccoli"));
        tiVegetables.getChildren().add(new TreeItem<String>("Beans"));
        tiRoot.getChildren().add(tiVegetables);

        // И наконец, ввести аналогичным образом узел орехов.
        TreeItem<String> tiNuts = new TreeItem<String>("Nuts");
        tiNuts.getChildren().add(new TreeItem<String>("Walnuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Peanuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Pecans"));
        tiRoot.getChildren().add(tiNuts);

        // Создать древовидное представление,
        // используя только что построенное дерево.
        TreeView<String> tvFood = new TreeView<String>(tiRoot);

        // Получить модель выбора для древовидного представления.
        MultipleSelectionModel<TreeItem<String>> tvSelModel =
                tvFood.getSelectionModel();

        // Использовать приемник событий изменения,
        // чтобы оперативно реагировать на выбор
        // элементов в древовидном представлении.
        tvSelModel.selectedItemProperty().addListener(
                new ChangeListener<TreeItem<String>>() {
                    public void changed(
                            ObservableValue<? extends TreeItem<String>> changed,
                            TreeItem<String> oldVal, TreeItem<String> newVal) {

                        // Отобразить выбранный элемент и полный путь
                        // от него к корневому узлу.
                        if(newVal != null) {

                            // Построить весь путь к выбранному элементу.
                            String path = newVal.getValue();
                            TreeItem<String> tmp = newVal.getParent();
                            while(tmp != null) {
                                path = tmp.getValue() + " -> " + path;
                                tmp = tmp.getParent();
                            }

                            // Отобразить выбранный элемент и
                            // полный путь к нему.
                            response.setText("Selection is " + newVal.getValue() +
                                    "\nComplete path is " + path);
                        }
                    }
                });

        // Ввести элементы управления в граф сцены.
        rootNode.getChildren().addAll(tvFood, response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}