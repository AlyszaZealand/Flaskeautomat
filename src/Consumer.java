import java.util.Random;

public class Consumer implements Runnable {

    private Buffer buffer;
    private String name;
    private Random random = new Random();

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String bottle = buffer.take();

                System.out.println("\u001B[36m" + "    -> [" + name + "] læssede: " + bottle + "\u001B[0m");

                int delay = random.nextInt(1000);
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " er kørt i garagen (stoppet).");
        }
    }
}
