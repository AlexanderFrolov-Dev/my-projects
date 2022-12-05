package ch36_introduction_to_JavaFX_Menu;

// Продемонстрировать меню - окончательный вариант.

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.beans.value.*;

public class MenuDemoJavaFX6FinalVersion extends Application {

    MenuBar mb;
    EventHandler<ActionEvent> MEHandler;
    ContextMenu editMenu;
    ToolBar tbDebug;

    Label response;

    public static void main(String[] args) {

        // Запустить JаvаFХ-приложение, вызвав метод launch().
        launch(args);
    }

    // Переопределить метод start().
    public void start(Stage myStage) {

        // Присвоить заголовок подмосткам.
        myStage.setTitle("Demonstrate Menus -- Final Version");
        // Демонстрация меню - окончательный вариант.

        // Использовать панель граничной компоновки
        // типа BorderPane в качестве корневого узла.
        BorderPane rootNode = new BorderPane();

        // Создать сцену.
        Scene myScene = new Scene(rootNode, 300, 300);

        // Установить сцену на подмостках.
        myStage.setScene(myScene);

        // Создать метку для отображения результатов выбора
        // из разных элементов управления ГПИ приложения.
        response = new Label();

        // Создать один приемник действий для обработки
        // всех событий действия, наступающих в меню.
        MEHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String name = ((MenuItem)ae.getTarget()).getText();

                if(name.equals("Exit")) Platform.exit();

                response.setText( name + " selected");
            }
        };

        // Создать строку меню.
        mb = new MenuBar();

        // Создать меню File.
        makeFileMenu();

        // Создать меню Options.
        makeOptionsMenu();

        // Создать меню Help.
        makeHelpMenu();

        // Создать контекстное меню.
        makeContextMenu();

        // Создать текстовое поле, задав ширину
        // его столбца равной 20.
        TextField tf = new TextField();
        tf.setPrefColumnCount(20);

        // Ввести контекстное меню в текстовое поле.
        tf.setContextMenu(editMenu);

        // Создать панель инструментов.
        makeToolBar();

        // Ввести контекстное меню непосредственно в граф сцены.
        rootNode.setOnContextMenuRequested(
                new EventHandler<ContextMenuEvent>() {
                    public void handle(ContextMenuEvent ae) {
                        // Отобразить всплывающее контекстное меню
                        // на том месте, где был произведен щелчок
                        // правой кнопкой мыши.
                        editMenu.show(rootNode, ae.getScreenX(), ae.getScreenY());
                    }
                });

        // Ввести строку меню в верхней области панели.
        rootNode.setTop(mb);

        // Создать панель поточной компоновки для хранения
        // текстового поля и метки ответной реакции на
        // действия пользователя.
        FlowPane fpRoot = new FlowPane(10, 10);

        // Выровнять элементы управления по центру сцены.
        fpRoot.setAlignment(Pos.CENTER);

        // Использовать разделитель, чтобы улучшить
        // порядок расположения элементов управления.
        Separator separator = new Separator();
        separator.setPrefWidth(260);

        // Ввести метку, разделитель и текстовое поле
        // на панели поточной компоновки.
        fpRoot.getChildren().addAll(response, separator,  tf);

        // Ввести панель инструментов в нижней области
        // панели граничной компоновки.
        rootNode.setBottom(tbDebug);

        // Ввести панель поточной компоновки в центральной
        // области панели граничной компоновки.
        rootNode.setCenter(fpRoot);

        // Показать подмостки и сцену на них.
        myStage.show();
    }

    // Создать меню File с мнемоникой.
    void makeFileMenu() {
        // Create the File menu, including a mnemonic.
        Menu fileMenu = new Menu("_File");  // "Файл"

        // Создать отдельные пункты меню File.
        MenuItem open = new MenuItem("Open");  // "Открыть"
        MenuItem close = new MenuItem("Close");  // "Закрыть"
        MenuItem save = new MenuItem("Save");  // "Сохранить"
        MenuItem exit = new MenuItem("Exit");  // "Выход"

        // Ввести пункты в меню File.
        fileMenu.getItems().addAll(open, close, save,
                new SeparatorMenuItem(), exit);

        // Ввести оперативные клавиши для быстрого выбора
        // пунктов из меню open, close, save, exit.
        open.setAccelerator(KeyCombination.keyCombination("shortcut+O"));
        close.setAccelerator(KeyCombination.keyCombination("shortcut+C"));
        save.setAccelerator(KeyCombination.keyCombination("shortcut+S"));
        exit.setAccelerator(KeyCombination.keyCombination("shortcut+E"));

        // Установить обработчики событий действия
        // для пунктов меню File.
        open.setOnAction(MEHandler);
        close.setOnAction(MEHandler);
        save.setOnAction(MEHandler);
        exit.setOnAction(MEHandler);

        // Ввести меню File в строку главного меню.
        mb.getMenus().add(fileMenu);
    }

    // Создать меню Options.
    void makeOptionsMenu() {
        Menu optionsMenu = new Menu("Options");  // "Параметры"

        // Создать подменю Colors.
        Menu colorsMenu = new Menu("Colors");  // "Цвета"

        // Использовать отмечаемые флажками пункты меню, чтобы
        // пользователь мог выбрать сразу несколько цветов.
        CheckMenuItem red = new CheckMenuItem("Red");  // "Красный"
        CheckMenuItem green = new CheckMenuItem("Green");  // "Зеленый"
        CheckMenuItem blue = new CheckMenuItem("Blue");  // "Синий"

        // Ввести отмечаемые флажками пункты в подменю Colors,
        // а само подменю Colors - в меню Options.
        colorsMenu.getItems().addAll(red, green, blue);
        optionsMenu.getItems().add(colorsMenu);

        // Задать зеленый цвет в качестве исходно выбираемого.
        green.setSelected(true);

        // Создать подменю Priority.
        Menu priorityMenu = new Menu("Priority");  // "Приоритет"

        // Использовать отмечаемые кнопками-переключателями
        // пункты меню для установки приоритета. Благодаря
        // этому в меню не только отображается установленный
        // приоритет, но и гарантируется установка одного и
        // только одного приоритета.
        RadioMenuItem high = new RadioMenuItem("High");  // "Высокий"
        RadioMenuItem low = new RadioMenuItem("Low");  // "Низкий"

        // Создать группу кнопок-переключателей
        // в пунктах подменю Priority.
        ToggleGroup tg = new ToggleGroup();
        high.setToggleGroup(tg);
        low.setToggleGroup(tg);

        // Отметить пункт меню High как исходно выбираемый.
        high.setSelected(true);

        // Ввести отмечаемые кнопками-переключателями пункты
        // в подменю Priority, а последнее - в меню Options.
        priorityMenu.getItems().addAll(high, low);
        optionsMenu.getItems().add(priorityMenu);

        // Ввести разделитель.
        optionsMenu.getItems().add(new SeparatorMenuItem());

        // Создать пункт меню Reset и ввести его в меню Options.
        MenuItem reset = new MenuItem("Reset");  // "Сбросить"
        optionsMenu.getItems().add(reset);

        // Установить обработчики событий действия для
        // пунктов меню Options.
        red.setOnAction(MEHandler);
        green.setOnAction(MEHandler);
        blue.setOnAction(MEHandler);
        high.setOnAction(MEHandler);
        low.setOnAction(MEHandler);
        reset.setOnAction(MEHandler);

        // Использовать приемник событий изменения,
        // чтобы оперативно реагировать на изменения
        // в отметке пунктов подменю Priority
        // кнопками-переключателями.
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> changed,
                                Toggle oldVal, Toggle newVal) {
                if(newVal==null) return;

                // Привести значение newVal к типу RadioMenuItem.
                RadioMenuItem rmi = (RadioMenuItem) newVal;

                // Отобразить результат выбора приоритета.
                response.setText("Priority selected is " + rmi.getText());
                // "Выбран указанный приоритет."
            }
        });

        // Ввести меню Options в строку меню.
        mb.getMenus().add(optionsMenu);
    }

    // Создать меню Help.
    void makeHelpMenu() {

        // Создать представление типа ImageView для изображения.
        ImageView aboutIV = new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\AboutIcon.png");

        // Создать меню Help.
        Menu helpMenu = new Menu("Help");  // "Справка"

        // Создать пункт About и ввести его в меню Help.
        MenuItem about = new MenuItem("About", aboutIV);
        helpMenu.getItems().add(about);
        // "О программе"

        // Установить обработчик событий действия для
        // пункта About меню Help.
        about.setOnAction(MEHandler);

        // Ввести меню Help в строку главного меню.
        mb.getMenus().add(helpMenu);
    }

    // Создать пункты контекстного меню.
    void makeContextMenu() {

        // Создать пункты для выбора команд редактирования
        // из контекстного меню.
        MenuItem cut = new MenuItem("Cut");  // "Вырезать"
        MenuItem copy = new MenuItem("Copy");  // "Копировать"
        MenuItem paste = new MenuItem("Paste");  // "Вставить"

        // Создать контекстное (т.е. всплывающее) меню
        // с пунктами для выбора команд редактирования.
        editMenu = new ContextMenu(cut, copy, paste);

        // Установить обработчики событий действия
        // для пунктов контекстного меню.
        cut.setOnAction(MEHandler);
        copy.setOnAction(MEHandler);
        paste.setOnAction(MEHandler);
    }

    // Создать панель инструментов.
    void makeToolBar() {
        // Создать кнопки для панели инструментов.
        Button btnSet = new Button("Set Breakpoint",
                new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\setBP.png"));
        Button btnClear = new Button("Clear Breakpoint",
                new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\clearBP.png"));
        Button btnResume = new Button("Resume Execution",
                new ImageView("E:\\From_Desktop\\IdeaProjects\\MyProjects\\JavaTCR10\\resume.png"));

        // Отключить текстовые надписи на кнопках.
        btnSet.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnClear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnResume.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        // Задать всплывающие подсказки для кнопок.
        btnSet.setTooltip(new Tooltip("Set a breakpoint."));
        // "Установить точку прерывания."
        btnClear.setTooltip(new Tooltip("Clear a breakpoint."));
        // "Очистить точку прерывания."
        btnResume.setTooltip(new Tooltip("Resume execution."));
        // "Возобновить выполнение."

        // Создать панель инструментов.
        tbDebug = new ToolBar(btnSet, btnClear, btnResume);

        // Создать обработчик событий от кнопок
        // на панели инструментов.
        EventHandler<ActionEvent> btnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText(((Button)ae.getTarget()).getText());
            }
        };

        // Установить обработчики событий действия
        // дпя кнопок на панели инструментов.
        btnSet.setOnAction(btnHandler);
        btnClear.setOnAction(btnHandler);
        btnResume.setOnAction(btnHandler);
    }
}
