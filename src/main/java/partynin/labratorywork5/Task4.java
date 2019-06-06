package partynin.labratorywork5;

import partynin.labratorywork2.Pupil;
import partynin.labratorywork2.Student;

public class Task4 {
    public static void main(String[] args) {
        Pupil partynin = new Student("Partynin", 3);
        partynin.setMarksElement(5,0);
        partynin.setMarksElement(5,1);
        partynin.setMarksElement(5,2);

        Pupil ivanov = new Student("Ivanov", 3);
        ivanov.setMarksElement(4,0);
        ivanov.setMarksElement(3,1);
        ivanov.setMarksElement(2,2);

        Pupil petrov = new Student("Petrov", 3);
        petrov.setMarksElement(4,0);
        petrov.setMarksElement(5,1);
        petrov.setMarksElement(5,2);

        System.out.println("Average mark for pupils: " + Pupils.averageMark(partynin, ivanov, petrov));
    }
}
