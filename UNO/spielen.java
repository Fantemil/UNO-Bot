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
    public boolean aussetzen = false;
    public boolean zweiplus = false;
    public boolean vierplus = false;
    public boolean farbwahl = false;
    public ArrayList<String> spielerdeck;
    public ArrayList<String> computerdeck;
    public String stapel;
    public String vierpluswunsch;

    public void visualisiereSpielfeld(int zug){
        if(zug == 2){
            ut.clr();
            ut.print("Computer: " + computer.visualisiereDeck(computerdeck)+ "\n\n\n\n");
            ut.print("Stapel: " + kartenstapel.visualisiereAktuelleKarte(stapel)+"\n\n\n\n"); 
            ut.print("> "+username +": " + spieler.visualisiereDeck(spielerdeck));
        }
        else if (zug == 1){
            ut.clr();
            ut.print("> Computer: " + computer.visualisiereDeck(computerdeck)+ "\n\n\n\n");
            ut.print("Stapel: " + kartenstapel.visualisiereAktuelleKarte(stapel)+"\n\n\n\n");
            ut.print(username +": " + spieler.visualisiereDeck(spielerdeck));

        }
    }
    public void ausführen(){
        
        ut.ascii();
        ut.print("\n Willkommen zu UNO in Java!\n\nVon Emil Toth, Jakob Schmid und Ulrich Weber");
        ut.print("Setze deinen Spielername: ");
        username = ut.input();
        ut.print("Mit wie vielen Karten wird gespielt? (Empfohlen: 8)");
        kartenanzahl = Integer.parseInt(ut.input());
        spielerdeck = kartenstapel.generiereDeck(kartenanzahl);
        computerdeck = kartenstapel.generiereDeck(kartenanzahl);
        stapel = kartenstapel.ziehekarte();
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
            if (aussetzen == false) {
                visualisiereSpielfeld(2);

                //check if any of the cards can be played by checking if any number or color matches
                boolean canplay = false;
                for (int i = 0; i < spielerdeck.size(); i++){
                    ut.print(ut.inttostring(i));
                    if (kartenstapel.selbeFarbe(spielerdeck.get(i), stapel)){
                        canplay = true;
                    }
                }
                if (canplay == false){
                    // check if the player has a vierplus card or a farbwahl card
                    for (int i = 0; i < spielerdeck.size(); i++){
                        if (spielerdeck.get(i).contains("vierplus")){
                            canplay = true;
                            break;
                        }
                        else if (spielerdeck.get(i).contains("farbwahl")){
                            canplay = true;
                            break;
                        }
                    }
                    if (canplay == false){
                        ut.print("Du kannst keine Karte spielen! Ziehe eine Karte! (Enter drücken zum weiterspielen)");
                        ut.input();
                        spielerdeck.add(kartenstapel.ziehekarte());
                        continue;
                    }
                }
                ut.print("Welche Karte möchtest du spielen?");
                int karte = ut.stringtoint(ut.input());
                String zuspielen = spielerdeck.get(karte-1);
                if (kartenstapel.selbeFarbe(zuspielen, stapel)){
                    stapel = zuspielen;
                    spielerdeck.remove(karte-1);   
                    visualisiereSpielfeld(2);
                }
                else{
                    //check if zuspielen is a farbwahl card or a vierplus card
                    if (zuspielen.contains("vierplus")){
                        vierplus = true;
                        stapel = zuspielen;
                        spielerdeck.remove(karte-1);
                        visualisiereSpielfeld(2);
                        ut.print("Du hast eine Vierplus Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Grün [3] Blau [4] Gelb");
                        int farbe = ut.stringtoint(ut.input());
                        while(true){
                            if (farbe == 1){
                                vierpluswunsch = "rot";
                                break;
                            }
                            else if (farbe == 2){
                                vierpluswunsch = "grün";
                                break;
                            }
                            else if (farbe == 3){
                                vierpluswunsch = "blau";
                                break;
                            }
                            else if (farbe == 4){
                                vierpluswunsch = "gelb";
                                break;
                            }
                            else{
                                ut.print("Bitte wähle eine der vier Farben aus!");
                                farbe = ut.stringtoint(ut.input());
                            }
                        }
                    }
                    else if (zuspielen.contains("farbwahl")){
                        farbwahl = true;
                        stapel = zuspielen;
                        spielerdeck.remove(karte-1);
                        visualisiereSpielfeld(2);
                        ut.print("Du hast eine Farbwahl Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Grün [3] Blau [4] Gelb");
                        int farbe = ut.stringtoint(ut.input());
                        while(true){
                            if (farbe == 1){
                                vierpluswunsch = "rot";
                                break;
                            }
                            else if (farbe == 2){
                                vierpluswunsch = "grün";
                                break;
                            }
                            else if (farbe == 3){
                                vierpluswunsch = "blau";
                                break;
                            }
                            else if (farbe == 4){
                                vierpluswunsch = "gelb";
                                break;
                            }
                            else{
                                ut.print("Bitte wähle eine der vier Farben aus!");
                                farbe = ut.stringtoint(ut.input());
                            }
                        }
                    }
                    else{
                        ut.print("Diese Karte kann nicht gespielt werden!");
                        ut.input();
                        continue;
                    }
                    
                }
            } else {
                aussetzen = false;
            }
            
            
        
        }

        
    

    }

    
}
