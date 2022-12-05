// Пример использования диалоговых окон работы с файлами и директориями

import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WithExplorerDialogBox extends JFrame {
    private final JButton selectSourceDir;
    private final JButton selectTargetDir;
    private final JButton createFiles;
//    private final JLabel selectedSourceDir;
//    private final JLabel selectedTargetDir;
//    private final JLabel noFolderSelected;
    private final JFileChooser fileChooser;
    private String sourceRootFolderPath;
    private String targetFolderPath;
    private static final Pattern FIRST_LINE_NEW_TYPE_FATAL = Pattern.compile("<FATALRequest:FATALRequest xmlns.+>");
    private static String firstLine;
    private static final String LAST_LINE_NEW_TYPE_FATAL = "</FATALRequest:FATALRequest>";
    private static ArrayList<String> filesNameList = new ArrayList<>();

    public WithExplorerDialogBox() {
        super("Выбор файлов с данными о смерти, относящиеся к ЗАГСам Смоленской области");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопка создания диалогового окна для выбора директории
        selectSourceDir = new JButton("Выбрать папку для чтения файлов");
        selectTargetDir = new JButton("Выбрать папку для сохранения файлов");
        createFiles = new JButton("Подтвердить выбор обеих папок");
        JLabel titleSelectedSrcDir = new JLabel("Папка для чтения файлов:");
        JLabel titleSelectedTargetDir = new JLabel("Папка для сохранения файлов:");
//        selectedSourceDir = new JLabel();
//        selectedTargetDir = new JLabel();
//        noFolderSelected = new JLabel();
        selectSourceDir.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectTargetDir.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleSelectedSrcDir.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleSelectedTargetDir.setAlignmentX(Component.CENTER_ALIGNMENT);
//        selectedSourceDir.setAlignmentX(Component.CENTER_ALIGNMENT);
//        selectedTargetDir.setAlignmentX(Component.CENTER_ALIGNMENT);
        createFiles.setAlignmentX(Component.CENTER_ALIGNMENT);
//        noFolderSelected.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Создание экземпляра JFileChooser
        fileChooser = new JFileChooser();
        // Подключение слушателей к кнопкам
        addFileChooserListeners();

        // Размещение кнопок в интерфейсе
        JPanel contents = new JPanel();
        contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
        contents.add(Box.createRigidArea(new Dimension(getWidth(), 50)));
        contents.add(selectSourceDir);
        contents.add(Box.createRigidArea(new Dimension(getWidth(), 10)));
        contents.add(titleSelectedSrcDir);
//        contents.add(selectedSourceDir);
        contents.add(Box.createRigidArea(new Dimension(getWidth(), 25)));
        contents.add(selectTargetDir);
        contents.add(Box.createRigidArea(new Dimension(getWidth(), 10)));
        contents.add(titleSelectedTargetDir);
//        contents.add(selectedTargetDir);
        contents.add(Box.createRigidArea(new Dimension(getWidth(), 25)));
        contents.add(createFiles);
        contents.add(Box.createRigidArea(new Dimension(getWidth(), 10)));
//        contents.add(noFolderSelected);
        setContentPane(contents);
        // Вывод окна на экран
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Локализация компонентов окна JFileChooser
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.lookInLabelText", "Директория");
        UIManager.put("FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put("FileChooser.folderNameLabelText", "Путь директории");

        new WithExplorerDialogBox();
    }

    public void addFileChooserListeners() {
        selectSourceDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(WithExplorerDialogBox.this);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION) {
//                    selectedSourceDir.setText(fileChooser.getSelectedFile().getName());
                    sourceRootFolderPath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
        selectTargetDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(WithExplorerDialogBox.this);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION) {
//                    selectedTargetDir.setText(fileChooser.getSelectedFile().getName());
                    targetFolderPath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
        createFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (selectedSourceDir.getName().isEmpty() && selectedTargetDir.getName().isEmpty()) {
//                    noFolderSelected.setText("Не все папки выбраны");
//                } else {
//                    try {
//                        createFiles();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                }
                try {
                    createFiles();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void createFiles() throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        int fileNumber = 0;

        searchFiles(new File(sourceRootFolderPath), fileList);
        for (File file : fileList) {
            fileNumber++;
//            System.out.println(fileNumber + " - " + file.getName());
            if (!searchSmolenskZagsFile(getStringFromFile(file)).isEmpty()) {
                createFile(searchSmolenskZagsFile(getStringFromFile(file)), file);
            }
        }

        for (String s : filesNameList) {
            System.out.println(s);
        }
    }

    private void createFile(java.util.List<String> blocksOfSeparateRecord, File sourceFile) {
        String targetPath = targetFolderPath.concat("\\").concat("Smolensk_").concat(sourceFile.getName());

        try {
            FileWriter writer = new FileWriter(targetPath);
            if (!firstLine.isEmpty()) {
                writer.write(firstLine.concat(System.lineSeparator()).concat("\s\s"));
            }
            for (String s : blocksOfSeparateRecord) {
                writer.write(s.concat(System.lineSeparator()));
            }
            writer.write(LAST_LINE_NEW_TYPE_FATAL);
            filesNameList.add(sourceFile.getName());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getFirstLine(String str) {
        Matcher matcher = FIRST_LINE_NEW_TYPE_FATAL.matcher(str);

        if (matcher.find()) {
            firstLine = matcher.group();
        }
    }

    private static String getStringFromFile(File file) throws IOException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);

        return IOUtils.toString(bis, "UTF-8");
    }

    private static void searchFiles(File rootFile, java.util.List<File> fileList) {
        int xmlFileNumber = 0;
        if (rootFile.isDirectory()) {
//            System.out.println("searching at: ".concat(rootFile.getAbsolutePath()));
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".xml")) {
                            xmlFileNumber++;
                            fileList.add(file);
//                            System.out.println("File" + xmlFileNumber + ": " + file.getName());
                        }
                    }
                }
            }
        }
    }

    private static java.util.List<String> searchSmolenskZagsFile(String str) {
        java.util.List<String> listOfBlocksDeathNew = new ArrayList<>();
        List<String> blocksWithZagsesSmolenskFatalNew = new ArrayList<>();
        Pattern blockFatalNew = Pattern.compile("<FATALRequest:СведРегСмерт[\\S\\s]*?</FATALRequest:СведРегСмерт>");
        Matcher matchInFatalNew = blockFatalNew.matcher(str);

        getFirstLine(str);

        while (matchInFatalNew.find()) {
            listOfBlocksDeathNew.add(matchInFatalNew.group());
        }

        for (String block : listOfBlocksDeathNew) {
            final Pattern fatalZagsSmolenskNew = Pattern.compile("<FATALRequest:ОрганЗАГС.+Смоленск");
            Matcher matchInBlock = fatalZagsSmolenskNew.matcher(block);
            if (matchInBlock.find()) {
                blocksWithZagsesSmolenskFatalNew.add(block);
            }
        }

        return blocksWithZagsesSmolenskFatalNew;
    }
}

