package partynin.labratorywork2;

public interface Pupil {

    String getLastName();

    void setLastName(String lastName);

    int getMarksElement(int elementNumber);

    void setMarksElement(int valueForElement, int elementNumber);

    void printMarks();

    String getSubjectsElement(int elementNumber);

    void setSubjectsElement(String valueForSubjects, int elementNumber);

    void printSubjects();

    void addSubjectAndMark(String subject, int mark);

    int getLength();
}
