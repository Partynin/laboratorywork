package partynin.labratorywork4;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.MarkOutOfBoundsException;
import partynin.labratorywork2.Pupil;

import java.io.Serializable;
import java.util.Arrays;

public class Schoolboy implements Pupil, Serializable, Cloneable {
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
                throw new MarkOutOfBoundsException(valueForMark);
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
                throw new DuplicateSubjectException(valueForSubjects);
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
            throw new MarkOutOfBoundsException(mark);

        for (int i = 0; i < registers.length; i++){
            if (registers[i].getSubject().equals(subject))
                throw new DuplicateSubjectException(subject);
        }

        int length = registers.length + 1;

        registers = Arrays.copyOf(registers, length);
        registers[length - 1] = new Register();
        registers[length - 1].setSubject(subject);
        registers[length - 1].setMark(mark);
    }

    @Override
    public int getLength() {
        return registers.length;
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
        if (this == obj) {
            return true;
        }

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
        Schoolboy schoolboyClone = null;

        schoolboyClone = (Schoolboy) super.clone();
        schoolboyClone.registers = registers.clone();
        for (int i = 0; i < getLength(); i++) {
            schoolboyClone.registers[i] = (Register) registers[i].clone();
        }
        return schoolboyClone;

    }

    class Register implements Serializable, Cloneable {
        private String subject;
        private int mark;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getMark() {
            return mark;
        }


        public void setMark(int mark) {
            if ((mark < 1) || (mark > 5)) throw new MarkOutOfBoundsException(mark);
            this.mark = mark;
        }

        public Register() {
            subject = "newSubject";
            mark = 0;
        }

        public Register(String subject, int mark) {
            if ((mark < 1) || (mark > 5)) throw new MarkOutOfBoundsException(mark);
            this.subject = subject;
            this.mark = mark;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(subject);
            stringBuffer.append(": ");
            stringBuffer.append(mark);
            return stringBuffer.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj instanceof Register) {
                Register register = (Register) obj;
                if (!register.getSubject().equals(getSubject())) {
                    return false;
                } else if (register.getMark() != getMark()) {
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = mark;
            result = 31 * result + (subject != null ? subject.hashCode() : 0);
            return result;
        }

        @Override
        public Object clone() {
            Register result = null;
            try {
                result = (Register) super.clone();
                return result;
            } catch (CloneNotSupportedException ex) {
                return null;
            }
        }

    }
}
