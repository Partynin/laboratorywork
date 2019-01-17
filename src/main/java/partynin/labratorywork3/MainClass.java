package partynin.labratorywork3;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork2.Schoolboy;
import partynin.labratorywork2.Student;

import java.io.*;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException, DuplicateSubjectException {
        System.out.print("Enter pupils name(Ivanov): ");
        Scanner input = new Scanner(System.in);
        String nameFirstStudent = input.nextLine();

        Pupil firstStudent = new Student(nameFirstStudent, 0);
        firstStudent.addSubjectAndMark("OOP", 4);
        firstStudent.addSubjectAndMark("Math", 5);
        firstStudent.addSubjectAndMark("Database", 3);
        firstStudent.addSubjectAndMark("Geography", 4);

        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream("files\\pupilDataFirst.dat"))) {
            Pupils.outputPupil(firstStudent, out);
        }
        try (DataInputStream in = new DataInputStream(
                new FileInputStream("files\\pupilDataFirst.dat"))) {
            Pupil readFirstStudent = Pupils.inputPupil(in);
            System.out.println(readFirstStudent.getLastName());
            Pupils.printSubjectsAndMarks(readFirstStudent);
        }

        Pupil secondStudent = new Schoolboy("Petrov", 0);
        secondStudent.addSubjectAndMark("OOP", 3);
        secondStudent.addSubjectAndMark("Math", 3);
        secondStudent.addSubjectAndMark("Database", 5);
        secondStudent.addSubjectAndMark("TheoryOfAlgorithms", 4);

        try (PrintWriter out = new PrintWriter(
                new FileWriter("files\\pupilDataSecond.txt"))
        ) {
            Pupils.writePupil(secondStudent, out);
        }

        try (BufferedReader in = new BufferedReader(
                new FileReader("files\\pupilDataSecond.txt"))) {
            Pupil readSecondStudent = Pupils.readPupil(in);
            System.out.println(readSecondStudent.getLastName());
            Pupils.printSubjectsAndMarks(readSecondStudent);
        }

        ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(
                "files\\objectData.dat"));
        objectOutput.writeObject(firstStudent);
        objectOutput.writeObject(secondStudent);

        ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(
                "files\\objectData.dat"));
        Pupil readFirstStudent = (Student) objectInput.readObject();
        Pupil readSecondStudent = (Schoolboy) objectInput.readObject();

        System.out.println("The first student object read from the file: ");
        System.out.println(readFirstStudent.getLastName());
        Pupils.printSubjectsAndMarks(readFirstStudent);

        System.out.println("The second student object read from the file: ");
        System.out.println(readSecondStudent.getLastName());
        Pupils.printSubjectsAndMarks(readSecondStudent);

        ComparatorPupils comparatorPupils = new ComparatorPupils();
        System.out.println("The first student equal the read first student - " +
                (comparatorPupils.compare(firstStudent, readFirstStudent) == 0 ?  "true" : "false"));
        System.out.println("The second student equal the read second student - " +
                (comparatorPupils.compare(secondStudent, readSecondStudent) == 0 ?  "true" : "false"));
    }
}
