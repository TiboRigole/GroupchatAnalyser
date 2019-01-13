import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class PersonenPercentages {

    public static String fileNaam = "messages.json";

    public static void main(String[] args) {

        //naam , aantalMessages
        HashMap<String, Integer> personen = new HashMap<>();

        // openen van de JSONFile
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(fileNaam));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;


        // titel van de groupchat ophalen in printen
        String chatNaam = (String) jo.get("title");
        System.out.println(chatNaam);



        // messages object openen en over itereren
        JSONArray ja = (JSONArray) jo.get("messages");

        int totaalAantalMessages = ja.size();


        for (int i = 0; i < ja.size(); i++) {


            JSONObject message = (JSONObject) ja.get(i);
            String name = (String) message.get("sender_name");

            // indien reeds aangemaakt
            if (personen.containsKey(name)) {

                Integer aantalBerichtjes = personen.get(name);
                personen.put(name, aantalBerichtjes + 1);

            } else {
                personen.put(name, new Integer(1));
            }

        }

        System.out.println("done");

        System.out.println();
        System.out.println();
        System.out.println("totaal # berichten:" + totaalAantalMessages);

        System.out.println();
        System.out.println();

        //namen + aantalMessages
        for (Map.Entry<String, Integer> entry : personen.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());

        }


        DecimalFormat df = new DecimalFormat("##.##"); //afronden tot 2 na de komma

        System.out.println();
        System.out.println("percentages:");

        for (Map.Entry<String, Integer> entry : personen.entrySet()) {

            String key = entry.getKey();
            Object value = entry.getValue();
            double percentage = calculatePercentage((Integer)value, totaalAantalMessages);
            System.out.println(key + ": " + df.format(percentage)+ " %");

        }


    }

    public static double calculatePercentage(Integer aandeel, Integer totaal){
        double a = aandeel;
        double t = totaal;
        return (a/t) * 100;
    }

}
