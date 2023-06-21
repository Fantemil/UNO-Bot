 

import java.util.ArrayList;

public class spielen
{
    // definieren von username (der name des spielers)
    public String username;

    // definieren von ut (ut steht fuer "utility"). bezieht sich auf die "werkzeuge" klasse
    public werkzeuge ut = new werkzeuge();

    // definieren von spieler und computer (spieler und computer sind objekte der klasse "Spieler" und "Computer")
    public Spieler spieler = new Spieler();
    public Computer computer = new Computer();

    /*  definieren von kartenstapel (kartenstapel ist ein objekt der klasse "Kartenstapel") und ist für das ziehen von karten zustaendig
    sowie fuer andere kartenbasierte funktionen */
    public kartenstapel kartenstapel = new kartenstapel();

    // definieren von kartenanzahl (kartenanzahl ist die anzahl der karten, die der spieler und der computer zu beginn des spiels erhalten)
    public int kartenanzahl;

    // definieren von aussetzen, zweiplus, vierplus, farbwahl (diese variablen sind fuer die funktionen der karten zustaendig wenn diese gelegt werden)
    public boolean aussetzen = false;
    public boolean zweiplus = false;
    public boolean vierplus = false;
    public boolean farbwahl = false;

    // definieren von spielergezogen (spielergezogen ist eine variable, die angibt, ob der spieler bereits gezogen hat)
    public boolean spielergezogen = false;

    // definieren von spielerdeck (spielerdeck ist ein array mit dynamischer länge (je nach kartenanzahl), welches die karten des spielers speichert)
    public ArrayList<String> spielerdeck;

    // definieren von computerdeck (computerdeck ist ein array mit dynamischer länge (je nach kartenanzahl), welches die karten des computers speichert)
    public ArrayList<String> computerdeck = new ArrayList<>();

    // definieren von computerdeckkopie (computerdeckkopie ist fuer funktionen des Computers notwendig)
    public ArrayList<String> computerdeckkopie = new ArrayList<>();

    // definieren von stapel (die variable stapel speichert die aktuelle karte, die auf dem stapel liegt)
    public String stapel;

    // definieren von wunsch (wunsch ist die farbe, die der spieler/computer waehlt, wenn er eine farbwahl/vierplus karte legt)
    public String wunsch;

    // definieren von blau, gruen, gelb, rot (diese variablen benutzt der computer um bei einer farbwahl/vierplus karte die farbe zu waehlen)
    public int blau;
    public int gruen;
    public int gelb;
    public int rot;

    // definieren von indexofbest (indexofbest ist der index der besten karte, die der computer legen könnte)
    public int indexofbest;

    // definieren von canplay (canplay ist eine variable, die angibt, ob der spieler/computer eine karte legen kann)
    boolean canplay;

