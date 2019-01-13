import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NamenHistorie {

    public static String filenaam = "messages.json";

    public static void main(String[] args) {

        ArrayList<GCNaam> gcnamen = new ArrayList<GCNaam>();

        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(filenaam));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;


        String lastName = (String) jo.get("title");
        System.out.println(lastName);

        // getting messages
        JSONArray ja = (JSONArray) jo.get("messages");

        for (int i = 0; i < ja.size(); i++) {
            JSONObject jso = (JSONObject) ja.get(i);

            String name = (String) jso.get("sender_name");
            String content = (String) jso.get("content");

            //System.out.println(name);
            //System.out.println(content);

            if((content !=  null) && (content.contains(" heeft de groep de naam ") ) ) {

                gcnamen.add(new GCNaam(name, content));

            }

        }

        System.out.println("done");

        //formatting
        for (GCNaam gcNaam : gcnamen){
            gcNaam.nieuweNaam = gcNaam.nieuweNaam.substring(gcNaam.nieuweNaam.indexOf("'")+1);
            gcNaam.nieuweNaam = gcNaam.nieuweNaam.substring(0, gcNaam.nieuweNaam.indexOf("'"));
        }

        for (GCNaam gcNaam : gcnamen) {
            System.out.println(gcNaam.nieuweNaam +", door: " + gcNaam.setter);
        }


    }
}
