package partynin.labratorywork2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {
    private Student testStudent;

    @Before
    public void beforeTestStudent() {
        testStudent = new Student("Ivanov", 2);
    }

    @Test
    public void testGetLastName() {
        String expectedName = "Ivanov";

        String actualName = testStudent.getLastName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetLastName() {
        String inputSetName = "Petrov";

        String expectedName = "Petrov";

        testStudent.setLastName(inputSetName);

        String actualName = testStudent.getLastName();

        assertEquals(expectedName, actualName);
    }

    @Test(expected = MarkOutOfBoundsException.class)
    public void testSetMarksElementWhenReturnException() {
        Student studentTest = new Student("Test", 2);
        studentTest.setMarksElement(7, 0);
    }

    @Test
    public void testSetMarksElement() {
        int inputMark = 3;

        int expectedMark = 3;

        testStudent.setMarksElement(3, 0);

        int actualMark = testStudent.getMarksElement(0);

        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void testGetMarksElement() {
        testStudent.setMarksElement(5, 0);

        int expectedMark = 5;

        int actualMark = testStudent.getMarksElement(0);

        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void testPrintMarks() {

    }

    @Test
    public void testSetSubjectsElement() throws DuplicateSubjectException {
        String inputSubject = "OOP";

        String expectedSubject = "OOP";

        testStudent.setSubjectsElement(inputSubject, 0);

        String actualSubject = testStudent.getSubjectsElement(0);

        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    public void testGetSubjectsElement() throws DuplicateSubjectException {
        String inputSubject = "Math";

        String expectedSubject = "Math";

        testStudent.setSubjectsElement(inputSubject, 0);

        String actualSubject = testStudent.getSubjectsElement(0);

        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    public void testPrintSubjects() {
    }

    @Test
    public void testAddSubjectAndMark() throws DuplicateSubjectException {
        String inputSubject = "Database";
        int inputMark = 5;

        String expectedSubject = "Database";
        int expectedMark = 5;

        testStudent.addSubjectAndMark(inputSubject, inputMark);

        String actualSubject = testStudent.getSubjectsElement(testStudent.getLength() - 1);
        int actualMark = testStudent.getMarksElement(testStudent.getLength() - 1);

        assertEquals(actualSubject, expectedSubject);
        assertEquals(actualMark, expectedMark);
    }

    @Test
    public void testGetLengthOfArrays() {
        int expectedLength = 2;

        int actualLength = testStudent.getLength();

        assertEquals(expectedLength, actualLength);
    }
}
