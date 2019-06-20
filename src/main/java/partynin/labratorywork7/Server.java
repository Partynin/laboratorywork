package partynin.labratorywork7;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork5.Pupils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


/** Реализовать (в отдельном модуле компиляции) серверную часть приложения в
 * рамках модели последовательной обработки запросов. */

public class Server extends Application {

    private ObjectInputStream objectInputFromClient;
    private DataOutputStream outputToClient;
    private Pupil[] pupils;


    @Override // Переопределяем метод start в классе Application
    public void start(Stage primaryStage) throws Exception {
        // TextArea для отображения данных получаемых сервером
        TextArea textArea = new TextArea();

        // Создаем Scene со панелью прокрутки
        Scene scene = new Scene(new ScrollPane(textArea), 450, 200);
        primaryStage.setTitle("Sequential server"); //
        primaryStage.setScene(scene);
        primaryStage.show(); // Отображение

        new Thread(() -> {
            try {
                // Создаём сервер сокет
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> textArea.appendText("Сервер запущен: "
                        + new Date() + '\n'));
                // Слущаем запрос на подключение
                Socket socket = serverSocket.accept();

                // Создаем исходящий и входящий потоки
                objectInputFromClient = new ObjectInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    try {
                        pupils = (Pupil[]) objectInputFromClient.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    double average = Pupils.averageMark(pupils);

                    outputToClient.writeDouble(average);

                    Platform.runLater(() -> {
                        textArea.appendText("Клиенту отправлено число: " + average
                        + '\n');
                    });
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
