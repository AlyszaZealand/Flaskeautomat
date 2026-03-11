import java.util.Random;

public class Consumer implements Runnable {
    // Transportbåndet (læsserampen), som lastbilen skal hente flasker fra
    private Buffer buffer;

    // Navnet på lastbilen (f.eks. "Øl-lastbil" eller "Sodavands-lastbil")
    private String navn;

    // En Random-generator til at simulere pakke-tiden
    private Random random = new Random();

    // Konstruktøren: Giver lastbilen et navn og fortæller den, hvilket bånd den skal holde ved
    public Consumer(Buffer buffer, String navn) {
        this.buffer = buffer;
        this.navn = navn;
    }

    @Override
    public void run() {
        try {
            // En uendelig løkke, så lastbilen bliver ved med at hente flasker
            while (true) {
                // 1. Lastbilen henter den næste flaske fra læsserampen
                // Hvis der ikke er nogen flasker, venter den her automatisk (notEmpty.await)
                String flaske = buffer.take();

                // 2. Udskriv information til konsollen, som opgaven kræver
                System.out.println("\u001B[36m" + "    -> [" + navn + "] læssede: " + flaske + "\u001B[0m");

                // 3. Vent en tilfældig mængde tid for at simulere virkeligheden
                int forsinkelse = random.nextInt(1500) + 500; // Venter mellem 0,5 og 2 sekunder
                Thread.sleep(forsinkelse);
            }
        } catch (InterruptedException e) {
            // Hvis systemet lukkes ned, fanger vi det her
            System.out.println(navn + " er kørt i garagen (stoppet).");
        }
    }
}
