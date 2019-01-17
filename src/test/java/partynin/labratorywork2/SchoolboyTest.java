package partynin.labratorywork2;

import org.junit.Test;

import static org.junit.Assert.*;

public class SchoolboyTest {

    private Schoolboy testSchoolboy = new Schoolboy("Ivanov", 2);

    @Test
    public void getLastName() {
        String expectedName = "Ivanov";

        String actualName = testSchoolboy.getLastName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void setLastName() {
        String inputName = "Sidorov";

        String expectedName = "Sidorov";

        testSchoolboy.setLastName(inputName);

        String actualName = testSchoolboy.getLastName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void getMarkValueFromRegister() throws DuplicateSubjectException {
        int inputMark = 4;
        String inputSubject = "History";

        int expectedMark = 4;

        testSchoolboy.setMarksElement(inputMark, 0);
        testSchoolboy.setSubjectsElement(inputSubject, 0);

        int actualMark = testSchoolboy.getMarksElement(0);

        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void setMarkValueForRegister() {
        int inputMark = 5;

        int expectedMark = 5;

        testSchoolboy.setMarksElement(inputMark, 1);

        int actualMark = testSchoolboy.getMarksElement(1);

        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void printMarks() {
    }

    @Test
    public void getSubjectsElement() throws DuplicateSubjectException {
        String inputSubject = "Database Administration";

        String expectedSubject = "Database Administration";

        testSchoolboy.setSubjectsElement(inputSubject, 0);

        String actualSubject = testSchoolboy.getSubjectsElement(0);

        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    public void setSubjectsValueForRegister() throws DuplicateSubjectException {
        String inputSubject = "Rapid Java Application";

        String expectedSubject = "Rapid Java Application";

        testSchoolboy.setSubjectsElement(inputSubject, 1);

        String actualSubject = testSchoolboy.getSubjectsElement(1);

        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    public void printSubjects() {
    }

    @Test
    public void addSubjectAndMark() throws DuplicateSubjectException {
        String inputSubject = "Reading";
        int inputMark = 3;

        String expectedSubject = "Reading";
        int expectedMark = 3;

        testSchoolboy.addSubjectAndMark(inputSubject, inputMark);

        String actualSubject = testSchoolboy.getSubjectsElement(2);
        int actualMark = testSchoolboy.getMarksElement(2);

        assertEquals(expectedSubject, actualSubject);
        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void lengthOfRegistersArray() {
        int expectedLength = 2;

        int actualLength = testSchoolboy.getLength();

        assertEquals(expectedLength, actualLength);
    }
}
