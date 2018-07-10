package partynin.labratorywork3;

import partynin.labratorywork2.Pupil;
import partynin.labratorywork2.Student;

import java.io.*;

public class Pupils {

    public static void writePupil(Pupil v, Writer out) throws IOException {
        out.write(v.getLastName()); // Writes the name of pupils.
        int numberOfSubject = v.getLength();
        out.write(" " + numberOfSubject); // Writes the number of subjects.
        for (int i = 0; i < v.getLength(); i++) {
            out.write(v.getSubjectsElement(i) + " "); // Writes the subject into the output.
            int mark = v.getMarksElement(i);
            out.write(mark + " "); // Writes the mark into the output.
        }
    }

    public static Pupil readPupil(Reader in) throws IOException {
        String name = "";
        int numberOfSubject;

        int token;
        while ((token = in.read()) != ' ') {
            name += (char) token; // Reading the name
        }
        numberOfSubject = Character.getNumericValue(in.read()); // Reading the number of subject
        Pupil readStudent = new Student(name, numberOfSubject);

        int mark;
        int serialNumber = 0;
        String subject = "";
        while ((token = in.read()) != -1) {
            if (token != ' ') {
                subject += (char) token; // Reading the subject name.
            } else {
                readStudent.setSubjectsElement(subject, serialNumber);
                mark = Character.getNumericValue(in.read()); // Reading the mark value.
                readStudent.setMarksElement(mark, serialNumber);
                in.read(); // Skip the whitespace.
                serialNumber++;
                subject = "";
            }
        }


        return readStudent;
    }

    public static void outputPupil(Pupil v, OutputStream out) throws IOException {
        out.write(v.getLastName().length());
        out.write(v.getLastName().getBytes());
        out.write(v.getLength()); // Writes the number of subjects and marks.
        for (int i = 0; i < v.getLength(); i++) {
            out.write(v.getSubjectsElement(i).length()); // Writes the length for the bytes array.
            out.write(v.getSubjectsElement(i).getBytes()); // Writes the bytes stream for the subject.
            out.write(v.getMarksElement(i)); // Writes the mark
        }
    }

    public static Pupil inputPupil(InputStream in) throws IOException {
        int nameLength = in.read();
        byte[] b = new byte[nameLength];
        in.read(b); // Reads up to b.length bytes into array b from the input stream.
        String readString = new String(b); // Pupil last name.

        int numberOfSubject = in.read(); // Number of subjects and marks.
        Pupil readStudent = new Student(readString, numberOfSubject);

        // Read within the loop values the subjects and mark and write its into the object.
        for (int i = 0; i < numberOfSubject; i++) {
            nameLength = in.read();
            b = new byte[nameLength];
            in.read(b);
            readString = new String(b);
            int mark = in.read();
            readStudent.setSubjectsElement(readString, i);
            readStudent.setMarksElement(mark, i);
        }

        return readStudent;
    }

    public static void printSubjects(Pupil pupil) {
        for (int i = 0; i < pupil.getLength(); i++) {
            System.out.println(pupil.getSubjectsElement(i));
        }
    }

    public static void printMarks(Pupil pupil) {
        for (int i = 0; i < pupil.getLength(); i++) {
            System.out.println(pupil.getMarksElement(i));
        }
    }

    public static void printSubjectsAndMarks(Pupil pupil) {
        for (int i = 0; i < pupil.getLength(); i++) {
            System.out.println(pupil.getSubjectsElement(i) + ": " +
                    pupil.getMarksElement(i));
        }
    }

    public static double averageMark(Pupil pupil) {
        double averageMark = 0;

        for (int i = 0; i < pupil.getLength(); i++) {
            averageMark += pupil.getMarksElement(i);
        }

        return averageMark / pupil.getLength();
    }
}
