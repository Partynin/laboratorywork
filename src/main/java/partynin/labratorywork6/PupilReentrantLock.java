package partynin.labratorywork6;

import partynin.labratorywork2.Pupil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PupilReentrantLock {

    private Pupil v;
    private volatile int currentMark = 0;
    private volatile int currentSubject = 0;
    Lock lock = new ReentrantLock();

    public PupilReentrantLock(Pupil v) {
        this.v = v;
    }

    public void printMark() throws InterruptedException {
        lock.lock();
        try {
            double val = 0;
            while (canPrintMark()) {
                if (!canPrintMark()) throw new InterruptedException();
                val = v.getMarksElement(currentMark++);
                System.out.println("Print mark: " + val);
                Thread.sleep(100);
            }
        } finally {
            lock.unlock();
        }
    }

    public void printSubject() throws InterruptedException {
        lock.lock();
        try {
            while (canPrintSubject()) {
                if (!canPrintSubject()) throw new InterruptedException();
                System.out.println("Print subject: " + v.getSubjectsElement(currentSubject++));
                Thread.sleep(100);
            }
        } finally {
            lock.unlock();
        }

    }

    public boolean canPrintMark() {
        return currentMark < v.getLength();
    }

    public boolean canPrintSubject() {
        return (currentSubject < v.getLength());
    }
}
