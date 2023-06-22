import java.util.ArrayList;
public class Spieler
{

    werkzeuge ut = new werkzeuge();
    public String visualisiereDeck( ArrayList<String> deck){
        String farbe;
        String karte;
        String visualisiertesDeck = "";
        int spacecounter = 0;
        int kartenanzahl = deck.size();
        for (int i = 0; i < kartenanzahl; i++){
            spacecounter = spacecounter + 1;
            int kartennummer = i+1;
            if (spacecounter == 9){
                visualisiertesDeck = visualisiertesDeck + "\n";
                spacecounter = 0;
            }
            visualisiertesDeck = visualisiertesDeck + "[" + ut.inttostring(kartennummer) + "] ";
            
            if (deck.get(i).startsWith("blau") || deck.get(i).startsWith("gruen") || deck.get(i).startsWith("gelb") || deck.get(i).startsWith("rot")){
                farbe = deck.get(i).split(":")[0];
                karte = deck.get(i).split(":")[1];
                if (karte.matches("[0-9]+")){
                    visualisiertesDeck = visualisiertesDeck + ut.hochstellen(farbe) + "e " + karte + " | ";
                }
                else{
                    if (karte.equals("zweiplus")){
                        visualisiertesDeck = visualisiertesDeck + ut.hochstellen(farbe) + "es" + " +2 | ";
                    }
                    else if (karte.equals("aussetzen")){
                        visualisiertesDeck = visualisiertesDeck + ut.hochstellen(farbe) + "es "+ "Setze aus | ";
                    }
                }
                
                
            } else{
                
                if (deck.get(i)=="farbwahl"){
                    visualisiertesDeck = visualisiertesDeck + "Farbwahl | ";
                }
                else if (deck.get(i)=="vierplus"){
                    visualisiertesDeck = visualisiertesDeck + "+4 | ";
                }
                
            }
        }
        return visualisiertesDeck;
    }
}
