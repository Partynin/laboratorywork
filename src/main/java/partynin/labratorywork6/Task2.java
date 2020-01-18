package partynin.labratorywork6;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork4.Schoolboy;

/**
 * Создайте два новых класса нитей (реализуют интерфейс Runnable), обеспечивающих последовательность
 * операций вывода предметов и оценок (т.е. на экран выводятся предмет-оценка-предмет-оценка…)
 * независимо от приоритетов потоков. Для этого потребуется описать некий вспомогательный класс PupilSynchronizer,
 * объект которого будет использоваться при взаимодействии нитей.
 */

public class Task2 {

    public static void main(String[] args) {
        Task2 task2 = new Task2();
    }

    public Task2() {
        Pupil pupilPartynin = new Schoolboy("Partynin", 0);
        try {
            pupilPartynin.addSubjectAndMark("OOP", 5);
            pupilPartynin.addSubjectAndMark("Math", 4);
            pupilPartynin.addSubjectAndMark("Database", 5);
            pupilPartynin.addSubjectAndMark("History", 3);
        } catch (DuplicateSubjectException e) {
            System.out.println("We have problem with duplicate subject!");
        }

        PupilSynchronizer pupilSynchronizer = new PupilSynchronizer(pupilPartynin);

        Thread1 thread1Runnable = new Thread1(pupilSynchronizer);
        Thread2 thread2Runnable = new Thread2(pupilSynchronizer);
        Thread thread1 = new Thread(thread1Runnable);
        Thread thread2 = new Thread(thread2Runnable);

        thread2.start();
        thread1.start();
    }

    class Thread1 implements Runnable {

        private PupilSynchronizer pupil;

        Thread1(PupilSynchronizer pupil) {
            this.pupil = pupil;
        }

        @Override
        public void run() {
            try {
                do {
                    pupil.printSubject();
                } while (pupil.canPrintSubject());
            } catch (InterruptedException e) {
                System.out.println("We have problem in method run Thread1 class!");
            }
        }
    }

    class Thread2 implements Runnable {

        private PupilSynchronizer pupil;

        Thread2(PupilSynchronizer pupil) {
            this.pupil = pupil;
        }

        @Override
        public void run() {
            try {
                do {
                    pupil.printMark();
                } while (pupil.canPrintMark());
            } catch (InterruptedException e) {
                System.out.println("We have problem in method run Thread2 class!");
            }
        }
    }
}
