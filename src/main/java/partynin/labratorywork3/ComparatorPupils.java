package partynin.labratorywork3;

import partynin.labratorywork2.Pupil;

import java.util.Comparator;

public class ComparatorPupils implements Comparator<Pupil> {
    @Override
    public int compare(Pupil o1, Pupil o2) {
        if (compareLength(o1, o2) && compareLastName(o1, o2)
                && compareSubjectsAndMarks(o1, o2)) {
            return 0;
        } else if (o1.getLength() > o2.getLength()) {
            return 1;
        } else return -1;
    }

    private boolean compareSubjectsAndMarks(Pupil pupil1, Pupil pupil2) {
        for (int i = 0; i < pupil1.getLength(); i++) {
            if (pupil1.getSubjectsElement(i).compareTo(pupil2.getSubjectsElement(i)) != 0 ||
                    pupil1.getMarksElement(i) != pupil2.getMarksElement(i))
                return false;
        }
        return true;
    }

    private boolean compareLastName(Pupil pupil1, Pupil pupil2) {
        if (pupil1.getLastName().compareTo(pupil2.getLastName()) == 0) {
            return true;
        } else return false;
    }

    private boolean compareLength(Pupil pupil1, Pupil pupil2) {
        if (pupil1.getLength() == pupil2.getLength()) {
            return true;
        } else return false;
    }
}