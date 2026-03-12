import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private Queue<String> queue;
    private int maxSize;

    // NYT: Hvor mange flasker skal vi vente på, før vi åbner for sluserne?
    private int startingToSortSize;

    // NYT: Vores "afbryder", der fortæller om vi er i gang med at tømme
    private boolean readyToSplit = false;

    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    // NYT: Konstruktøren tager nu to tal: Max plads, og hvornår vi starter
    public Buffer(int maxSize, int startingToSortSize) {
        this.maxSize = maxSize;
        this.startingToSortSize = startingToSortSize;
        this.queue = new LinkedList<>();

        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public void put(String Bottle) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                notFull.await();
            }

            queue.add(Bottle);

            // NYT: Tjek om vi har ramt vores magiske grænse (f.eks. 50)
            if (queue.size() >= startingToSortSize) {
                readyToSplit = true; // Slå afbryderen TIL!
                notEmpty.signal();    // Væk Splitteren, nu er der fest!
            }
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            // NYT: Splitteren venter, indtil afbryderen er slået TIL
            while (!readyToSplit) {
                notEmpty.await();
            }

            String Bottle = queue.poll();
            notFull.signal();

            // NYT: Hvis Splitteren lige har taget den ALLERSIDSTE flaske...
            if (queue.isEmpty()) {
                readyToSplit = false; // Slå afbryderen FRA igen, så vi skal vente på 50 næste gang
            }

            return Bottle;
        } finally {
            lock.unlock();
        }
    }
}

