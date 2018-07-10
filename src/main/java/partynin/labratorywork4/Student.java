package partynin.labratorywork4;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.MarkOutOfBoundsException;
import partynin.labratorywork2.Pupil;

import java.io.Serializable;
import java.util.Arrays;

public class Student implements Pupil, Serializable, Cloneable {

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
            System.out.println("The subjects array can't " +
                    "hold duplicate elements. " +
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

    @Override
    public String toString() {
        StringBuffer stringBufferStudent = new StringBuffer("Student last name: ");
        stringBufferStudent.append(lastName);

        for (int i = 0; i < getLength(); i++) {
            stringBufferStudent.append("\n" + subjects[i] + ": " + marks[i]);
        }

        return stringBufferStudent.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean equalsStudent = false;

        if (obj instanceof Pupil) {
            if (lastName.compareTo(((Pupil) obj).getLastName()) == 0 &&
                    subjectsAndMarksEqual((Pupil) obj))
            equalsStudent = true;
        }

        return equalsStudent;
    }

    private boolean subjectsAndMarksEqual(Pupil pupil) {
        if (getLength() == pupil.getLength()) {
            for (int i = 0; i < getLength(); i++) {
                if (subjects[i] != pupil.getSubjectsElement(i) ||
                        marks[i] != pupil.getMarksElement(i)) {
                    return false;
                }
            }
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (marks != null ? Arrays.hashCode(marks) : 0);
        result = 31 * result + (subjects != null ? Arrays.hashCode(subjects) : 0);

        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Perform a shallow copy
        Student studentClone = (Student) super.clone();
        // Deep copy
        studentClone.marks = marks.clone();
        studentClone.subjects = subjects.clone();

        return studentClone;
    }
}
