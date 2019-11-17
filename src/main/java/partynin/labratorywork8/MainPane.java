package partynin.labratorywork8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainPane extends Application {

    private Button buttonEqual = new Button("=");
    private Button buttonC = new Button("C");
    private Button buttonSing = new Button("+/-");
    private Button buttonSqrt = new Button("sqrt");
    private Button buttonPercent = new Button("%");
    private Button buttonZero = new Button("0");
    private Button button1 = new Button(Integer.toString(1));
    private Button button2 = new Button(Integer.toString(2));
    private Button button4 = new Button(Integer.toString(4));
    private Button button3 = new Button(Integer.toString(3));
    private Button button5 = new Button(Integer.toString(5));
    private Button button6 = new Button(Integer.toString(6));
    private Button button7 = new Button(Integer.toString(7));
    private Button button8 = new Button(Integer.toString(8));
    private Button button9 = new Button(Integer.toString(9));

    // Поле для ввода/вывода результата
    private TextArea textAreaView = new TextArea("You can start calculate!");
    private String firstNumber = "";
    private String secondNumber = "";
    private String lastCommand;
    private boolean startCalculate = false;
    private double result;

    private BorderPane getPane() {
        // Главная панель
        BorderPane pane = new BorderPane();

        textAreaView.setPrefRowCount(1);
        BorderPane paneTextAreaView = new BorderPane();
        paneTextAreaView.setCenter(textAreaView);
        paneTextAreaView.setPadding(new Insets(10, 10, 0, 10));


        textAreaView.setFont(Font.font("Times", 12));

        // Панель для размещения кнопок
        GridPane gridPaneButtons = new GridPane();
        gridPaneButtons.setAlignment(Pos.CENTER);
        gridPaneButtons.setPadding(new Insets(10, 10, 10, 10));
        gridPaneButtons.setHgap(5);
        gridPaneButtons.setVgap(5);

        // Создание небоходимых кнопок команд
        buttonEqual.setMinSize(50, 50);
        gridPaneButtons.add(buttonEqual, 3, 4);

        buttonC.setMinSize(50, 50);
        gridPaneButtons.add(buttonC, 0, 0);

        Button buttonDot = new Button(".");
        buttonDot.setMinSize(50, 50);
        gridPaneButtons.add(buttonDot, 2, 4);
        setActionForInsertButton(buttonDot);

        buttonSing.setMinSize(50, 50);
        gridPaneButtons.add(buttonSing, 1, 0);

        buttonPercent.setMinSize(50, 50);
        gridPaneButtons.add(buttonPercent, 2, 0);

        buttonSqrt.setMinSize(50, 50);
        gridPaneButtons.add(buttonSqrt, 0, 4);

        Button buttonDivision = new Button("/");
        buttonDivision.setMinSize(50, 50);
        gridPaneButtons.add(buttonDivision, 3, 0);
        setActionForCommandButton(buttonDivision);

        Button buttonMultiplication = new Button("*");
        buttonMultiplication.setMinSize(50, 50);
        gridPaneButtons.add(buttonMultiplication, 3, 1);
        setActionForCommandButton(buttonMultiplication);

        Button buttonSubtraction = new Button("-");
        buttonSubtraction.setMinSize(50, 50);
        gridPaneButtons.add(buttonSubtraction, 3, 2);
        setActionForCommandButton(buttonSubtraction);

        Button buttonAddition = new Button("+");
        buttonAddition.setMinSize(50, 50);
        gridPaneButtons.add(buttonAddition, 3, 3);
        setActionForCommandButton(buttonAddition);

        // Кнопки ввода цифр 0 - 9
        buttonZero.setMinSize(50, 50);
        gridPaneButtons.add(buttonZero, 1, 4);
        setActionForInsertButton(buttonZero);

        button1.setMinSize(50, 50);
        gridPaneButtons.add(button1, 0, 3);
        setActionForInsertButton(button1);

        button2.setMinSize(50, 50);
        gridPaneButtons.add(button2, 1, 3);
        setActionForInsertButton(button2);

        button3.setMinSize(50, 50);
        gridPaneButtons.add(button3, 2, 3);
        setActionForInsertButton(button3);

        button4.setMinSize(50, 50);
        gridPaneButtons.add(button4, 0, 2);
        setActionForInsertButton(button4);

        button5.setMinSize(50, 50);
        gridPaneButtons.add(button5, 1, 2);
        setActionForInsertButton(button5);

        button6.setMinSize(50, 50);
        gridPaneButtons.add(button6, 2, 2);
        setActionForInsertButton(button6);

        button7.setMinSize(50, 50);
        gridPaneButtons.add(button7, 0, 1);
        setActionForInsertButton(button7);

        button8.setMinSize(50, 50);
        gridPaneButtons.add(button8, 1, 1);
        setActionForInsertButton(button8);

        button9.setMinSize(50, 50);
        gridPaneButtons.add(button9, 2, 1);
        setActionForInsertButton(button9);

        // Размещаем на главной панели поле ввода/вывода и панель с кнопками
        pane.setTop(paneTextAreaView);
        pane.setBottom(gridPaneButtons);

        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = getPane();

        Scene scene = new Scene(mainPane, 250, 340);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ввод цифр с клавиатуры
        mainPane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DIGIT0: buttonZero.fire(); break;
                case DIGIT1: button1.fire(); break;
                case DIGIT2: button2.fire(); break;
                case DIGIT3: button3.fire(); break;
                case DIGIT4: button4.fire(); break;
                case DIGIT5: button5.fire(); break;
                case DIGIT6: button6.fire(); break;
                case DIGIT7: button7.fire(); break;
                case DIGIT8: button8.fire(); break;
                case DIGIT9: button9.fire(); break;
            }
        });

        buttonEqual.setOnAction(event -> {
            startCalculate = false;
            calculate(secondNumber);
        });

        buttonC.setOnAction(event -> {
            lastCommand = "";
            startCalculate = false;
            firstNumber = "";
            secondNumber = "";
            textAreaView.setText("0");
        });

        buttonSing.setOnAction(event -> {
            // Проверка на наличие "-" для первого числа
            if (!startCalculate && !firstNumber.contains("-")) {
                firstNumber = "-" + firstNumber; // Добавляем знак "-" первому числу
                textAreaView.setText(firstNumber);
            } else if (!startCalculate && firstNumber.contains("-")) {
                firstNumber = firstNumber.replaceFirst("-", ""); // Убираем знак "-" у первого числа
                textAreaView.setText(firstNumber);
            }

            // Проверка на наличие "-" для второго числа
            if (startCalculate && !secondNumber.contains("-")) {
                secondNumber = "-" + secondNumber; // Добавляем знак "-" первому числу
                textAreaView.setText(secondNumber);
            } else if (startCalculate && secondNumber.contains("-")) {
                secondNumber = secondNumber.replaceFirst("-", ""); // Меняем знак у первого числа
                textAreaView.setText(secondNumber);
            }
        });

        buttonSqrt.setOnAction(event -> {
            Double number = Double.parseDouble(textAreaView.getText());
            number = Math.sqrt(number);
            lastCommand = "";
            startCalculate = false;
            firstNumber = number.toString();
            secondNumber = "";
            textAreaView.setText(number.toString());
        });

        buttonPercent.setOnAction(event -> {
            Double number = Double.parseDouble(textAreaView.getText());
            number = number * 0.01;
            lastCommand = "";
            startCalculate = false;
            firstNumber = number.toString();
            secondNumber = "";
            textAreaView.setText(number.toString());
        });
    }

    private void addSymbolToNumber(String symbol) {
        if (!startCalculate) {
            firstNumber = firstNumber + symbol;
            textAreaView.setText(firstNumber);
        } else {
            secondNumber = secondNumber + symbol;
            textAreaView.setText(secondNumber);
        }
    }

    public void calculate(String number) {
        if (Double.parseDouble(number) == 0) { // Проверка деления на 0
            lastCommand = "";
            startCalculate = false;
            firstNumber = "";
            secondNumber = "";
            textAreaView.setText("Err");
        } else {
            switch (lastCommand) {
                case "+":
                    result = Double.parseDouble(firstNumber) + Double.parseDouble(number);
                    break;
                case "-":
                    result = Double.parseDouble(firstNumber) - Double.parseDouble(number);
                    break;
                case "*":
                    result = Double.parseDouble(firstNumber) * Double.parseDouble(number);
                    break;
                case "/":
                    result = Double.parseDouble(firstNumber) / Double.parseDouble(number);
                    break;
            }

            if (result == (long) result) {
                textAreaView.setText(String.format("%d", (long) result));
                firstNumber = String.format("%d", (long) result);
            } else {
                textAreaView.setText(String.format("%s", result));
                firstNumber = String.format("%s", result);
            }

            secondNumber = "";
        }
    }

    /* Метод описвывает дейстивия при нажатии кнопки ввода цифры */
    private void setActionForInsertButton(Button button) {
        button.setOnAction(event -> {
            // Проверка на наличие только одной точки для первого числа
            if ((!startCalculate && !firstNumber.contains(".")) || (button.getText() != "." && !startCalculate))
                addSymbolToNumber(button.getText()); // Получаем символ кнопки и записываем его

            // Проверка на наличие только одной точки для второго числа
            if ((startCalculate && !secondNumber.contains(".")) || (button.getText() != "." && startCalculate))
                addSymbolToNumber(button.getText()); // Получаем символ кнопки и записываем его
        });
    }

    private void setActionForCommandButton(Button button) {
        button.setOnAction(event -> {
            if (startCalculate) {
                calculate(secondNumber);
            } else {
                lastCommand = button.getText();
                startCalculate = true;
            }
        });
    }
}
