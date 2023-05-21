import java.util.ArrayList;

public class Computer
{
    public String visualisiereDeck(ArrayList<String> deck){
        int kartenanzahl = deck.size();
        String visualisiertesDeck = "";
        // for i in deck
        for (int i = 0; i < kartenanzahl; i++){
            int kartennummer = i+1;
            visualisiertesDeck = visualisiertesDeck + "[" + kartennummer + "] ? | ";
        }
        return visualisiertesDeck;
    }
}
