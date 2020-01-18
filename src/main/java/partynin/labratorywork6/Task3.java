package partynin.labratorywork6;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork4.Schoolboy;

import java.util.concurrent.locks.ReentrantLock;

/** Создайте два новых класса нитей (реализуют интерфейс Runnable), обеспечивающих вывод сначала всех предметов,
 *  а затем всех оценок ученика (возможен вывод сначала всех оценок,
 *  а затем всех предметов). Использовать ReentrantLock. */

public class Task3 {

    public static void main(String[] args) {
        Schoolboy pupilPartynin = new Schoolboy("Partynin", 0);

        try {
            pupilPartynin.addSubjectAndMark("OOP", 5);
            pupilPartynin.addSubjectAndMark("Math", 4);
            pupilPartynin.addSubjectAndMark("Database", 5);
            pupilPartynin.addSubjectAndMark("History", 3);
        } catch (DuplicateSubjectException e) {
            System.out.println("We have problem with duplicate subject!");
        }

        ReentrantLock lock = new ReentrantLock();

        Runnable thread1Reentrant = new Thread1Reentrant(pupilPartynin, lock);
        Runnable thread2Reentrant = new Thread2Reentrant(pupilPartynin, lock);
        Thread thread1 = new Thread(thread1Reentrant);
        Thread thread2 = new Thread(thread2Reentrant);

        thread1.start();
        thread2.start();
    }
}
