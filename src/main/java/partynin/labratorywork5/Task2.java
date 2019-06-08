package partynin.labratorywork5;

import partynin.labratorywork2.Pupil;
import partynin.labratorywork4.Student;

import java.lang.reflect.InvocationTargetException;

public class Task2 {
    public static void main(String[] args) {
        Student studentPartynin = new Student("Partynin", 2);

        Pupil studentIvanov = null;
        try {
            studentIvanov = (Pupil) Pupils.createPupil("Ivanov", 2, studentPartynin);
        }
          catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(studentIvanov.getClass());
    }
}
