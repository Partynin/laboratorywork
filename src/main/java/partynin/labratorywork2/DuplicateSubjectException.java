package partynin.labratorywork2;

public class DuplicateSubjectException extends Exception {
    private String duplicateSubject;

    /** Construct an exception */
    public DuplicateSubjectException(String duplicateSubject) {
        super("Invalid subject: " + duplicateSubject);
        this.duplicateSubject = duplicateSubject;
    }

    /** Returns the duplicate subject */
    public String getDuplicateSubject() {
        return duplicateSubject;
    }
}
