public class Splitter implements Runnable {

        private Buffer incommingBelt; // Båndet fra Producenten
        private Buffer beerBelt;          // Båndet kun til øl
        private Buffer sodaBelt;   // Båndet kun til sodavand


        public Splitter(Buffer incommingBelt, Buffer beerBelt, Buffer sodaBelt) {
            this.incommingBelt = incommingBelt;
            this.beerBelt = beerBelt;
            this.sodaBelt = sodaBelt;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String bottle = incommingBelt.take();

                    if (bottle.startsWith("øl")) {
                        beerBelt.put(bottle); // Læg på øl-båndet
                        System.out.println("\u001B[33m" + "  Splitter sorterede en øl: " + bottle + "\u001B[0m");
                    } else if (bottle.startsWith("soda")) {
                        sodaBelt.put(bottle); // Læg på sodavands-båndet
                        System.out.println("\u001B[33m" + "  Splitter sorterede en vand: " + bottle + "\u001B[0m");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Splitter-maskinen blev stoppet.");
            }
        }
    }
