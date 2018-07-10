package partynin.labratorywork2;

public class Pupils {

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
