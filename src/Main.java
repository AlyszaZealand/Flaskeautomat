import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {

        // 1. Vi bygger de tre specifikke transportbånd (med plads til f.eks. 10 flasker)
        Buffer commonBelt = new Buffer(20,10);
        Buffer beerBelt = new Buffer(20,10);
        Buffer sodaBelt = new Buffer(20,10);

        // 2. Vi bygger Splitteren og giver den adgang til alle tre bånd
        Producer producer = new Producer(commonBelt);
        Splitter splitter = new Splitter(commonBelt, beerBelt, sodaBelt);

        // 3. HER SKER MAGIEN: Vi bygger lastbilerne og giver dem deres SPECIFIKKE bånd
        Consumer beerTruck = new Consumer(beerBelt, "Øl-lastbilen");
        Consumer sodaTruck = new Consumer(sodaBelt, "Sodavands-lastbilen");

        Thread producerThread = new Thread(producer);
        Thread SplitterThread = new Thread(splitter);
        Thread beerThread = new Thread(beerTruck);
        Thread sodavandsThread = new Thread(sodaTruck);

        System.out.println("Starter automaten");
        producerThread.start();
        SplitterThread.start();
        beerThread.start();
        sodavandsThread.start();


    }
}




// Buffer Klasse (Ressource-monitorer)
// Producer Klasse (Øl + Sodavand)
// Consumer Klasse (Lastbil afhenter)
// Splitter Klasse (Splitter Øl og Sodavand til Buffer)
// signal() og await()
//