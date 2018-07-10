package partynin.labratorywork2;

public class MarkOutOfBoundsException extends RuntimeException {
    private int markOutOfBounds;

    /** Construct an exception */
    public MarkOutOfBoundsException(int markOutOfBounds) {
        super("This mark is illegal: " + markOutOfBounds);
        this.markOutOfBounds = markOutOfBounds;
    }

    /** Returns the index what out of bounds */
    public int getMarkOutOfBounds() {
        return markOutOfBounds;
    }
}
