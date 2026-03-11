import java.util.Random;

    public class Producer implements Runnable {
        // Producenten skal kende til det transportbånd (buffer), den skal lægge flasker på
        private Buffer buffer;

        // Vi holder styr på løbenummeret her
        private int idCounter = 1;

        // En Random-generator til at vælge mellem øl/vand og til at lave tidsforsinkelsen
        private Random random = new Random();

        // Konstruktøren: Når vi opretter en Producer, giver vi den bufferen med
        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                //  En uendelig løkke, så producenten bliver ved med at arbejde
                while (true) {
                    // 1. Beslut om det er en øl eller en vand (50/50 chance)
                    String type = random.nextBoolean() ? "øl" : "sodavand";

                    // 2. Sæt navnet sammen med løbenummeret (f.eks. "øl1" eller "vand2")
                    String flaske = type + idCounter;
                    idCounter++; // Tæl nummeret op til næste gang

                    // 3. Læg flasken på båndet ved hjælp af vores trådsikre put-metode
                    buffer.put(flaske);
                    System.out.println("\u001B[32m" + "Producer lavede: " + flaske + "\u001B[0m");

                    // 4. Vent en tilfældig mængde tid (simulerer at maskinen arbejder)
                    // Her venter den et tilfældigt antal millisekunder mellem 100 og 1000
                    int forsinkelse = random.nextInt(900) + 100;
                    Thread.sleep(forsinkelse);
                }
            } catch (InterruptedException e) {
                // Hvis tråden bliver afbrudt udefra, lander vi her
                System.out.println("Producer maskinen blev stoppet.");
            }
        }
    }
