public class Splitter implements Runnable {

    // Splitteren har brug for adgang til tre transportbånd:
        private Buffer indkommendeBånd; // Båndet fra Producenten
        private Buffer ølBånd;          // Båndet kun til øl
        private Buffer sodavandsBånd;   // Båndet kun til sodavand

        // Konstruktøren: Vi giver Splitteren de tre bånd, når vi opretter den
        public Splitter(Buffer indkommendeBånd, Buffer ølBånd, Buffer sodavandsBånd) {
            this.indkommendeBånd = indkommendeBånd;
            this.ølBånd = ølBånd;
            this.sodavandsBånd = sodavandsBånd;
        }

        @Override
        public void run() {
            try {
                // En uendelig løkke, så Splitteren altid står klar til at sortere
                while (true) {
                    // 1. Hent den næste flaske fra det fælles bånd
                    // Hvis båndet er tomt, falder Splitteren i søvn her (notEmpty.await())
                    String flaske = indkommendeBånd.take();

                    // 2. Tjek hvilken type flaske det er, og send den til det rigtige bånd
                    if (flaske.startsWith("øl")) {
                        ølBånd.put(flaske); // Læg på øl-båndet
                        System.out.println("\u001B[33m" + "  Splitter sorterede en øl: " + flaske + "\u001B[0m");
                    } else if (flaske.startsWith("soda")) {
                        sodavandsBånd.put(flaske); // Læg på sodavands-båndet
                        System.out.println("\u001B[33m" + "  Splitter sorterede en vand: " + flaske + "\u001B[0m");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Splitter-maskinen blev stoppet.");
            }
        }
    }
