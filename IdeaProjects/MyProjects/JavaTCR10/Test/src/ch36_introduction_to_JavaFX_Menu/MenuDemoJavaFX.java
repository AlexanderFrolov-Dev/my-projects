package ch36_introduction_to_JavaFX_Menu;

// Продемонстрировать меню.

import javafx.application.*;
        import javafx.scene.*;
        import javafx.stage.*;
        import javafx.scene.layout.*;
        import javafx.scene.control.*;
        import javafx.event.*;

public class MenuDemoJavaFX extends Application {

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Demonstrate Menus");
        // "Демонстрация меню."

        // Использовать панель граничной компоновки
        // типа BorderPane в качестве корневого узла.
        BorderPane rootNode = new BorderPane();

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 300);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку для отображения
        // результатов выбора из меню.
        response = new Label("Menu Demo");
        // "Демонстрация меню."

        // Создать строку меню.
        MenuBar mb = new MenuBar();

        // Создать меню File.
        Menu fileMenu = new Menu("File");  // "Файл"
        MenuItem open = new MenuItem("Open");  // "Открыть"
        MenuItem close = new MenuItem("Close");  // "Закрыть"
        MenuItem save = new MenuItem("Save");  // "Сохранить"
        MenuItem exit = new MenuItem("Exit");  // "Выход"
        fileMenu.getItems().addAll(open, close, save,
                new SeparatorMenuItem(), exit);

        // Ввести меню File в строку меню.
        mb.getMenus().add(fileMenu);

        // Создать меню Options.
        Menu optionsMenu = new Menu("Options");  // "Параметры"

        // Создать подменю Colors.
        Menu colorsMenu = new Menu("Colors");  // "Цвета"
        MenuItem red = new MenuItem("Red");  // "Красный"
        MenuItem green = new MenuItem("Green");  // "Зеленый"
        MenuItem blue = new MenuItem("Blue");  // "Синий"
        colorsMenu.getItems().addAll(red, green, blue);
        optionsMenu.getItems().add(colorsMenu);

        //  Создать подменю Priority.
        Menu priorityMenu = new Menu("Priority");  // "Приоритет"
        MenuItem high = new MenuItem("High");  // "Высокий"
        MenuItem low = new MenuItem("Low");  // "Низкий"
        priorityMenu.getItems().addAll(high, low);
        optionsMenu.getItems().add(priorityMenu);

        // Ввести разделитель.
        optionsMenu.getItems().add(new SeparatorMenuItem());

        // Создать пункт меню Reset.
        MenuItem reset = new MenuItem("Reset");  // "Сбросить"
        optionsMenu.getItems().add(reset);

        // Ввести меню Options в строку меню.
        mb.getMenus().add(optionsMenu);

        // Создать меню Help.
        Menu helpMenu = new Menu("Help");  //"Справка"
        MenuItem about = new MenuItem("About");  // "О программе"
        helpMenu.getItems().add(about);

        // Ввести меню Help в строку меню.
        mb.getMenus().add(helpMenu);

        // Создать один приемник действий для обработки
        // всех событий действия, наступающих в меню.
        EventHandler<ActionEvent> MEHandler =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        String name = ((MenuItem)ae.getTarget()).getText();

                        // Выйти из программы, если выбран пункт меню Exit.
                        if(name.equals("Exit")) Platform.exit();

                        response.setText( name + " selected");
                        // "Выбран указанный пункт меню."
                    }
                };

        // Установить приемники действий от пунктов меню.
        open.setOnAction(MEHandler);
        close.setOnAction(MEHandler);
        save.setOnAction(MEHandler);
        exit.setOnAction(MEHandler);
        red.setOnAction(MEHandler);
        green.setOnAction(MEHandler);
        blue.setOnAction(MEHandler);
        high.setOnAction(MEHandler);
        low.setOnAction(MEHandler);
        reset.setOnAction(MEHandler);
        about.setOnAction(MEHandler);

        // Ввести строку меню в верхней области панели
        // граничной компоновки, а метку response -
        // в центре этой панели.
        rootNode.setTop(mb);
        rootNode.setCenter(response);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}
