import java.util.Random;

    public class Producer implements Runnable {

        private Buffer buffer;
        private int idCounter = 1;

        private Random random = new Random();
        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String type = random.nextBoolean() ? "øl" : "sodavand";
                    String bottle = type + idCounter;
                    idCounter++; //

                    buffer.put(bottle);
                    System.out.println("\u001B[32m" + "Producer lavede: " + bottle + "\u001B[0m");

                    int delay = random.nextInt(1000);
                    Thread.sleep(delay);
                }
            } catch (InterruptedException e) {
                System.out.println("Producer maskinen blev stoppet.");
            }
        }
    }
