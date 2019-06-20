package partynin.labratorywork7;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork5.Pupils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Реализовать (в отдельном модуле компиляции) серверную часть приложения в рамках
 * модели параллельной обработки запросов.
 */

public class MultipleThreadServer extends Application {

    private Pupil[] pupils;
    // TextArea для отображения данных получаемых сервером
    TextArea textArea = new TextArea();
    // Колличество подключений клиентов
    private int clientNo = 0;


    @Override // Переопределяем метод start в классе Application
    public void start(Stage primaryStage) throws Exception {

        // Создаем Scene со панелью прокрутки
        Scene scene = new Scene(new ScrollPane(textArea), 450, 200);
        primaryStage.setTitle("Sequential server"); //
        primaryStage.setScene(scene);
        primaryStage.show(); // Отображение

        new Thread(() -> {
            try {
                // Создаём сервер сокет
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> {
                    textArea.appendText("Многопоточный сервер запущен: "
                            + new Date() + '\n');
                });

                while (true) {
                    // Слущаем запрос на подключение
                    Socket socket = serverSocket.accept();

                    // Увеличиваем колличество подключенных клиентов
                    clientNo++;

                    Platform.runLater(() -> {
                        // Вывод колличества подключенных клиентов
                        textArea.appendText("Запущен поток для клиента " + clientNo +
                                " в " + new Date() + '\n');

                        // Находим имя и адресс клиента
                        InetAddress inetAddress = socket.getInetAddress();
                        textArea.appendText("Клиент " + clientNo + " host name - "
                                + inetAddress.getHostName() + "\n");
                        textArea.appendText("Клиент " + clientNo + " IP адресс - "
                                + inetAddress.getHostAddress() + "\n");
                    });

                    // Создаём и запускаем новый поток для соединеня клиента с сервером
                    new Thread(new HandleAClient(socket, clientNo)).start();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    // Класс для создания пактока нововго колиента
    class HandleAClient implements Runnable {
        private Socket socket; // Подключенный сокет
        private int clientNumber; // Номер клиента

        /**
         * Конструктор потока
         */
        public HandleAClient(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
        }

        /**
         * Запуск потока
         */
        public void run() {
            try {
                // Create data input and output streams
                ObjectInputStream objectInputFromClient = new ObjectInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                // Бесконечно обслуживает клиента
                while (true) {
                    try {
                        pupils = (Pupil[]) objectInputFromClient.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    double average = Pupils.averageMark(pupils);

                    outputToClient.writeDouble(average);

                    Platform.runLater(() -> {
                        textArea.appendText("Клиенту " + clientNumber + " отправлено число: " + average
                                + '\n');
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
