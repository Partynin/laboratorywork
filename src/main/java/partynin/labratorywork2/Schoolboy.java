package partynin.labratorywork2;

import java.io.Serializable;
import java.util.Arrays;

public class Schoolboy implements Pupil,Serializable {
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
                    "The mark value must be greater than 6 and less than 0, not "
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
            if (register.subject == subject)
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
    }
}
