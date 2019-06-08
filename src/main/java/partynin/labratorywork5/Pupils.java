package partynin.labratorywork5;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;
import partynin.labratorywork2.Schoolboy;
import partynin.labratorywork2.Student;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * В классе Pupils написать метод createPupil(), который получает значение фамилии,
 * размер массивов предметов и оценок, ссылку типа Pupil, по которой средствами рефлексии
 * определяет реальный класс объекта, находит в нем конструктор и создает объект средствами
 * рефлексии и того же класса, что и переданный параметр. Если конструктор с параметрами типа
 * String и int отсутствует, то следует вернуть пустую ссылку.
 */

public class Pupils {

    /* Method for task 2 */
    public static Object createPupil(String lastName, int lengthOfArrays, Pupil objectIn) {

        Class<?> classStudent = objectIn.getClass();

        Constructor[] constructors = classStudent.getConstructors();
        for (Constructor constructor : constructors) {
            Class<?>[] params = constructor.getParameterTypes();
            if (!params[0].toString().equals("class java.lang.String") &&
                    !params[1].toString().equals("int"))
                return null;
        }

        Object instance = null;
        try {
            Constructor<?> constructor = classStudent.getConstructor(String.class, int.class);
            instance = constructor.newInstance(lastName, lengthOfArrays);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return instance;
    }

    /**
     * Изменить в классе Pupils метод, возвращающий среднее арифметическое оценок ученика,
     * так, чтобы он возвращал среднее арифметическое группы учеников
     * (с использованием аргумента переменной длины).
     */
    public static double averageMark(Pupil... pupils) {
        double averageMark = 0;

        for(Pupil pupil : pupils){
            averageMark += partynin.labratorywork3.Pupils.averageMark(pupil);
        }

        return averageMark / pupils.length;
    }

    // 1
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

    // 2
    public static Pupil inputPupil(InputStream in) throws IOException, DuplicateSubjectException {
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

    // 3
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

    // 4
    public static Pupil readPupil(Reader in) throws IOException, DuplicateSubjectException {
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


    /** Изменить методы текстового чтения и записи класса Pupils таким образом,
     * чтобы они использовали возможности форматированного ввода и вывода.
     * Метод записи должен использовать метод printf(), а метод чтения - класс Scanner. */

    public static Pupil readPupil5(Reader in) throws IOException, DuplicateSubjectException {
        Scanner inStreamScanner = new Scanner(in);
        String lastNameScanner = inStreamScanner.nextLine();

        int markScanner;
        String subScanner;

        int lenScanner = Integer.parseInt(inStreamScanner.nextLine());
        Pupil pupilScanner = new Schoolboy(lastNameScanner, lenScanner);

        for (int i = 0; i < lenScanner; i++) {
            subScanner = inStreamScanner.nextLine();
            markScanner = Integer.parseInt(inStreamScanner.nextLine());
            pupilScanner.setMarksElement(markScanner, i);
            pupilScanner.setSubjectsElement(subScanner, i);
        }
        return pupilScanner;
    }

    public static void writePupil5(Pupil pupil) {
        String lastName = pupil.getLastName();
        System.out.printf("Last Name %s %n", lastName);
        for (int i = 0; i < pupil.getLength(); i++) {
            String subject = pupil.getSubjectsElement(i);
            int mark = pupil.getMarksElement(i);
            System.out.printf("%s : %d %n", subject, mark);
        }
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
