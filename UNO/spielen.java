import java.util.ArrayList;

public class spielen
{

    public String username;
    // definieren von ut (ut steht für "utility"). bezieht sich auf die "werkzeuge" klasse
    public werkzeuge ut = new werkzeuge();
    public Spieler spieler = new Spieler();
    public Computer computer = new Computer();
    public kartenstapel kartenstapel = new kartenstapel();
    public int kartenanzahl;
    public void ausführen(){
        
        ut.ascii();
        ut.print("\n Willkommen zu UNO in Java!\n\nVon Emil Toth, Jakob Schmid und Ulrich Weber");
        ut.print("Setze deinen Spielername: ");
        username = ut.input();
        ut.print("Mit wie vielen Karten wird gespielt? (Empfohlen: 8)");
        kartenanzahl = Integer.parseInt(ut.input());
        ArrayList<String> spielerdeck = kartenstapel.generiereDeck(kartenanzahl);
        ArrayList<String> computerdeck = kartenstapel.generiereDeck(kartenanzahl);
        String startkarte = kartenstapel.ziehekarte();
        ut.clr();
        while(true){
            if (spielerdeck.size() == 0){
                ut.clr();
                ut.print("Du hast gewonnen!");
                break;
            }
            else if (computerdeck.size() == 0){
                ut.clr();
                ut.print("Der Computer hat gewonnen!");
                break;
            }
            ut.clr();
            ut.print("Computer: " + computer.visualisiereDeck(computerdeck)+ "\n\n\n\n");
            ut.print("Stapel: " + kartenstapel.visualisiereAktuelleKarte(startkarte)+"\n\n\n\n"); 
            ut.print("> "+username +": " + spieler.visualisiereDeck(spielerdeck));
            ut.print("Welche Karte möchtest du spielen?");
            int karte = ut.stringtoint(ut.input());
        
        }

        
    

    }

    
}
