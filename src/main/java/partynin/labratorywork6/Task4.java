package partynin.labratorywork6;

import partynin.labratorywork2.Pupil;
import partynin.labratorywork4.Schoolboy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Создайте новый класс нитей (реализует интерфейс Runnable), обеспечивающий вывод на экран фамилии ученика.
 * В методе main() следует создать четверых участвующих в процессе учеников и пул потоков размерностью два.
 * Использовать класс Executors. Запустить программу несколько раз и проанализировать результаты. */

public class Task4 {

    public static void main(String[] args) {
        Pupil pupilPartynin = new Schoolboy("Partynin", 0);
        Pupil pupilIvanov = new Schoolboy("Ivanov", 0);
        Pupil pupilPetrov = new Schoolboy("Petrov", 0);
        Pupil pupilSidorov = new Schoolboy("Sidorov", 0);

        MyThread thread1 = new MyThread(pupilPartynin);
        MyThread thread2 = new MyThread(pupilIvanov);
        MyThread thread3 = new MyThread(pupilPetrov);
        MyThread thread4 = new MyThread(pupilSidorov);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(thread1);
        executor.execute(thread2);
        executor.execute(thread3);
        executor.execute(thread4);
        executor.shutdown();
    }
}

class MyThread implements Runnable {

    Pupil pupil;

    MyThread(Pupil pupil) {
        this.pupil = pupil;
    }

    @Override
    public void run() {
        System.out.println(pupil.getLastName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
