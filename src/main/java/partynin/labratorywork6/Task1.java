package partynin.labratorywork6;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork4.Schoolboy;

/** Создать два класса нитей (наследуют от класса Thread), взаимодействующих с помощью промежуточного объекта типа Pupil.
 Первая нить последовательно выводит на экран оценки ученика.
 Вторая нить последовательно выводит на экран предметы ученика.
 В методе main() следует создать 3 участвующих в процессе объекта (ученик и две нити) и запустить нити на выполнение.
 Запустите программу несколько раз. Попробуйте варьировать приоритеты нитей.
 */

public class Task1 {

    public static void main(String[] args) {
        Task1 task1 = new Task1();
    }

    public Task1() {
        Pupil pupilPartynin = new Schoolboy("Partynin", 2);
        try {
            pupilPartynin.addSubjectAndMark("OOP", 5);
            pupilPartynin.addSubjectAndMark("Math", 5);
            pupilPartynin.addSubjectAndMark("BD", 4);
        } catch (DuplicateSubjectException e) {
            System.out.println("We have problem with duplicate subject!");
        }

        Thread1 thread1 = new Thread1(pupilPartynin);
        Thread2 thread2 = new Thread2(pupilPartynin);

        //System.out.println(thread1.getName());
       // System.out.println("Thread1 priority: " + thread1.getPriority());
       // System.out.println("Thread2 priority: " + thread2.getPriority());
        thread1.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
    }

    class Thread1 extends Thread {

        private Pupil pupil;

        public Thread1(Pupil pupil) {
            this.pupil = pupil;
        }

        @Override
        public  void run() {
            pupil.printMarks();
        }
    }

    class Thread2 extends Thread {

       private  Pupil pupil;

        public Thread2(Pupil pupil) {
            this.pupil = pupil;
        }

        @Override
        public  void run() {
            pupil.printSubjects();
        }
    }
}
