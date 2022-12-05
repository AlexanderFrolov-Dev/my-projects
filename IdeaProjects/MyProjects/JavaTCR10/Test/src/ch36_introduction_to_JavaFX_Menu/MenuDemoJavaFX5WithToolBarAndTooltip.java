package ch36_introduction_to_JavaFX_Menu;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCombination;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

public class MenuDemoJavaFX5WithToolBarAndTooltip extends Application {

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
        Menu fileMenu = new Menu("_File");  // "Файл"
        // Теперь в наименовании меню определяется мнемоника.

        MenuItem open = new MenuItem("Open");  // "Открыть"
        MenuItem close = new MenuItem("Close");  // "Закрыть"
        MenuItem save = new MenuItem("Save");  // "Сохранить"
        MenuItem exit = new MenuItem("Exit");  // "Выход"
        fileMenu.getItems().addAll(open, close, save,
                new SeparatorMenuItem(), exit);

        // Ввести оперативные клавиши для быстрого
        // выбора пунктов меню File.
        open.setAccelerator(KeyCombination.keyCombination("shortcut+O"));
        close.setAccelerator(KeyCombination.keyCombination("shortcut+C"));
        save.setAccelerator(KeyCombination.keyCombination("shortcut+S"));
        exit.setAccelerator(KeyCombination.keyCombination("shortcut+E"));

        // Ввести меню File в строку меню.
        mb.getMenus().add(fileMenu);

        // Создать меню Options.
        Menu optionsMenu = new Menu("Options");  // "Параметры"

        // Создать подменю Colors.
        Menu colorsMenu = new Menu("Colors");  // "Цвета"

        // Использовать отмечаемые флажками пункты меню, чтобы
        // пользователь мог выбрать сразу несколько цветов.
        CheckMenuItem red = new CheckMenuItem("Red");  // "Красный"
        CheckMenuItem green = new CheckMenuItem("Green");  // "Зеленый"
        CheckMenuItem blue = new CheckMenuItem("Blue");  // "Синий"
        colorsMenu.getItems().addAll(red, green, blue);
        optionsMenu.getItems().add(colorsMenu);

        // Выбрать по умолчанию зеленый цвет.
        green.setSelected(true);

        //  Создать подменю Priority.
        Menu priorityMenu = new Menu("Priority");  // "Приоритет"

        // Использовать отмечаемые кнопками-переключателями
        // пункты меню для установки приоритета. Благодаря
        // этому в меню не только отображается установленный
        // приоритет, но и гарантируется установка одного
        // и только одного приоритета.
        RadioMenuItem high = new RadioMenuItem("High");  // "Высокий"
        RadioMenuItem low = new RadioMenuItem("Low");  // "Низкий"

        // Создать группу кнопок-переключателей в пунктах
        // подменю Priority.
        ToggleGroup tg = new ToggleGroup();
        high.setToggleGroup(tg);
        low.setToggleGroup(tg);

        // Отметить исходно пункт меню High
        // как исходно выбираемый.
        high.setSelected(true);

        // Ввести отмечаемые кнопками-переключателями пункты
        // в подменю Priority, а последнее - в меню Options.
        priorityMenu.getItems().addAll(high, low);
        optionsMenu.getItems().add(priorityMenu);

        // Ввести разделитель.
        optionsMenu.getItems().add(new SeparatorMenuItem());

        // Создать пункт меню Reset.
        MenuItem reset = new MenuItem("Reset");  // "Сбросить"
        optionsMenu.getItems().add(reset);

        // И наконец, ввести меню Options в строку главного меню.
        mb.getMenus().add(optionsMenu);

        // Создать меню Help.
        Menu helpMenu = new Menu("Help");  //"Справка"

        ImageView aboutIV = new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\AboutIcon.png");
        MenuItem about = new MenuItem("About", aboutIV);  // "О программе"
        helpMenu.getItems().add(about);

        // Ввести меню Help в строку меню.
        mb.getMenus().add(helpMenu);

        // Создать пункты контекстного меню.
        MenuItem cut = new MenuItem("Cut");  // "Вырезать"
        MenuItem copy = new MenuItem("Copy");  // "Скопировать"
        MenuItem paste = new MenuItem("Paste");  // "Вставить"

        // Создать контекстное (т.е. всплывающее) меню
        // с пунктами для выбора команд редактирования.
        final ContextMenu editMenu = new ContextMenu(cut, copy, paste);

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

        cut.setOnAction(MEHandler);
        copy.setOnAction(MEHandler);
        paste.setOnAction(MEHandler);

        // Создать текстовое поле, задав ширину
        // его столбца равной 20.
        TextField tf = new TextField();
        tf.setPrefColumnCount(20);

        // Ввести контекстное меню в текстовое поле.
        tf.setContextMenu(editMenu);

        // Ввести строку меню в верхней области панели
        // граничной компоновки, а метку response -
        // в центре этой панели.
        rootNode.setTop(mb);

        // Создать панель поточной компоновки, которая
        // должна содержать текстовое поле и метку ответной
        // реакции на действия пользователя.
        FlowPane fpRoot = new FlowPane(10,10);

        // Выровнять элементы управления по центру сцены.
        fpRoot.setAlignment(Pos.CENTER);

        // Ввести текстовое поле и метку на
        // панели поточной компоновки.
        fpRoot.getChildren().addAll(response, tf);

        // Расположить панель поточной компоновки
        // по центру панели граничной компоновки.
        rootNode.setCenter(fpRoot);

        // Ввести контекстное меню непосредственно в граф сцены.
        rootNode.setOnContextMenuRequested(
                new EventHandler<ContextMenuEvent>() {
                    public void handle(ContextMenuEvent ae) {
                        // Отобразить контекстное меню в том месте,
                        // где был произведен щелчок кнопкой мыши.
                        editMenu.show(rootNode, ae.getScreenX(), ae.getScreenY());
                    }
                });

        // Определить панель инструментов.
        // Создать сначала кнопки на этой панели.
        Button btnSet = new Button("Set Breakpoint",
                new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\setBP.png"));
        Button btnClear = new Button("Clear Breakpoint",
                new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\clearBP.png"));
        Button btnResume = new Button("Resume Execution",
                new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\resume.png"));

        // Затем отключить текстовые надписи на кнопках.
        btnSet.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnClear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnResume.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        // Задать всплывающие подсказки.
        btnSet.setTooltip(new Tooltip("Set a breakpoint."));
        // "Установить точку прерывания."

        btnClear.setTooltip(new Tooltip("Clear a breakpoint."));
        // "Очистить точку прерывания."

        btnResume.setTooltip(new Tooltip("Resume execution."));
        // "Возобновить выполнение."

        // Создать панель инструментов.
        ToolBar tbDebug = new ToolBar(btnSet, btnClear, btnResume);

        // Создать обработчик событий действия от
        // каждой кнопки на панели инструментов.
        EventHandler<ActionEvent> btnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText(((Button)ae.getTarget()).getText());
            }
        };

        // Установить обработчики событий действия для
        // отдельных кнопок на панели инструментов.
        btnSet.setOnAction(btnHandler);
        btnClear.setOnAction(btnHandler);
        btnResume.setOnAction(btnHandler);

        rootNode.setBottom(tbDebug);

        // Показать подмостки и сцену на них.
        myStage.show();
    }
}