    // definieren von farben (farben ist ein array, das alle farben enthaelt damit der computer im zweifelsfall eine farbe waehlen kann)
    String[] farben = {"blau", "gruen", "gelb", "rot"};
    public spielen(){
    
        // ut.ascii() ist eine funktion, die den text "UNO" in ascii art ausgibt
        ut.ascii();

        // ut.print() ist eine abstrahierende Methode für System.out.println()
        ut.print("\n Willkommen zu UNO in Java!\n\nVon Emil Toth, Jakob Schmid und Ulrich Weber");
        ut.print("Setze deinen Spielername: ");

        // ut.input() frägt nach einer eingabe und gibt diese zurück
        username = ut.input();
        ut.print("Mit wie vielen Karten wird gespielt? (Empfohlen: 8)");

        //ut.stringtoint() abstrahiert Integer.parseInt() und wandelt eine String in einen Integer um
        kartenanzahl = ut.stringtoint(ut.input());

        // kartenstapel.generiereDeck() generiert eine arraylist mit einem deck voller karten und gibt dieses zurück
        spielerdeck = kartenstapel.generiereDeck(kartenanzahl);
        computerdeck = kartenstapel.generiereDeck(kartenanzahl);
        while (true){
            // kartenstapel.ziehekarte() zieht eine karte aus einem unendlichen deck mit wahrscheinlichkeiten und gibt diese zurück (wird auch in generiereDeck() verwendet)
            stapel = kartenstapel.ziehekarte();

            //startWith() ist eine funktion, die überprüft, ob eine string mit einem gewissen inhalt beginnt (eingebaut in java)
            if (stapel.startsWith("blau") || stapel.startsWith("gruen") || stapel.startsWith("gelb") || stapel.startsWith("rot")){
                // break beendet eine schleife
                break;
            } else {
                stapel = kartenstapel.ziehekarte();
            }
        }

        // ut.clr() ist eine funktion, die die konsole leert
        ut.clr();
        while(true){
            //size() ist eine funktion, die die anzahl der inhalte eines arrays zurückgibt (eingebaut in java)
            if (spielerdeck.size() == 0){
                    ut.print("Du hast gewonnen! (Druecke Enter)");
                    ut.input();
                    ut.clr();
                    ut.ascii();
                    ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank fuers spielen!");
                    break;
                }
            else if (computerdeck.size() == 0){
                ut.print("Der Computer hat gewonnen! (Druecke Enter)");
                ut.input();
                ut.clr();
                ut.ascii();
                ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank fuers spielen!");
                break;
            }

            if (aussetzen == false) {
                spielergezogen = false;
                
                if (vierplus){
                    ut.print("Du musst 4 Karten ziehen! (Enter druecken zum weiterspielen)");
                    ut.input();
                    for (int i = 0; i < 4; i++){
                        //add() ist eine funktion, die ein element zu einem array hinzufügt (eingebaut in java)
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
                // visualisiereSpielfeld() ist eine funktion, die sofort das spielfeld aktualisiert, indem es die anderen visualisierungs funktionen aufruft und diese zusammenträgt.
                visualisiereSpielfeld(2);
                    if (farbwahl == false){

                        

                        canplay = false;
                        for (int i = 0; i < spielerdeck.size(); i++){
                            // kartenstapel.selbeFarbe() ist eine funktion, die überprüft, ob zwei karten die gleiche farbe haben
                            if (kartenstapel.selbeFarbe(spielerdeck.get(i), stapel)){
                                canplay = true;
                            // get() ist eine funktion für ArrayLists die den inhalt eines bestimmten index zurückgibt
                            // contains() ist eine funktion für Strings, die überprüft, ob ein string einen bestimmten inhalt hat
                            } else if (!spielerdeck.get(i).contains("vierplus") && !spielerdeck.get(i).contains("farbwahl")){
                                // kartenstapel.gibKartenInhalt() ist eine funktion, die den inhalt einer karte zurückgibt indem er die farbe ausblendet
                                if (kartenstapel.gibKartenInhalt(spielerdeck.get(i)).contains(kartenstapel.gibKartenInhalt(stapel))){
                                    canplay = true;
                                }
                                
                            } else if (spielerdeck.get(i).contains("vierplus")){
                                canplay = true;
                            } else if (spielerdeck.get(i).contains("farbwahl")){
                                canplay = true;
                            }
                        }
                        if (canplay == false){
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
                        //zuspielen speichert die karte selbst, die der spieler spielen möchte
                        String zuspielen;
                        // karte speichert die nummer der karte, die der spieler spielen möchte
                        int karte;
                        ut.print("Welche Karte moechtest du spielen?");
                        while (true){
                            // inputter speichert die eingabe des spielers temporär
                            String inputter = ut.input();
                            // matches() ist eine funktion für Strings, die regex (regular expressions) also die suche nach bestimmten mustern ermöglicht
                            if (inputter.matches("[0-9]+")){
                                karte = ut.stringtoint(inputter);
                                zuspielen = spielerdeck.get(karte-1);
                                break;
                            } else{
                                ut.print("Bitte gib eine Zahl ein!");
                            }
                            
                        }
                        if (kartenstapel.selbeFarbe(zuspielen, stapel)){
                            if (zuspielen.contains("aussetzen")){
                                aussetzen = true;
                            } else if (zuspielen.contains("zweiplus")){
                                zweiplus = true;
                            }
                            stapel = zuspielen;
                            // remove() ist eine funktion für ArrayLists, die ein element aus dem array entfernt
                            spielerdeck.remove(karte-1);   
                            visualisiereSpielfeld(2);
                        
                        } 
                        else if (!zuspielen.contains("vierplus") && !zuspielen.contains("farbwahl")){
                            if (kartenstapel.gibKartenInhalt(zuspielen).contains(kartenstapel.gibKartenInhalt(stapel))){
                                if (zuspielen.contains("aussetzen")){
                                    aussetzen = true;
                                } else if (zuspielen.contains("zweiplus")){
                                    zweiplus = true;
                                }
                                stapel = zuspielen;
                                spielerdeck.remove(karte-1);
                                visualisiereSpielfeld(2);
                            }
                        } else{
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
                    } else if (farbwahl == true){
                        visualisiereSpielfeld(2);
                        ut.print("Farbwunsch offen: "+wunsch+"!");
                        canplay = false;
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
                            }
            } else if (aussetzen==true){
                visualisiereSpielfeld(2);
                aussetzen = false;
                ut.print("Du musst aussetzen! (Enter druecken zum weiterspielen)");
                ut.input();
                
            }
            






            //Teil des Codes welcher den Computer handled
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
                    //clear() ist eine ArrayList Methode, welche alle Elemente aus der Liste entfernt
                    computerdeckkopie.clear();

                    //addAll() ist eine ArrayList Methode, welche alle Elemente aus einer Liste in eine andere Liste kopiert, ohne auf die originale Liste zu zeigen und somit die originale Liste zu veraendern
                    computerdeckkopie.addAll(computerdeck);
                    for (int i = 0; i < computerdeckkopie.size(); i++){
                        if (computerdeckkopie.get(i).contains("vierplus") || computerdeckkopie.get(i).contains("farbwahl")){
                            computerdeckkopie.remove(i);
                        }
                        
                    }
                    while (true){
                        if (computerdeckkopie.size() == 0){
                            ut.print("Der Computer kann keine Karte spielen! Er zieht eine Karte! (Enter druecken zum weiterspielen)");
                            ut.input();
                            computerdeck.add(kartenstapel.ziehekarte());
                            visualisiereSpielfeld(1);
                            break;
                        }
                        //computer.ermittleWert() gibt den Index der best möglichen Karte zurück
                        indexofbest = computer.ermittleWert(computerdeckkopie);
                        if (kartenstapel.selbeFarbe(computerdeckkopie.get(indexofbest), wunsch+":")){
                            stapel = computerdeckkopie.get(indexofbest);
                            for (int i = 0; i < computerdeck.size(); i++){
                                //equals() ist eine String Methode, welche zwei Strings vergleicht
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
                        }
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
                        if (kartenstapel.selbeFarbe(computerdeckkopie.get(indexofbest), stapel)){
                            stapel = computerdeckkopie.get(indexofbest);
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
                            break;
                        } 
                        else if (computerdeckkopie.get(indexofbest).contains("vierplus")){
                            stapel = computerdeckkopie.get(indexofbest);
                            
                            for (int i = 0; i < computerdeck.size(); i++){
                                if (computerdeck.get(i).equals(computerdeckkopie.get(indexofbest))){
                                    computerdeck.remove(i);
                                    break;
                                }
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine 4+ Karte gespielt! (Enter druecken zum weiterspielen)");
                            ut.input();
                            vierplus = true;
                            farbwahl = true;
                            blau = 0;
                            rot = 0;
                            gelb = 0;
                            gruen = 0;

                            for (int i = 0; i < computerdeck.size(); i++){
                                if (!computerdeck.get(i).contains("vierplus") && !computerdeck.get(i).contains("farbwahl")){
                                    if (computerdeck.get(i).contains("blau")){
                                        blau++;
                                    } else if (computerdeck.get(i).contains("rot")){
                                        rot++;
                                    } else if (computerdeck.get(i).contains("gelb")){
                                        gelb++;
                                    } else if (computerdeck.get(i).contains("gruen")){
                                        gruen++;
                                    }
                                }

                                
                            }
                            if (blau > rot && blau > gelb && blau > gruen){
                                wunsch = "blau:";
                            } else if (rot > blau && rot > gelb && rot > gruen){
                                wunsch = "rot:";
                            } else if (gelb > rot && gelb > blau && gelb > gruen){
                                wunsch = "gelb:";
                            } else if (gruen > rot && gruen > gelb && gruen > blau){
                                wunsch = "gruen:";
                            } else {
                                int random = (int) (Math.random() * 4);
                                wunsch = farben[random];
                            }
                            
                            break;
                        } else if (computerdeckkopie.get(indexofbest).contains("farbwahl")){
                            stapel = computerdeckkopie.get(indexofbest);
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
                            blau = 0;
                            rot = 0;
                            gelb = 0;
                            gruen = 0;

                            for (int i = 0; i < computerdeck.size(); i++){
                                if (!computerdeck.get(i).contains("vierplus") && !computerdeck.get(i).contains("farbwahl")){
                                    if (computerdeck.get(i).contains("blau")){
                                        blau++;
                                    } else if (computerdeck.get(i).contains("rot")){
                                        rot++;
                                    } else if (computerdeck.get(i).contains("gelb")){
                                        gelb++;
                                    } else if (computerdeck.get(i).contains("gruen")){
                                        gruen++;
                                    }
                                }

                                
                            }
                            if (blau > rot && blau > gelb && blau > gruen){
                                wunsch = "blau:";
                            } else if (rot > blau && rot > gelb && rot > gruen){
                                wunsch = "rot:";
                            } else if (gelb > rot && gelb > blau && gelb > gruen){
                                wunsch = "gelb:";
                            } else if (gruen > rot && gruen > gelb && gruen > blau){
                                wunsch = "gruen:";
                            } else {
                                int random = (int) (Math.random() * 4);
                                wunsch = farben[random];
                            }
                            
                            break;
                        } else if (kartenstapel.gibKartenInhalt(computerdeckkopie.get(indexofbest)).equals(kartenstapel.gibKartenInhalt(stapel))){
                            stapel = computerdeckkopie.get(indexofbest);
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
            if (spielerdeck.size() == 0){
                    ut.print("Du hast gewonnen! (Druecke Enter)");
                    ut.input();
                    ut.clr();
                    ut.ascii();
                    ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank fuers spielen!");
                    break;
                }
            else if (computerdeck.size() == 0){
                ut.print("Der Computer hat gewonnen! (Druecke Enter)");
                ut.input();
                ut.clr();
                ut.ascii();
                ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank fuers spielen!");
                break;
            }
            }

        
    

    }

    public void visualisiereSpielfeld(int zug){
        if(zug == 2){
            ut.clr();
            //computer.visualisiereDeck ist eine Methode aus der Klasse Computer, die das Deck des Computers visualisiert und zurueckgibt
            ut.print("Computer: " + computer.visualisiereDeck(computerdeck)+ "\n\n\n\n");
            //visualisiereAktuelleKarte ist eine Methode aus der Klasse Kartenstapel, die die aktuelle Karte visualisiert und zurueckgibt
            ut.print("Stapel: " + kartenstapel.visualisiereAktuelleKarte(stapel)+"\n\n\n\n"); 
            //visualisiereDeck ist eine Methode aus der Klasse Spieler, die das Deck des Spielers visualisiert und zurueckgibt
            ut.print(">>> "+username +": " + spieler.visualisiereDeck(spielerdeck));
            ut.print("\n\n");
        }
        else if (zug == 1){
            ut.clr();
            ut.print(">>> Computer: " + computer.visualisiereDeck(computerdeck)+ "\n\n\n\n");
            ut.print("Stapel: " + kartenstapel.visualisiereAktuelleKarte(stapel)+"\n\n\n\n");
            ut.print(username +": " + spieler.visualisiereDeck(spielerdeck));
            ut.print("\n\n");

        }
    }
    
    
}
