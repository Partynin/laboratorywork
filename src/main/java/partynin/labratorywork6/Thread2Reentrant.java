package partynin.labratorywork6;

import partynin.labratorywork2.Pupil;

import java.util.concurrent.locks.ReentrantLock;

public class Thread2Reentrant implements Runnable {

    private Pupil pupil;
    private ReentrantLock locker;

    public Thread2Reentrant(Pupil pupil, ReentrantLock locker) {
        this.pupil = pupil;
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            pupil.printMarks();
        } finally {
            locker.unlock();
        }

    }
}
