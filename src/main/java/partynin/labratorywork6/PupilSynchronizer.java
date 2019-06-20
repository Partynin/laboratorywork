package partynin.labratorywork6;

import partynin.labratorywork2.Pupil;

public class PupilSynchronizer {

    private Pupil v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public PupilSynchronizer(Pupil v) {
        this.v = v;
    }

    public double printMark() throws InterruptedException {
        double val;
        synchronized(lock) {
            if (!canPrintMark()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = v.getMarksElement(current++);
            System.out.println("Print mark: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void printSubject() throws InterruptedException {
        synchronized(lock) {
            if (!canPrintSubject()) throw new InterruptedException();
            while (set)
                lock.wait();

            System.out.println("Print subject: " + v.getSubjectsElement(current));
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintMark() {
        return current < v.getLength();
    }

    public boolean canPrintSubject() {
        return (!set && current < v.getLength()) || (set && current < v.getLength() - 1);
    }

}
