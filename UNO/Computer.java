 

import java.util.ArrayList;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.*;
public class Computer
{
    private Dictionary<String, Integer> werte = new Hashtable<>();
    public Computer(){
        
        werte.put("vierplus", 100);
        werte.put("zweiplus", 99);
        werte.put("farbwahl", 98);
        werte.put("aussetzen", 101);
        werte.put("9", 96);
        werte.put("8", 96);
        werte.put("7", 96);
        werte.put("6", 96);
        werte.put("5", 96);
        werte.put("4", 96);
        werte.put("3", 96);
        werte.put("2", 96);
        werte.put("1", 96);
        werte.put("0", 96);
    }   
    public String visualisiereDeck(ArrayList<String> deck){
        int kartenanzahl = deck.size();
        String visualisiertesDeck = "";
        for (int i = 0; i < kartenanzahl; i++){
            int kartennummer = i+1;
            visualisiertesDeck = visualisiertesDeck + "[" + kartennummer + "] ? | ";
        }
        return visualisiertesDeck;
    }
    
    public int ermittleWert(ArrayList<String> deck){
        int besterwertindex = 0;
        String currentvalue;
        String[] currentvalue_array;
        int highestvalue = 0;
        for (int i = 0; i < deck.size(); i++){
            currentvalue = deck.get(i);
            if (currentvalue.contains(":")){
                currentvalue_array = currentvalue.split(":");
                currentvalue = currentvalue_array[1];
            }
            if (werte.get(currentvalue) > highestvalue){
                highestvalue = werte.get(currentvalue);
                besterwertindex = i;
            }
            
        }
        return besterwertindex;

    }
}
