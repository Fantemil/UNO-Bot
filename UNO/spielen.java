 

import java.util.ArrayList;

public class spielen
{

    public String username;
    // definieren von ut (ut steht fuer "utility"). bezieht sich auf die "werkzeuge" klasse
    public werkzeuge ut = new werkzeuge();
    public Spieler spieler = new Spieler();
    public Computer computer = new Computer();
    public kartenstapel kartenstapel = new kartenstapel();
    public int kartenanzahl;
    public boolean aussetzen = false;
    public boolean zweiplus = false;
    public boolean vierplus = false;
    public boolean farbwahl = false;
    public boolean spielergezogen = false;
    public ArrayList<String> spielerdeck;
    public ArrayList<String> computerdeck = new ArrayList<>();
    public ArrayList<String> computerdeckkopie = new ArrayList<>();
    public String stapel;
    public String wunsch;
    public int indexofbest;
    boolean canplay;
    String[] farben = {"blau", "gruen", "gelb", "rot"};
    public spielen(){
    

        ut.ascii();
        ut.print("\n Willkommen zu UNO in Java!\n\nVon Emil Toth, Jakob Schmid und Ulrich Weber");
        ut.print("Setze deinen Spielername: ");
        username = ut.input();
        ut.print("Mit wie vielen Karten wird gespielt? (Empfohlen: 8)");
        kartenanzahl = Integer.parseInt(ut.input());
        spielerdeck = kartenstapel.generiereDeck(kartenanzahl);
        computerdeck = kartenstapel.generiereDeck(kartenanzahl);
        while (true){

            stapel = kartenstapel.ziehekarte();
            if (stapel.startsWith("blau") || stapel.startsWith("gruen") || stapel.startsWith("gelb") || stapel.startsWith("rot")){
                break;
            } else {
                stapel = kartenstapel.ziehekarte();
            }
        }
        ut.clr();
        while(true){
            spielergezogen = false;
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
                if (vierplus){
                    ut.print("Du musst 4 Karten ziehen! (Enter druecken zum weiterspielen)");
                    ut.input();
                    for (int i = 0; i < 4; i++){
                        spielerdeck.add(kartenstapel.ziehekarte());
                    }
                    vierplus = false;
                }
                else if (zweiplus){
                    ut.print("Du musst 2 Karten ziehen! (Enter druecken zum weiterspielen)");
                    ut.input();
                    for (int i = 0; i < 2; i++){
                        spielerdeck.add(kartenstapel.ziehekarte());
                    }
                    zweiplus = false;
                }

                visualisiereSpielfeld(2);
                if (farbwahl == false){

                    
                    //check if any of the cards can be played by checking if any number or color matches
                    canplay = false;
                    for (int i = 0; i < spielerdeck.size(); i++){
                        if (kartenstapel.selbeFarbe(spielerdeck.get(i), stapel)){
                            canplay = true;
                        } else if (kartenstapel.gibKartenInhalt(spielerdeck.get(i)).contains(kartenstapel.gibKartenInhalt(stapel))){
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
                            ut.print("Du kannst keine Karte spielen! Ziehe eine Karte! (Enter druecken zum weiterspielen)");
                            ut.input();
                            spielerdeck.add(kartenstapel.ziehekarte());
                            spielergezogen = true;
                        }
                    }
                    if (spielergezogen == false){
                    ut.print("Welche Karte moechtest du spielen?");
                    int karte = ut.stringtoint(ut.input());
                    String zuspielen = spielerdeck.get(karte-1);
                    if (kartenstapel.selbeFarbe(zuspielen, stapel)){
                        if (zuspielen.contains("aussetzen")){
                            aussetzen = true;
                        } else if (zuspielen.contains("zweiplus")){
                            zweiplus = true;
                        }
                        stapel = zuspielen;
                        spielerdeck.remove(karte-1);   
                        visualisiereSpielfeld(2);
                    
                    } /*only do else if statement*/
                    else if (kartenstapel.gibKartenInhalt(zuspielen).contains(kartenstapel.gibKartenInhalt(stapel))){
                        if (zuspielen.contains("aussetzen")){
                            aussetzen = true;
                        } else if (zuspielen.contains("zweiplus")){
                            zweiplus = true;
                        }
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
                            ut.print("Du hast eine Vierplus Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Gruen [3] Blau [4] Gelb");
                            int farbe = ut.stringtoint(ut.input());
                            while(true){
                                if (farbe == 1){
                                    farbwahl = true;
                                    wunsch = "rot";
                                    break;
                                }
                                else if (farbe == 2){
                                    farbwahl = true;
                                    wunsch = "gruen";
                                    break;
                                }
                                else if (farbe == 3){
                                    farbwahl = true;
                                    wunsch = "blau";
                                    break;
                                }
                                else if (farbe == 4){
                                    farbwahl = true;
                                    wunsch = "gelb";
                                    break;
                                }
                                else{
                                    ut.print("Bitte waehle eine der vier Farben aus!");
                                    farbe = ut.stringtoint(ut.input());
                                }
                            }
                        }
                        else if (zuspielen.contains("farbwahl")){
                            farbwahl = true;
                            stapel = zuspielen;
                            spielerdeck.remove(karte-1);
                            visualisiereSpielfeld(2);
                            ut.print("Du hast eine Farbwahl Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Gruen [3] Blau [4] Gelb");
                            int farbe = ut.stringtoint(ut.input());
                            while(true){
                                if (farbe == 1){
                                    wunsch = "rot";
                                    break;
                                }
                                else if (farbe == 2){
                                    wunsch = "gruen";
                                    break;
                                }
                                else if (farbe == 3){
                                    wunsch = "blau";
                                    break;
                                }
                                else if (farbe == 4){
                                    wunsch = "gelb";
                                    break;
                                }
                                else{
                                    ut.print("Bitte waehle eine der vier Farben aus!");
                                    farbe = ut.stringtoint(ut.input());
                                }
                            }
                        } else if (zuspielen.contains("zweiplus")){
                            zweiplus = true;
                            stapel = zuspielen;
                            spielerdeck.remove(karte-1);
                            visualisiereSpielfeld(2);

                        } else if (zuspielen.contains("aussetzen")){
                            aussetzen = true;
                            stapel = zuspielen;
                            spielerdeck.remove(karte-1);
                            visualisiereSpielfeld(2);}

                        else{
                            ut.print("Diese Karte kann nicht gespielt werden!");
                            ut.input();
                            continue;
                        }
                    
                        }}
                } else if (farbwahl){
                    visualisiereSpielfeld(2);
                    ut.print("Computer wuenscht sich "+wunsch+"!");
                    canplay = false;
                    //for i in spielerdeck:
                    for (int i = 0; i < spielerdeck.size(); i++){
                        if (kartenstapel.selbeFarbe(spielerdeck.get(i), wunsch+":")){
                            canplay = true;
                        }
                    }
                    if (canplay == false){
                        ut.print("Du kannst keine Karte spielen! Ziehe eine Karte! (Enter druecken zum weiterspielen)");
                        ut.input();
                        spielerdeck.add(kartenstapel.ziehekarte());
                        spielergezogen = true;
                    }
                    if (spielergezogen == false){
                        ut.print("Welche Karte moechtest du spielen? Schwarze Sonderkarten koennen nicht gespielt werden!");
                        int karte = ut.stringtoint(ut.input());
                        String zuspielen = spielerdeck.get(karte-1);
                        if (kartenstapel.selbeFarbe(zuspielen, wunsch+":")){
                            stapel = zuspielen;
                            spielerdeck.remove(karte-1);
                            visualisiereSpielfeld(2);
                            if (zuspielen.contains("zweiplus")){
                                zweiplus = true;
                            } else if (zuspielen.contains("aussetzen")){
                                aussetzen = true;
                            }
                            farbwahl = false;
                        }
                        
                        
                        else {
                            ut.print("Diese Karte kann nicht gespielt werden!");
                            ut.input();
                            continue;
                        }
                }
            } else {
                aussetzen = false;
            }
            






            //computerzug
            if (aussetzen == false){

                if (vierplus){
                    for (int i = 0; i < 4; i++){
                        computerdeck.add(kartenstapel.ziehekarte());
                    }
                    vierplus = false;
                
                } else if (zweiplus){
                    for (int i = 0; i < 2; i++){
                        computerdeck.add(kartenstapel.ziehekarte());
                    }
                    zweiplus = false;
                }
                visualisiereSpielfeld(1);
                if (farbwahl){
                    for (int i = 0; i < computerdeck.size(); i++){
                        if (kartenstapel.selbeFarbe(computerdeck.get(i), wunsch+":")){
                            canplay = true;
                        }
                    }
                    if (canplay == false){
                        ut.print("Der Computer kann keine Karte spielen! Er zieht eine Karte! (Enter druecken zum weiterspielen)");
                        ut.input();
                        computerdeck.add(kartenstapel.ziehekarte());
                        visualisiereSpielfeld(1);

                        continue;
                    }
                    // for i in range(len(computerdeck)):
                    computerdeckkopie.clear();
                    computerdeckkopie.addAll(computerdeck);
                    for (int i = 0; i < computerdeckkopie.size(); i++){
                        // if the card is a special card remove it from computerdeckkopie
                        if (computerdeckkopie.get(i).contains("vierplus") || computerdeckkopie.get(i).contains("farbwahl")){
                            computerdeckkopie.remove(i);
                        }
                        
                    }
                    while (true){
                        // if computerdeckkopie is empty, the computer has no playable cards
                        if (computerdeckkopie.size() == 0){
                            ut.print("Der Computer kann keine Karte spielen! Er zieht eine Karte! (Enter druecken zum weiterspielen)");
                            ut.input();
                            computerdeck.add(kartenstapel.ziehekarte());
                            visualisiereSpielfeld(1);
                            break;
                        }
                        indexofbest = computer.ermittleWert(computerdeckkopie);

                        //check if the card is playable by checking if the color is the same as in wunsch
                        if (kartenstapel.selbeFarbe(computerdeckkopie.get(indexofbest), wunsch+":")){
                            stapel = computerdeckkopie.get(indexofbest);
                            //for i in computerdeck:
                            for (int i = 0; i < computerdeck.size(); i++){
                                if (computerdeck.get(i).equals(computerdeckkopie.get(indexofbest))){
                                    computerdeck.remove(i);
                                    break;
                                }
                            }
                            if (computerdeckkopie.get(indexofbest).contains("zweiplus")){
                                zweiplus = true;
                            } else if (computerdeckkopie.get(indexofbest).contains("aussetzen")){
                                aussetzen = true;
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt! (Enter druecken zum weiterspielen)");
                            ut.input();
                            farbwahl = false;
                            
                            break;
                        } // else if it has the same number
                        //only prepare the if statement
                        else{
                            computerdeckkopie.remove(indexofbest);
                        }
                    }
                    
                } else {
                    computerdeckkopie.clear();
                    computerdeckkopie.addAll(computerdeck);
                    
                    while (true){
                        if (computerdeckkopie.size() == 0){
                            ut.print("Der Computer kann keine Karte spielen! Er zieht eine Karte! (Enter druecken zum weiterspielen)");
                            ut.input();
                            computerdeck.add(kartenstapel.ziehekarte());
                            visualisiereSpielfeld(1);
                            break;
                        }
                        indexofbest = computer.ermittleWert(computerdeckkopie);
                        //check if the card is playable by checking if the color is the same as in wunsch
                        if (kartenstapel.selbeFarbe(computerdeckkopie.get(indexofbest), stapel)){
                            stapel = computerdeckkopie.get(indexofbest);
                            //for i in computerdeck:
                            for (int i = 0; i < computerdeck.size(); i++){
                                if (computerdeck.get(i).equals(computerdeckkopie.get(indexofbest))){
                                    computerdeck.remove(i);
                                    break;
                                }
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt! (Enter druecken zum weiterspielen)");
                            ut.input();
                            break;
                        } // else if the card is a special card
                        else if (computerdeckkopie.get(indexofbest).contains("vierplus")){
                            stapel = computerdeckkopie.get(indexofbest);
                            //for i in computerdeck:
                            for (int i = 0; i < computerdeck.size(); i++){
                                if (computerdeck.get(i).equals(computerdeckkopie.get(indexofbest))){
                                    computerdeck.remove(i);
                                    break;
                                }
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt! (Enter druecken zum weiterspielen)");
                            ut.input();
                            vierplus = true;
                            farbwahl = true;
                            // pick a random number from 0 to 3
                            int random = (int) (Math.random() * 4);
                            wunsch = farben[random];
                            
                            break;
                        } else if (computerdeckkopie.get(indexofbest).contains("farbwahl")){
                            stapel = computerdeckkopie.get(indexofbest);
                            // for i in computerdeck:
                            for (int i = 0; i < computerdeck.size(); i++){
                                if (computerdeck.get(i).equals(computerdeckkopie.get(indexofbest))){
                                    computerdeck.remove(i);
                                    break;
                                }
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine farbwahl Karte gespielt! (Enter druecken zum weiterspielen)");
                            ut.input();
                            farbwahl = true;
                            //pick a random number from 0 to 3
                            int random = (int) (Math.random() * 4);
                            wunsch = farben[random];
                            break;
                        } else if (kartenstapel.gibKartenInhalt(computerdeckkopie.get(indexofbest)).equals(kartenstapel.gibKartenInhalt(stapel))){
                            stapel = computerdeckkopie.get(indexofbest);
                            //for i in computerdeck:
                            for (int i = 0; i < computerdeck.size(); i++){
                                if (computerdeck.get(i).equals(computerdeckkopie.get(indexofbest))){
                                    computerdeck.remove(i);
                                    break;
                                }
                            }
                            if (computerdeckkopie.get(indexofbest).contains("zweiplus")){
                                zweiplus = true;
                            } else if (computerdeckkopie.get(indexofbest).contains("aussetzen")){
                                aussetzen = true;
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt (farben gekreuzt)! (Enter druecken zum weiterspielen)");
                            ut.input();
                            break;
                        }
                        else{
                            computerdeckkopie.remove(indexofbest);
                        }
                    }
                    
                }
        
            } else {
                ut.print("Der Computer setzt aus! (Enter druecken zum weiterspielen)");
                ut.input();
                aussetzen = false;
            }
        
            }

        
    

    }}
    
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
    
    
}
