package partynin.labratorywork6;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork4.Schoolboy;

/** Создайте два новых класса нитей (реализуют интерфейс Runnable), обеспечивающих вывод сначала всех предметов,
 *  а затем всех оценок ученика (возможен вывод сначала всех оценок,
 *  а затем всех предметов). Использовать ReentrantLock. */

public class Task3 {

    public static void main(String[] args) {
        Task3 task3 = new Task3();
    }

    public Task3() {
        Pupil pupilPartynin = new Schoolboy("Partynin", 0);
        try {
            pupilPartynin.addSubjectAndMark("OOP", 5);
            pupilPartynin.addSubjectAndMark("Math", 4);
            pupilPartynin.addSubjectAndMark("Database", 5);
            pupilPartynin.addSubjectAndMark("History", 3);
        } catch (DuplicateSubjectException e) {
            System.out.println("We have problem with duplicate subject!");
        }

        PupilReentrantLock pupilReentrantLock = new PupilReentrantLock(pupilPartynin);

        Task3.Thread1 thread1Runnable = new Task3.Thread1(pupilReentrantLock);
        Task3.Thread2 thread2Runnable = new Task3.Thread2(pupilReentrantLock);
        Thread thread1 = new Thread(thread1Runnable);
        Thread thread2 = new Thread(thread2Runnable);

        thread1.start();
        thread2.start();
    }

    class Thread1 implements Runnable {

        PupilReentrantLock pupil;

        Thread1(PupilReentrantLock pupil) {
            this.pupil = pupil;
        }

        @Override
        public void run() {
            try {
                    pupil.printSubject();
            } catch (InterruptedException e) {
                System.out.println("We have problem in method run Thread1 class!");
            }
        }
    }

    class Thread2 implements Runnable {
        PupilReentrantLock pupil;

        Thread2(PupilReentrantLock pupil) {
            this.pupil = pupil;
        }

        @Override
        public void run() {
            try {
                    pupil.printMark();
            } catch (InterruptedException e) {
                System.out.println("We have problem in method run Thread2 class!");
            }
        }
    }
}
