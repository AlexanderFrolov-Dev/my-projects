// Пример использования диалоговых окон работы с файлами и директориями

import javax.swing.*;
import java.awt.event.*;

public class FileChooserTest extends JFrame
{
    private  JButton  btnSaveFile   = null;
    private  JButton  btnOpenDir    = null;
    private  JButton  btnFileFilter = null;

    private  JFileChooser fileChooser = null;

    private final String[][] FILTERS = {{"docx", "Файлы Word (*.docx)"},
            {"pdf" , "Adobe Reader(*.pdf)"}};
    public FileChooserTest() {
        super("Пример FileChooser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопка создания диалогового окна для выбора директории
        btnOpenDir = new JButton("Открыть директорию");
        // Кнопка создания диалогового окна для сохранения файла
        btnSaveFile = new JButton("Сохранить файл");
        // Кнопка создания диалогового окна для сохранения файла
        btnFileFilter = new JButton("Фильтрация файлов");

        // Создание экземпляра JFileChooser
        fileChooser = new JFileChooser();
        // Подключение слушателей к кнопкам
        addFileChooserListeners();

        // Размещение кнопок в интерфейсе
        JPanel contents = new JPanel();
        contents.add(btnOpenDir   );
        contents.add(btnSaveFile  );
        contents.add(btnFileFilter);
        setContentPane(contents);
        // Вывод окна на экран
        setSize(360, 110);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        // Локализация компонентов окна JFileChooser
        UIManager.put(
                "FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                "FileChooser.cancelButtonText", "Отмена");
        UIManager.put(
                "FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                "FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                "FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                "FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                "FileChooser.folderNameLabelText", "Путь директории");

        new FileChooserTest();
    }

    public void addFileChooserListeners() {
        btnOpenDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(FileChooserTest.this);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION )
                    JOptionPane.showMessageDialog(FileChooserTest.this,
                            fileChooser.getSelectedFile());
            }
        });
    }
}
