package partynin.labratorywork2;

import java.io.Serializable;
import java.util.Arrays;

public class Schoolboy implements Pupil, Serializable {
    private Register[] registers;
    private String lastName;

    public Schoolboy(String lastName, int registersLength) {
        this.lastName = lastName;
        registers = new Register[registersLength];
        for (int i = 0; i < registers.length; i++) {
            registers[i] = new Register();
        }
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
    public int getMarksElement(int registerNumber) {
        return registers[registerNumber].mark;
    }

    @Override
    public void setMarksElement(int valueForMark, int registerNumber) {
        if (valueForMark < 6 && valueForMark > 1) {
            registers[registerNumber].mark = valueForMark;
        } else
            throw new MarkOutOfBoundsException(valueForMark); // Mark have to be less than 6 and more than 1
    }

    @Override
    public void printMarks() {
        for (int i = 0; i < registers.length; i++) {
            System.out.print(getMarksElement(i) + " ");
        }
    }

    @Override
    public String getSubjectsElement(int registerNumber) {
        return registers[registerNumber].subject;
    }

    @Override
    public void setSubjectsElement(String valueForSubjects, int registerNumber) throws DuplicateSubjectException {
                if (registersNotContain(valueForSubjects))
                    registers[registerNumber].subject = valueForSubjects;
                else
                    throw new DuplicateSubjectException(valueForSubjects); // This subjects already exists in list
    }

    private boolean registersNotContain(String subject) {
        for (Register register : registers) {
            if (register.subject.equals(subject))
                return false;
        }

        return true;
    }

    @Override
    public void printSubjects() {
        for (int i = 0; i < registers.length; i++) {
            System.out.print(getSubjectsElement(i) + " ");
        }
    }

    @Override
    public void addSubjectAndMark(String subject, int mark) throws DuplicateSubjectException {
        if (mark < 1 || mark > 5)
            throw new MarkOutOfBoundsException(mark); // Mark have to be less than 6 and more than 1

        if (!registersNotContain(subject))
            throw new DuplicateSubjectException(subject); // This subjects already exists in list

        registers = Arrays.copyOf(registers, getLength() + 1);

        registers[getLength() - 1] = new Register();
        setSubjectsElement(subject, getLength() - 1);
        setMarksElement(mark, getLength() - 1);
    }

    @Override
    public int getLength() {
        return registers.length;
    }

    private class Register implements Serializable {
        private String subject = "sub";
        private int mark;
    }
}
