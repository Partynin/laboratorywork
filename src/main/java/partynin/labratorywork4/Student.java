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
            throw new MarkOutOfBoundsException(valueForElement);
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
            if (!Arrays.asList(subjects).contains(valueForSubjects)) {
                subjects[elementNumber] = valueForSubjects;
            } else throw new DuplicateSubjectException(valueForSubjects);
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
        if (this == obj) {
            return true;
        }

        boolean equalsStudent = false;

        if (obj instanceof Student) {
            if (lastName.compareTo(((Student) obj).getLastName()) == 0 &&
                    subjectsAndMarksEqual((Student) obj))
            equalsStudent = true;
        }

        return equalsStudent;
    }

    private boolean subjectsAndMarksEqual(Student obj) {
        if (getLength() == obj.getLength()) {
            for (int i = 0; i < getLength(); i++) {
                if (!subjects[i].equals(obj.getSubjectsElement(i)) ||
                        marks[i] != obj.getMarksElement(i)) {
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
