package partynin.labratorywork5;

import partynin.labratorywork2.DuplicateSubjectException;
import partynin.labratorywork2.Pupil;

import java.io.IOException;
import java.io.InputStreamReader;

public class Task5 {
    public static void main(String[] args) {
        try {
            Pupil pupil = Pupils.readPupil5(new InputStreamReader(System.in));

            Pupils.writePupil5(pupil);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DuplicateSubjectException e) {
            e.printStackTrace();
        }
    }
}
