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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMarksElement(int elementNumber) {
        return marks[elementNumber];
    }

    public void setMarksElement(int valueForElement, int elementNumber) {
            if (valueForElement < 6 && valueForElement > 0) {
                marks[elementNumber] = valueForElement;
            } else
                throw new MarkOutOfBoundsException(valueForElement);
    }

    public void printMarks() {
        for (int i = 0; i < marks.length; i++) {
            System.out.print(getMarksElement(i) + " ");
        }
    }

    public String getSubjectsElement(int elementNumber) {
        return subjects[elementNumber];
    }

    public void setSubjectsElement(String valueForSubjects, int elementNumber) {
        try {
            if (!Arrays.asList(subjects).contains(valueForSubjects)) {
                subjects[elementNumber] = valueForSubjects;
            } else throw new DuplicateSubjectException(valueForSubjects);
        } catch (DuplicateSubjectException ex) {
            System.out.println("The subjects array can't hold duplicate elements. " +
                    "The value " + ex.getDuplicateSubject() + " is prohibited.");
            ex.printStackTrace();
            System.exit(2);
        }
    }

    public void printSubjects() {
        for (int i = 0; i < subjects.length; i++) {
            System.out.print(getSubjectsElement(i) + " ");
        }
    }

    public void addSubjectAndMark(String subject, int mark) {
        int lengthOfArrays = getLength() + 1;

        String[] newSubjects =
                Arrays.copyOf(subjects, lengthOfArrays);
        int[] newMarks = Arrays.copyOf(marks, lengthOfArrays);

        subjects = newSubjects;
        marks = newMarks;

        setSubjectsElement(subject, lengthOfArrays - 1);
        setMarksElement(mark, lengthOfArrays - 1);
    }

    public int getLength() {
        return subjects.length;
    }
}
