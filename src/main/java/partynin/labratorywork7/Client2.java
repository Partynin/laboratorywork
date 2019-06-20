package partynin.labratorywork7;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork2.Schoolboy;
import partynin.labratorywork2.Student;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/** Клиент для теста многопоточного сервера*/

public class Client2 extends Application {

    // Исходящий поток обьектного типа
    private ObjectOutputStream objectToServer = null;
    private DataInputStream fromServer = null;

    @Override // Переопределяем метод start в классе Application
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();

        VBox vBoxPane = new VBox();
        HBox hBoxPane = new HBox();
        Button buttonSend = new Button("Send");
        Text textSendToServer = new Text("Send objects to server  ");
        TextArea textAreaForServerReplay = new TextArea();

        hBoxPane.getChildren().addAll(textSendToServer, buttonSend);
        hBoxPane.setPadding(new Insets(5, 5, 5, 5));
        hBoxPane.setAlignment(Pos.CENTER);

        vBoxPane.getChildren().addAll(textAreaForServerReplay, hBoxPane);

        mainPane.setCenter(vBoxPane);

        // Создаем Scene со панелью прокрутки
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Client"); //
        primaryStage.setScene(scene);
        primaryStage.show(); // Отображение

        buttonSend.setOnAction(e -> {
            try {
                // Создаём объекты для отправки на сервер
                Pupil schoolboyPartynin = new Schoolboy("Partynin", 0);
                Pupil studentIvaonv = new Student("Ivanov", 0);
                Pupil studentPetrov = new Student("Petrov", 0);
                try {
                    schoolboyPartynin.addSubjectAndMark("OOP", 5);
                    schoolboyPartynin.addSubjectAndMark("Math", 5);
                    studentIvaonv.addSubjectAndMark("Database", 5);
                    studentIvaonv.addSubjectAndMark("History", 3);
                    studentPetrov.addSubjectAndMark("Geometry", 4);
                    studentPetrov.addSubjectAndMark("Biology", 3);
                } catch (DuplicateSubjectException e1) {
                    e1.printStackTrace();
                }
                // Массив для отправки серверу
                Pupil[] pupils = {schoolboyPartynin, studentIvaonv, studentPetrov};
                // Отрпавляем массив серверу
                objectToServer.writeObject(pupils);
                objectToServer.flush();

                // Получаем среднее значение оценок учеников от сервера
                double average = fromServer.readDouble();

                textAreaForServerReplay.appendText("Всего созданно объектов Pupil: " + pupils.length + "\n");
                textAreaForServerReplay.appendText("Среднеарифметическое значение оценок учеников: " + average + '\n');
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        });

        try {
            // Сокет для соединения с сервером
            Socket socket = new Socket("localhost", 8000);

            // Поток для получения данных с сервера
            fromServer = new DataInputStream(socket.getInputStream());

            // Создаём объектный исходящий поток на сервер
            objectToServer = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
