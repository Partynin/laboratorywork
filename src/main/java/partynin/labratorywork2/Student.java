package partynin.labratorywork2;

import java.io.Serializable;
import java.util.Arrays;

public class Student implements Pupil, Serializable {

    private String lastName;
    private int[] marks;
    private String[] subjects;

    public Student(String lastName, int lengthOfArrays) {
        this.lastName = lastName;
        marks = new int[lengthOfArrays];
        subjects = new String[lengthOfArrays];
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int getMarksElement(int elementNumber) {
        return marks[elementNumber];
    }

    @Override
    public void setMarksElement(int valueForElement, int elementNumber) {
        if (valueForElement < 6 && valueForElement > 1) {
            marks[elementNumber] = valueForElement;
        } else
            throw new MarkOutOfBoundsException(valueForElement); // Mark have to be less than 6 and more than 1
    }

    @Override
    public void printMarks() {
        for (int i = 0; i < marks.length; i++) {
            System.out.print(getMarksElement(i) + " ");
        }
    }

    @Override
    public String getSubjectsElement(int elementNumber) {
        return subjects[elementNumber];
    }

    @Override
    public void setSubjectsElement(String valueForSubjects, int elementNumber) throws DuplicateSubjectException {
        if (Arrays.asList(subjects).contains(valueForSubjects)) {
            throw new DuplicateSubjectException(valueForSubjects); // This subjects already exists in list
        } else
            subjects[elementNumber] = valueForSubjects;
    }

    @Override
    public void printSubjects() {
        for (int i = 0; i < subjects.length; i++) {
            System.out.print(getSubjectsElement(i) + " ");
        }
    }

    @Override
    public void addSubjectAndMark(String subject, int mark) throws DuplicateSubjectException {
        if (Arrays.asList(subjects).contains(subject))
            throw new DuplicateSubjectException(subject); // This subjects already exists in list

        if (mark < 1 || mark > 5)
            throw new MarkOutOfBoundsException(mark); // Mark have to be less than 6 and more than 1

        int lengthOfArrays = getLength() + 1;

        subjects = Arrays.copyOf(subjects, lengthOfArrays);
        marks = Arrays.copyOf(marks, lengthOfArrays);

        setSubjectsElement(subject, lengthOfArrays - 1);
        setMarksElement(mark, lengthOfArrays - 1);
    }

    @Override
    public int getLength() {
        return subjects.length;
    }
}
