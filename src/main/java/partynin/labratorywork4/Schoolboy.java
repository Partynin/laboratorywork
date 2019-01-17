package partynin.labratorywork4;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.MarkOutOfBoundsException;
import partynin.labratorywork2.Pupil;

import java.io.Serializable;
import java.util.Arrays;

public class Schoolboy implements Pupil,Serializable, Cloneable {
    private Register[] registers;
    private String lastName;

    public Schoolboy(String lastName, int registersLength) {
        this.lastName = lastName;
        registers = new Register[registersLength];
        for (int i = 0; i < registers.length; i++) {
            registers[i] = new Register();
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMarksElement(int registerNumber) {
        return registers[registerNumber].mark;
    }

    public void setMarksElement(int valueForMark, int registerNumber) {
        try {
            if (valueForMark < 6 && valueForMark > 0) {
                registers[registerNumber].mark = valueForMark;
            } else
                throw new MarkOutOfBoundsException(valueForMark);
        } catch (MarkOutOfBoundsException ex) {
            System.out.println(
                    "The mark value must be greater" +
                            " than 6 and less than 0, not "
                            + ex.getMarkOutOfBounds());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void printMarks() {
        for (int i = 0; i < registers.length; i++) {
            System.out.print(getMarksElement(i) + " ");
        }
    }

    public String getSubjectsElement(int registerNumber) {
        return registers[registerNumber].subject;
    }

    public void setSubjectsElement(String valueForSubjects, int registerNumber) {
        try {
            if (registersNotContain(valueForSubjects))
                registers[registerNumber].subject = valueForSubjects;
            else
                throw new DuplicateSubjectException(valueForSubjects);
        } catch (DuplicateSubjectException ex) {
            ex.printStackTrace();
        }
    }

    private boolean registersNotContain(String subject) {
        for (Register register : registers) {
            if (register.subject.equals(subject))
                return false;
        }

        return true;
    }

    public void printSubjects() {
        for (int i = 0; i < registers.length; i++) {
            System.out.print(getSubjectsElement(i) + " ");
        }
    }

    public void addSubjectAndMark(String subject, int mark) {
        Register[] newRegisters =
                Arrays.copyOf(registers, getLength() + 1);

        registers = newRegisters;

        registers[getLength() - 1] = new Register();
        setSubjectsElement(subject, getLength() - 1);
        setMarksElement(mark, getLength() - 1);
    }

    public int getLength() {
        return registers.length;
    }

    private class Register implements Serializable {
        private String subject;
        private int mark;

       // @Override
        /*public int hashCode() {
            int result = mark;
            result = 31 * result + (subject != null ? subject.hashCode() : 0);

            return result;
        }*/
    }

    @Override
    public String toString() {
        StringBuffer stringBufferSchoolboy = new StringBuffer("Schoolboy last name: ");
        stringBufferSchoolboy.append(lastName);

        for (int i = 0; i < getLength(); i++) {
            stringBufferSchoolboy.append("\n" + registers[i].subject + ": "
                    + registers[i].mark);
        }

        return stringBufferSchoolboy.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean equalsSchoolboy = false;

        if (obj instanceof Pupil) {
            if (lastName.compareTo(((Pupil) obj).getLastName()) == 0 &&
                    subjectsAndMarksEqual((Pupil) obj))
                equalsSchoolboy = true;
        }

        return equalsSchoolboy;
    }

    private boolean subjectsAndMarksEqual(Pupil pupil) {
        if (getLength() == pupil.getLength()) {
            for (int i = 0; i < getLength(); i++) {
                if (!registers[i].subject.equals(pupil.getSubjectsElement(i)) ||
                        registers[i].mark != pupil.getMarksElement(i)) {
                    return false;
                }
            }
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (registers != null ? Arrays.hashCode(registers) : 0);

        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Shallow copy
        Schoolboy schoolboyClone = (Schoolboy) super.clone();
        // Deep copy
        schoolboyClone.registers = registers.clone();

        return schoolboyClone;
    }
}
