package partynin.labratorywork2;

public class MainLR2 {

    public static void main(String[] args) {
        Pupil partynin = new Student("Partynin", 3);
        partynin.setMarksElement(5,0);
        partynin.setMarksElement(5,1);
        partynin.setMarksElement(5,2);
        // see exception
        //partynin.setMarksElement(0, 0);
        try {
            partynin.setSubjectsElement("OOP", 0);
            partynin.setSubjectsElement("Database", 1);
            partynin.setSubjectsElement("Math", 2);
            partynin.setSubjectsElement("Math", 2);
        } catch (DuplicateSubjectException e) {
            e.printStackTrace();
        }

        try {
            partynin.addSubjectAndMark("Geography", 4);
        } catch (DuplicateSubjectException e) {
            e.printStackTrace();
        }
        System.out.println(partynin.getLastName());
        System.out.print("Print marks: ");
        partynin.printMarks();
        System.out.print("\nPrint subjects: ");
        partynin.printSubjects();
        System.out.println("\nAverage mark: " + Pupils.averageMark(partynin));

        Pupil ivanov = new Schoolboy("Ivanov", 3);
        ivanov.setMarksElement(4,0);
        ivanov.setMarksElement(5,1);
        ivanov.setMarksElement(4,2);
        // see exception
        //partynin.setMarksElement(0, 0);
        try {
            ivanov.setSubjectsElement("OOP", 0);
            ivanov.setSubjectsElement("Database", 1);
            ivanov.setSubjectsElement("Math", 2);
        } catch (DuplicateSubjectException e) {
            e.printStackTrace();
        }

        try {
            ivanov.addSubjectAndMark("Geography", 4);
        } catch (DuplicateSubjectException e) {
            e.printStackTrace();
        }
        System.out.println("____________________________________________");
        System.out.println(ivanov.getLastName());
        System.out.println("Print marks: ");
        Pupils.printMarks(ivanov);
        System.out.println("\nPrint subjects: ");
        Pupils.printSubjects(ivanov);
        System.out.print("\nAverage mark: " + Pupils.averageMark(ivanov));
    }
}
