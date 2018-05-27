package twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import twitter4j.TwitterException;

public class Twitter {

    public static void main(String[] args) throws TwitterException, InterruptedException, ExecutionException {
        long segundos_activo = 61200 * 1000; //61200 segundos son 17 horas. De 6:00am a 11:00pm
        //long segundos_activo = 60 * 1000; //60 segundos, para pruebas
        
        
        //ANDRES MANUEL LÓPEZ OBRADOR
        List<Twitter_Listener> AMLO_Listeners = new ArrayList<>();
        String [] keywords_amlo = new String[]{"amlo", "andres manuel lopez obrador", "andres manuel", "lopez obrador", "lopezobrador", "peje", "ya sabes quien", "andres manuelovich", "yoconamlo", "amlopresidente"};
        for(String keyword: keywords_amlo)
        {
            Twitter_Listener listener = new Twitter_Listener(segundos_activo, keyword, "AMLO-"+keyword, "0", 0.1139,
                                                            "uPMj2A7Aa5wjtWHrdFWEsxxyF",
                                                            "1TPTR6Xi9Zu1413gcwUFTBCl3B4OtXJ4C6gBWozW3c4fgrqKbX",
                                                            "992459717707534337-kWpEMgsDQv0WGx1vVunLYBCKrtZBanP",
                                                            "0b7562wA8tPATVA7Zb54nyfw9luC4qZHhvaSRUbRniiuZ");
            AMLO_Listeners.add(listener);
            listener.start();
        }
        
        
        //RICARDO ANAYA CORTÉS
        List<Twitter_Listener> ANAYA_Listeners = new ArrayList<>();
        String [] keywords_anaya = new String[]{"ricardo anaya cortes", "ricardo anaya", "anaya", "anaya cortes", "ricardoanayac", "chicken little anaya", "canayin", "ricky riquin", "anayapresidente"};
        for(String keyword: keywords_anaya)
        {
            Twitter_Listener listener = new Twitter_Listener(segundos_activo, keyword, "ANAYA-"+keyword, "1", 0.0201,
                                                            "g3TosjVx5XhfaYhjhguPiUVVk",
                                                            "eLWrTkKuuQj4SLOAfffX82jjt6Ed72bPwoGsA16DcLHFDLoPLb",
                                                            "992649625768218624-zqLLBouzDcBnmwnMDKja5KfBzV8WP06",
                                                            "E6oE54mM9p4FWCANqVzP3p5A5mq3Of3JnqRdFlHr98uUh");
            ANAYA_Listeners.add(listener);
            listener.start();
        }
        
        
        //JOSÉ ANTONIO MEADE KURIBREÑA
        List<Twitter_Listener> MEADE_Listeners = new ArrayList<>();
        String [] keywords_meade = new String[]{"jose antonio meade kuribreña", "jose antonio meade", "meade", "joseameadek", "pepe meade", "yo mero", "toño meade", "meadepresidente"};
        for(String keyword: keywords_meade)
        {
            Twitter_Listener listener = new Twitter_Listener(segundos_activo, keyword, "MEADE-"+keyword, "2", 0.0374,
                                                            "Esx5GT7vm3bfp7NUXjUrIAXv2",
                                                            "9MmFHDKLREKpqrPBRBBykx7dhaVkMOfl6aRBF5HtycRrdbqpIm",
                                                            "1000056053856268288-g7s9EYzxMZhouwNGw1wFGObaTbLu9c",
                                                            "TlBZG021bDG68LYT0J7hJVaVjPPsVPAQpLCzO9Asjq4mV");
            MEADE_Listeners.add(listener);
            listener.start();
        }
        
        
        //JAIME RODRÍGUEZ CALDERÓN
        List<Twitter_Listener> BRONCO_Listeners = new ArrayList<>();
        String [] keywords_bronco = new String[]{"jaime rodriguez calderon", "jaime rodriguez", "el bronco", "rodriguez calderon", "candidato independiente", "broncopresidente", "mocha manos"};
        for(String keyword: keywords_bronco)
        {
            Twitter_Listener listener = new Twitter_Listener(segundos_activo, keyword, "BRONCO-"+keyword, "3", 0.0187,
                                                            "qoJj0V4oKXhuVy1M6zNBhoRQb",
                                                            "hiZmdb9GyseaqqqLEqOQMfi39W1xBuch34aNJsiU20h5PcTXjS",
                                                            "1000071509489008645-v34VtAKToxntSs2g51CYlS5qgF8Zyg",
                                                            "d0yMRQoaHpFipsA4YnzBbhWcfsPYnJ78lMiaGlmzJlG1y");
            BRONCO_Listeners.add(listener);
            listener.start();
        }
    }
    
}
