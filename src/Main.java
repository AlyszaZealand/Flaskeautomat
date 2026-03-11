import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {

        // 1. Vi bygger de tre specifikke transportbånd (med plads til f.eks. 10 flasker)
        Buffer fællesBånd = new Buffer(60,30);
        Buffer ølBånd = new Buffer(10,1);
        Buffer sodavandsBånd = new Buffer(10,1);

        // 2. Vi bygger Splitteren og giver den adgang til alle tre bånd
        Producer producer = new Producer(fællesBånd);
        Splitter splitter = new Splitter(fællesBånd, ølBånd, sodavandsBånd);

        // 3. HER SKER MAGIEN: Vi bygger lastbilerne og giver dem deres SPECIFIKKE bånd
        Consumer ølLastbil = new Consumer(ølBånd, "Øl-lastbilen");
        Consumer sodavandsLastbil = new Consumer(sodavandsBånd, "Sodavands-lastbilen");

        Thread producerThread = new Thread(producer);
        Thread SplitterThread = new Thread(splitter);
        Thread ølThread = new Thread(ølLastbil);
        Thread sodavandsThread = new Thread(sodavandsLastbil);

        System.out.println("Starter automaten");
        producerThread.start();
        SplitterThread.start();
        ølThread.start();
        sodavandsThread.start();


    }
}




// Buffer Klasse (Ressource-monitorer)
// Producer Klasse (Øl + Sodavand)
// Consumer Klasse (Lastbil afhenter)
// Splitter Klasse (Splitter Øl og Sodavand til Buffer)
// signal() og await()
//