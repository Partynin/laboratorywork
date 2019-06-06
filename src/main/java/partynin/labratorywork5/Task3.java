package partynin.labratorywork5;

public class Task3 {
    public static void main(String[] args) {
        Collegeboy collegeboyPartynin = new Collegeboy("Partynin");
        collegeboyPartynin.setSubjectAndMark("OOP", 5);
        collegeboyPartynin.setSubjectAndMark("Database", 5);
        collegeboyPartynin.setSubjectAndMark("Math", 5);

        System.out.println(collegeboyPartynin.getLastName());
        System.out.println(collegeboyPartynin.getLength());
        System.out.println(collegeboyPartynin.getListMarks());
        System.out.println(collegeboyPartynin.getListSubjects());
        collegeboyPartynin.printMarks();
        collegeboyPartynin.printSubjects();
    }
}
