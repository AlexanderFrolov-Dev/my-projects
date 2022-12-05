import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.regex.Pattern;

public class AppFrame extends JFrame {
    private final JTextField firstNumberField = new JTextField();
    private final JLabel choosingFirstNumber = new JLabel(FIRST_NUMBER_NOT_ENTERED);
    private final JLabel choosingOperator = new JLabel(OPERATOR_NOT_ENTERED);
    private final JTextField secondNumberField = new JTextField();
    private final JLabel choosingSecondNumber = new JLabel(SECOND_NUMBER_NOT_ENTERED);
    private final JTextArea resultText = new JTextArea("");
    StringBuilder result = new StringBuilder();
    private static final String FIRST_NUMBER_NOT_ENTERED = "Первое число не выбрано";
    private static final String SECOND_NUMBER_NOT_ENTERED = "Второе число не выбрано";
    private static final String OPERATOR_NOT_ENTERED = "Вы не выбрали оператор";
    private static final String FIRST_NUMBER_IS_ENTERED = "Первое число: ";
    private static final String SECOND_NUMBER_IS_ENTERED = "Второе число: ";
    private static final String OPERATOR_IS_ENTERED = "Выбран оператор: ";
    private static final String NOT_NUMBER = "Введенное значение не является числом. \nВведите число.\n";
    private static final String CANNOT_BE_DIVIDED_BY_ZERO = "Нельзя делить на ноль\n";
    private final String[] arrayResult = new String[4];
    private String firstNumberInput = "";
    private String operator = "";
    private String secondNumberInput = "";
    private static final String ALERT = "Пора сделать перерыв.\n";
    private static int countAlert = 0;
    private double firstNumber;
    private double secondNumber;
    private double resultCount;

    public AppFrame() {
        setTitle("Калькулятор");
        setSize(new Dimension(750,750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPanel = new JPanel();
        setContentPane(contentPanel);
        setLayout(new BoxLayout(contentPanel,BoxLayout.PAGE_AXIS));
        setLocationRelativeTo(null);

        contentPanel.setBackground(Color.PINK);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton minus = new JRadioButton("-");
        buttonGroup.add(minus);
        JRadioButton plus = new JRadioButton("+");
        buttonGroup.add(plus);
        JRadioButton multiply = new JRadioButton("*");
        buttonGroup.add(multiply);
        JRadioButton divide = new JRadioButton("/");
        buttonGroup.add(divide);

        firstNumberField.setMaximumSize(new Dimension(150, 20));
        secondNumberField.setMaximumSize(new Dimension(150, 20));

        JLabel addFirstNumber = new JLabel("Введите первое число и нажмите <Enter>:");
        addFirstNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        choosingFirstNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel addOperator = new JLabel("Выберите оператора:");
        addOperator.setAlignmentX(Component.CENTER_ALIGNMENT);
        choosingOperator.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel addSecondNumber = new JLabel("Введите второе число и нажмите <Enter>:");
        addSecondNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        choosingSecondNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton calculateResult = new JButton("Вычислить результат");
        calculateResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultText);
        scrollPane.setMaximumSize(new Dimension(250, 400));

        JPanel operatorPanel = new JPanel(new FlowLayout());
        operatorPanel.setMaximumSize(new Dimension(140, 25));
        operatorPanel.setBackground(Color.PINK);
        operatorPanel.add(plus);
        operatorPanel.add(minus);
        operatorPanel.add(multiply);
        operatorPanel.add(divide);

        add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), 50)));
        contentPanel.add(addFirstNumber);
        contentPanel.add(firstNumberField);
        contentPanel.add(choosingFirstNumber);
        add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), 15)));
        contentPanel.add(addOperator);
        contentPanel.add(operatorPanel);
        contentPanel.add(choosingOperator);
        add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), 15)));
        contentPanel.add(addSecondNumber);
        contentPanel.add(secondNumberField);
        contentPanel.add(choosingSecondNumber);
        add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), 50)));
        contentPanel.add(calculateResult);
        add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), 50)));
        contentPanel.add(scrollPane);

        firstNumberField.addActionListener(e -> {
            firstNumberInput = firstNumberField.getText();
            if (isNumber(firstNumberInput)){
                firstNumber = getNumber(firstNumberField.getText());
                if (!Objects.equals(firstNumberInput, "")) {
                    arrayResult[0] = String.valueOf(firstNumber);
                    choosingFirstNumber.setText(FIRST_NUMBER_IS_ENTERED + firstNumber);
                }
            } else {
                result.append(NOT_NUMBER);
                resultText.setText(String.valueOf(result));
            }
        });

        secondNumberField.addActionListener(e -> {
            secondNumberInput = secondNumberField.getText();
            if (isNumber(secondNumberInput)){
                secondNumber = getNumber(secondNumberField.getText());
                if (!Objects.equals(secondNumberInput, "")) {
                    arrayResult[2] = String.valueOf(secondNumber);
                    choosingSecondNumber.setText(SECOND_NUMBER_IS_ENTERED + secondNumber);
                }
            } else {
                result.append(NOT_NUMBER);
                resultText.setText(String.valueOf(result));
            }
        });

        calculateResult.addActionListener(e -> {
            resultCount = switch (operator) {
                case "+" -> firstNumber + secondNumber;
                case "-" -> firstNumber - secondNumber;
                case "*" -> firstNumber * secondNumber;
                case "/" -> firstNumber / secondNumber;
                default -> 0;
            };
            if (Double.isInfinite(resultCount)) {
                resultText.setText(String.valueOf(result.append(CANNOT_BE_DIVIDED_BY_ZERO)));
            } else {
                composeString(String.valueOf(resultCount));
                printAlert();
                resultText.setText(String.valueOf(result));
            }
            arrayResult[0] = String.valueOf(0);
            arrayResult[2] = String.valueOf(0);
            firstNumber = 0;
            secondNumber = 0;
            firstNumberField.setText("");
            secondNumberField.setText("");
            choosingFirstNumber.setText(FIRST_NUMBER_NOT_ENTERED);
            choosingSecondNumber.setText(SECOND_NUMBER_NOT_ENTERED);
        });

        plus.addActionListener(new CalculatorActionListener());
        minus.addActionListener(new CalculatorActionListener());
        multiply.addActionListener(new CalculatorActionListener());
        divide.addActionListener(new CalculatorActionListener());

        setVisible(true);
    }

    private boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        return pattern.matcher(str).find();
    }

    private double getNumber(String inputString) {
        return Double.parseDouble(inputString);
    }

    private String composeString(String resultNumber) {
        result.append(arrayResult[0])
                .append(" ")
                .append(arrayResult[1])
                .append(" ")
                .append(arrayResult[2])
                .append(" ")
                .append("=")
                .append(" ")
                .append(resultNumber)
                .append("\n");
        return String.valueOf(result);
    }

    private void printAlert() {
        countAlert++;
        if (countAlert % 5 == 0) {
            result.append(ALERT);
        }
    }

    private class CalculatorActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            operator = ((JRadioButton)e.getSource()).getText();
            if (!Objects.equals(operator, "")) {
                arrayResult[1] = String.valueOf(operator);
                choosingOperator.setText(OPERATOR_IS_ENTERED + operator);
            }
        }
    }
}