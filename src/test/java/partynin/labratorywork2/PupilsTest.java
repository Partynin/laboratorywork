package partynin.labratorywork2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PupilsTest {

    @Test
    public void printSubjects() {
    }

    @Test
    public void printMarks() {
    }

    @Test
    public void printSubjectsAndMarks() {
    }

    @Test
    public void averageMark() {
        int inputMark1 = 3;
        int inputMark2 = 4;
        int inputMark3 = 3;

        double expectedAverage = 3.333;

        Student student = new Student("Ivanov", 3);
        student.setMarksElement(inputMark1, 0);
        student.setMarksElement(inputMark2, 1);
        student.setMarksElement(inputMark3, 2);

        double actualAverage = Pupils.averageMark(student);

        assertEquals(expectedAverage, actualAverage, 0.0004);
    }
}