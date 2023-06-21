 

import java.util.Random;
import java.util.ArrayList;
public class kartenstapel
{
    

    werkzeuge ut = new werkzeuge();
    public kartenstapel()
    {
    
    }

    public String visualisiereAktuelleKarte(String aktuelleKarte){
        String farbe;
        String karte;
        if (aktuelleKarte.startsWith("blau") || aktuelleKarte.startsWith("gruen") || aktuelleKarte.startsWith("gelb") || aktuelleKarte.startsWith("rot")){
            farbe = aktuelleKarte.split(":")[0];
            karte = aktuelleKarte.split(":")[1];
            if (karte.matches("[0-9]+")){
                return ut.hochstellen(farbe) + "e " + karte;
            }
            else{
                if (karte.equals("zweiplus")){
                    return ut.hochstellen(farbe) + "es" + " +2";
                }
                else if (karte.equals("aussetzen")){
                    return ut.hochstellen(farbe) + "es "+ "Setze aus";
                }
            }
            
            
        } else{
            
            if (aktuelleKarte=="farbwahl"){
                return "Farbwahl";
            }
            else if (aktuelleKarte=="vierplus"){
                return "+4";
            }
            
        }
        return "Fehler";
    }
    public ArrayList<String> generiereDeck(int anzahlKarten){
        ArrayList<String> deck = new ArrayList<String>();
        for (int i = 1; i <= anzahlKarten; i++){
            String newcard = ziehekarte();
            deck.add(newcard);
        }
        return deck;
    }
    public boolean selbeFarbe(String zuspielen, String aufStapel){
        // check if "blau:" or "gruen:" or "gelb:" or "rot:" is in zuspielen
        // no startswith
        if (zuspielen.contains("blau:") || zuspielen.contains("gruen:") || zuspielen.contains("gelb:") || zuspielen.contains("rot:")){
            // check if "blau:" or "gruen:" or "gelb:" or "rot:" is in aufStapel
            if (aufStapel.contains("blau:") || aufStapel.contains("gruen:") || aufStapel.contains("gelb:") || aufStapel.contains("rot:")){
                // check if the color is the same
                if (zuspielen.split(":")[0].equals(aufStapel.split(":")[0])){
                    return true;
                }
            }
        }
        // else if check if the numbers after the ":" are the same, if there is a ":"
        else if (zuspielen.contains(":") && aufStapel.contains(":")){
            if (zuspielen.split(":")[1].equals(aufStapel.split(":")[1])){
                return true;
            }
        }
        return false;
        
    }
    public String gibKartenInhalt(String karte){
        //return the raw splitted card
        String splitted = karte.split(":")[1];
        return splitted;
    }
    
    // Nicht von Hand geschrieben!
    public String ziehekarte(){
        Random rand = new Random();
        int random = rand.nextInt(208) +1;
        if (random<= 4){
            return "rot:0";
        }
        else if (random <= 8){
            return "blau:0";
        }
        else if (random <= 12){
            return "gruen:0";
        }
        else if (random <= 16){
            return "gelb:0";
        }
        else if (random <= 20){
            return "rot:1";
        }
        else if (random <= 24){
            return "blau:1";
        }
        else if (random <= 28){
            return "gruen:1";
        }
        else if (random <= 32){
            return "gelb:1";
        }
        else if (random <= 36){
            return "rot:2";
        }
        else if (random <= 40){
            return "blau:2";
        }
        else if (random <= 44){
            return "gruen:2";
        }
        else if (random <= 48){
            return "gelb:2";
        }
        else if (random <= 52){
            return "rot:3";
        }
        else if (random <= 56){
            return "blau:3";
        }
        else if (random <= 60){
            return "gruen:3";
        }
        else if (random <= 64){
            return "gelb:3";
        }
        else if (random <= 68){
            return "rot:4";
        }
        else if (random <= 72){
            return "blau:4";
        }
        else if (random <= 76){
            return "gruen:4";
        }
        else if (random <= 80){
            return "gelb:4";
        }
        else if (random <= 84){
            return "rot:5";
        }
        else if (random <= 88){
            return "blau:5";
        }
        else if (random <= 92){
            return "gruen:5";
        }
        else if (random <= 96){
            return "gelb:5";
        }
        else if (random <= 100){
            return "rot:6";
        }
        else if (random <= 104){
            return "blau:6";
        }
        else if (random <= 108){
            return "gruen:6";
        }
        else if (random <= 112){
            return "gelb:6";
        }
        else if (random <= 116){
            return "rot:7";
        }
        else if (random <= 120){
            return "blau:7";
        }
        else if (random <= 124){
            return "gruen:7";
        }
        //until 9
        else if (random <= 128){
            return "gelb:7";
        }
        else if (random <= 132){
            return "rot:8";
        }
        else if (random <= 136){
            return "blau:8";
        }
        else if (random <= 140){
            return "gruen:8";
        }
        else if (random <= 144){
            return "gelb:8";
        }
        else if (random <= 148){
            return "rot:9";
        }
        else if (random <= 152){
            return "blau:9";
        }
        else if (random <= 156){
            return "gruen:9";
        }
        else if (random <= 160){
            return "gelb:9";
        }
        else if (random <= 164){
            return "rot:zweiplus";
        }
        else if (random <= 168){
            return "blau:zweiplus";
        }
        else if (random <= 172){
            return "gruen:zweiplus";
        }
        else if (random <= 176){
            return "gelb:zweiplus";
        }
        else if (random <= 180){
            return "rot:aussetzen";
        }
        else if (random <= 184){
            return "blau:aussetzen";
        }
        else if (random <= 188){
            return "gruen:aussetzen";
        }
        else if (random <= 192){
            return "gelb:aussetzen";
        }
        else if (random <= 200){
            return "vierplus";
        }
        else if (random <= 208){
            return "farbwahl";
        }
        return "error";
    }
}
