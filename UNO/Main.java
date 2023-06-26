 

import java.util.ArrayList;

public class Main
{
    // definieren von username (der name des spielers)
    String username;

    // definieren von ut (ut steht fuer "utility"). bezieht sich auf die "werkzeuge" klasse
    werkzeuge ut = new werkzeuge();

    // definieren von spieler und computer (spieler und computer sind objekte der klasse "Spieler" und "Computer")
    Spieler spieler = new Spieler();
    Computer computer = new Computer();

    /*  definieren von kartenstapel (kartenstapel ist ein objekt der klasse "Kartenstapel") und ist fuer das ziehen von karten zustaendig
    sowie fuer andere kartenbasierte funktionen */
    kartenstapel kartenstapel = new kartenstapel();

    // definieren von kartenanzahl (kartenanzahl ist die anzahl der karten, die der spieler und der computer zu beginn des spiels erhalten)
    int kartenanzahl;

    // definieren von aussetzen, zweiplus, vierplus, farbwahl (diese variablen sind fuer die funktionen der karten zustaendig wenn diese gelegt werden)
    boolean aussetzen = false;
    boolean zweiplus = false;
    boolean vierplus = false;
    boolean farbwahl = false;

    // definieren von spielergezogen (spielergezogen ist eine variable, die angibt, ob der spieler bereits gezogen hat)
    boolean spielergezogen = false;

    // definieren von spielerdeck (spielerdeck ist ein array mit dynamischer laenge (je nach kartenanzahl), welches die karten des spielers speichert)
    ArrayList<String> spielerdeck;

    // definieren von computerdeck (computerdeck ist ein array mit dynamischer laenge (je nach kartenanzahl), welches die karten des computers speichert)
    ArrayList<String> computerdeck = new ArrayList<>();

    // definieren von computerdeckkopie (computerdeckkopie ist fuer funktionen des Computers notwendig)
    ArrayList<String> computerdeckkopie = new ArrayList<>();

    // definieren von stapel (die variable stapel speichert die aktuelle karte, die auf dem stapel liegt)
    String stapel;

    // definieren von wunsch (wunsch ist die farbe, die der spieler/computer waehlt, wenn er eine farbwahl/vierplus karte legt)
    String wunsch;

    // definieren von blau, gruen, gelb, rot (diese variablen benutzt der computer um bei einer farbwahl/vierplus karte die farbe zu waehlen)
    int blau;
    int gruen;
    int gelb;
    int rot;
    boolean spielt = true;
    // definieren von indexofbest (indexofbest ist der index der besten karte, die der computer legen koennte)
    int indexofbest;

    // definieren von canplay (canplay ist eine variable, die angibt, ob der spieler/computer eine karte legen kann)
    boolean canplay;

    // definieren von farben (farben ist ein array, das alle farben enthaelt damit der computer im zweifelsfall eine farbe waehlen kann)
    String[] farben = {"blau", "gruen", "gelb", "rot"};

    // definieren von zweiplusstack (speichert die ueberlagerung von zweiplus karten)
    int zweiplusstack = 0;

    // definieren von vierplusstack (speichert die ueberlagerung von vierplus karten)
    int vierplusstack = 0;

    //definieren von schongezogen (speichert, ob der computer bereits gezogen hat)
    boolean schongezogen = false;

    // definieren von common (Die farbkarte die der spieler am meisten hat wenn er eine farbwahl/vierplus karte legt)
    String common;

    // definieren von temp (temp ist für die farbsortierungs funktion des spielers von bedeutung)
    String temp;
    public void ausfuehren(){

        // ut.ascii() ist eine funktion, die den text "UNO" in ascii art ausgibt
        ut.ascii();

        // ut.print() ist eine verkürzende Methode fuer System.out.println()
        ut.print("\n Willkommen zu UNO in Java!\n\nVon Emil Toth, Jakob Schmid und Ulrich Weber");
        ut.print("Setze deinen Spielername: ");

        // ut.input() fraegt nach einer eingabe und gibt diese zurueck
        username = ut.input();

        ut.print("Mit wie vielen Karten wird gespielt? (Empfohlen: 8)");
        while (true){
            String input = ut.input();
            if (input.matches("[0-9]+")){
                kartenanzahl = ut.stringtoint(input);
                break;
            } else {
                ut.print("Bitte gebe eine Zahl ein!");
            }
        }
        //ut.stringtoint() "verkürzt" Integer.parseInt() und wandelt eine String in einen Integer um


        // kartenstapel.generiereDeck() generiert eine arraylist mit einem deck voller karten und gibt dieses zurueck
        spielerdeck = kartenstapel.generiereDeck(kartenanzahl);
        computerdeck = kartenstapel.generiereDeck(kartenanzahl);
        
        while (true){
            // kartenstapel.ziehekarte() zieht eine karte aus einem unendlichen deck mit wahrscheinlichkeiten und gibt diese zurueck (wird auch in generiereDeck() verwendet)
            stapel = kartenstapel.ziehekarte();

            //startWith() ist eine funktion, die ueberprueft, ob eine string mit einem gewissen inhalt beginnt (eingebaut in java)
            if (stapel.startsWith("blau") || stapel.startsWith("gruen") || stapel.startsWith("gelb") || stapel.startsWith("rot")){
                // break beendet eine schleife
                break;
            } 
        }

        // ut.clr() ist eine funktion, die die konsole leert
        ut.clr();
        while(true){
            spielt = true;
            //size() ist eine funktion, die die anzahl der inhalte eines arrays zurueckgibt (eingebaut in java)
            if (spielerdeck.size() == 0){
                    ut.print("Du hast gewonnen! (Dr\u00fccke Enter)");
                    ut.input();
                    ut.clr();
                    ut.ascii();
                    ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank fuers spielen!");
                    ut.input();
                    break;
                }
            else if (computerdeck.size() == 0){
                ut.print("Der Computer hat gewonnen! (Dr\u00fccke Enter)");
                ut.input();
                ut.clr();
                ut.ascii();
                ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank f\u00fcrs spielen!");
                ut.input();
                break;
            }

            if (aussetzen == false && spielt) {
                
                
                if (vierplus){
                    boolean mussziehen = true;
                    for (int i = 0; i < spielerdeck.size(); i++){
                        if (spielerdeck.get(i).startsWith("vierplus") ){
                            ut.print("M\u00f6chtest du eine deiner +4 Karten überlagern? (j/n)");
                            while (true){
                                String input = ut.input();
                                if (input.equals("j")){
                                    stapel = spielerdeck.get(i);
                                    spielerdeck.remove(i);
                                    vierplus = true;
                                    farbwahl = true;
                                    vierplusstack += 4;
                                    visualisiereSpielfeld(2);
                                    spielt = false;
                                    mussziehen = false;
                                    
                                    ut.print("Du hast eine Vierplus Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Gr\u00fcn [3] Blau [4] Gelb");
                                    blau = 0;
                                    rot = 0;
                                    gruen = 0;
                                    gelb = 0;
                                    for (int j = 0; j < spielerdeck.size(); j++){
                                        if (spielerdeck.get(j).contains("blau")){
                                            blau += 1;
                                        }
                                        else if (spielerdeck.get(j).contains("rot")){
                                            rot += 1;
                                        }
                                        else if (spielerdeck.get(j).contains("gruen")){
                                            gruen += 1;
                                        }
                                        else if (spielerdeck.get(j).contains("gelb")){
                                            gelb += 1;
                                        }
                                    }
                                    if (blau > rot && blau > gruen && blau > gelb){
                                        common = "blau";
                                    }
                                    else if (rot > blau && rot > gruen && rot > gelb){
                                        common = "rot";
                                    }
                                    else if (gruen > blau && gruen > rot && gruen > gelb){
                                        common = "gruen";
                                    }
                                    else if (gelb > blau && gelb > rot && gelb > gruen){
                                        common = "gelb";
                                    }
                                    else{
                                        common = "";
                                    }
                                    if (!common.equals("")){
                                        ut.print("Am meisten hast du die Farbe " + common + " auf der Hand!");
                                    }
                                    while(true){
                                    int farbe;
                                    input = ut.input();
                                    if (input.matches("[0-9]+")){
                                        farbe = ut.stringtoint(input);
                                        
                                    } else{
                                        ut.print("Bitte gib eine Zahl ein!");
                                        continue;
                                    }
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
                                        
                                    }}
                                    break;

                                } else if (input.equals("n")){
                                    mussziehen = true;
                                    break;
                                } else {
                                    ut.print("Bitte gebe j oder n ein!");
                                }
                            }
                            break;

                        }
                        
                    }
                    if (mussziehen){
                    ut.print("Du musst " + vierplusstack + " Karten ziehen! (Dr\u00fccke Enter)");
                    ut.input();
                    for (int i = 0; i < vierplusstack; i++){
                        //add() ist eine funktion, die ein element zu einem array hinzufuegt (eingebaut in java)
                        spielerdeck.add(kartenstapel.ziehekarte());
                    }
                    vierplus = false;
                    vierplusstack = 0;
                }
                }
                else if (zweiplus){
                    boolean mussziehen = true;
                    for (int i = 0; i < spielerdeck.size(); i++){
                        if (spielerdeck.get(i).startsWith("vierplus") || spielerdeck.get(i).startsWith("farbwahl")){
                            continue;
                        }
                        else if (kartenstapel.gibKartenInhalt(spielerdeck.get(i)).equals("zweiplus")){
                            ut.print("M\u00f6chtest du eine deiner +2 karten \u00fcberlagern? (j/n)");
                            while (true){
                                String input = ut.input();
                                if (input.equals("j")){
                                    //remove() ist eine funktion, die ein element aus einem array entfernt (eingebaut in java)
                                    spielerdeck.remove(i);
                                    zweiplusstack += 2;
                                    zweiplus = true;
                                    visualisiereSpielfeld(2);
                                    spielt = false;
                                    mussziehen = false;
                                    break;
                                }
                                else if (input.equals("n")){
                                    ut.print("Du musst " + ut.inttostring(zweiplusstack) + " Karten ziehen! (Enter dr\u00fccken zum weiterspielen)");
                                    ut.input();
                                    for (int j = 0; j < zweiplusstack; j++){
                                        spielerdeck.add(kartenstapel.ziehekarte());
                                    }
                                    zweiplusstack = 0;
                                    zweiplus = false;
                                    mussziehen = false;
                                    visualisiereSpielfeld(2);
                                    break;
                                }
                                else {
                                    ut.print("Bitte gebe j oder n ein!");
                                }
                            }
                            break;

                        }
                    }

                    if (mussziehen){
                    ut.print("Du musst " + ut.inttostring(zweiplusstack) + " Karten ziehen! (Enter dr\u00fccken zum weiterspielen)");
                    ut.input();
                    for (int i = 0; i < zweiplusstack; i++){
                        spielerdeck.add(kartenstapel.ziehekarte());
                    }
                    zweiplus = false;
                    zweiplusstack = 0;}
                }
                // visualisiereSpielfeld() ist eine funktion, die sofort das spielfeld aktualisiert, indem es die anderen visualisierungs funktionen aufruft und diese zusammentraegt.
                visualisiereSpielfeld(2);
                    if (farbwahl == false && spielt){

                        

                        canplay = false;
                        for (int i = 0; i < spielerdeck.size(); i++){
                            // kartenstapel.selbeFarbe() ist eine funktion, die ueberprueft, ob zwei karten die gleiche farbe haben
                            if (kartenstapel.selbeFarbe(spielerdeck.get(i), stapel)){
                                temp = spielerdeck.get(i);
                                
                                
                                
                                canplay = true;
                            // get() ist eine funktion fuer ArrayLists die den inhalt eines bestimmten index zurueckgibt
                            // contains() ist eine funktion fuer Strings, die ueberprueft, ob ein string einen bestimmten inhalt hat
                            } else if (!spielerdeck.get(i).contains("vierplus") && !spielerdeck.get(i).contains("farbwahl")){
                                // kartenstapel.gibKartenInhalt() ist eine funktion, die den inhalt einer karte zurueckgibt indem er die farbe ausblendet
                                if (kartenstapel.gibKartenInhalt(spielerdeck.get(i)).contains(kartenstapel.gibKartenInhalt(stapel))){
                                    canplay = true;
                                }
                                
                            } else if (spielerdeck.get(i).contains("vierplus")){
                                canplay = true;
                            } else if (spielerdeck.get(i).contains("farbwahl")){
                                canplay = true;
                            }
                            if (!spielerdeck.get(i).equals("vierplus") && !spielerdeck.get(i).equals("farbwahl") && !stapel.equals("farbwahl") && !stapel.equals("vierplus")){
                                String aufdeck = kartenstapel.gibKartenInhalt(stapel);
                                if (aufdeck.equals(kartenstapel.gibKartenInhalt(spielerdeck.get(i)))){
                                    temp = spielerdeck.get(i);
                                    spielerdeck.remove(i);
                                    spielerdeck.add(0, temp);
                                }
                            }
                            if (stapel.startsWith(spielerdeck.get(i).split(":")[0])){
                                temp = spielerdeck.get(i);
                                spielerdeck.remove(i);
                                spielerdeck.add(0, temp);                                

                            }}
                        
                        
                        visualisiereSpielfeld(2);
                        
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
                        if (canplay == false && spielergezogen == false){
                            ut.print("Du kannst keine Karte spielen! Ziehe eine Karte! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            spielerdeck.add(kartenstapel.ziehekarte());
                            spielergezogen = true;
                            visualisiereSpielfeld(2);
                            continue;
                        } else if (canplay == false && spielergezogen){
                            ut.print("Du kannst keine Karte spielen! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            visualisiereSpielfeld(2);
                        }
                        
                        if (canplay == true){
                        //zuspielen speichert die karte selbst, die der spieler spielen moechte
                        String zuspielen;
                        // karte speichert die nummer der karte, die der spieler spielen moechte
                        int karte;
                        ut.print("Welche Karte m\u00f6chtest du spielen?");
                        while (true){
                            // inputter speichert die eingabe des spielers temporaer
                            String inputter = ut.input();
                            // matches() ist eine funktion fuer Strings, die regex (regular expressions) also die suche nach bestimmten mustern ermoeglicht
                            if (inputter.matches("[0-9]+")){
                                if (ut.stringtoint(inputter) <= spielerdeck.size()){
                                    karte = ut.stringtoint(inputter);
                                    zuspielen = spielerdeck.get(karte-1);
                                    break;
                                } else{
                                    ut.print("Du hast keine Karte mit dieser Nummer!");
                                    continue;
                                }
                            } else{
                                ut.print("Bitte gib eine Zahl ein!");
                                continue;
                            }
                            
                        }
                        if (kartenstapel.selbeFarbe(zuspielen, stapel)){
                            if (zuspielen.contains("aussetzen")){
                                aussetzen = true;
                            } else if (zuspielen.contains("zweiplus")){
                                zweiplus = true;
                                zweiplusstack += 2;
                            }
                            stapel = zuspielen;
                            // remove() ist eine funktion fuer ArrayLists, die ein element aus dem array entfernt
                            spielerdeck.remove(karte-1);   
                            visualisiereSpielfeld(2);
                        
                        } 
                        
                        else{
                            if (!zuspielen.contains("vierplus") && !zuspielen.contains("farbwahl")){
                                if (kartenstapel.gibKartenInhalt(zuspielen).equals(kartenstapel.gibKartenInhalt(stapel))){
                                    if (zuspielen.contains("aussetzen")){
                                        aussetzen = true;
                                    } else if (zuspielen.contains("zweiplus")){
                                        zweiplus = true;
                                        zweiplusstack += 2;
                                    }
                                    stapel = zuspielen;
                                    spielerdeck.remove(karte-1);
                                    visualisiereSpielfeld(2);
                                } else{
                                    ut.print("Diese Karte kannst du nicht spielen!");
                                    ut.input();
                                    continue;
                                }}
                            else if (zuspielen.contains("vierplus")){
                                vierplus = true;
                                vierplusstack += 4;
                                stapel = zuspielen;
                                spielerdeck.remove(karte-1);
                                visualisiereSpielfeld(2);
                                ut.print("Du hast eine Vierplus Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Gr\u00fcn [3] Blau [4] Gelb");
                                blau = 0;
                                rot = 0;
                                gruen = 0;
                                gelb = 0;
                                for (int i = 0; i < spielerdeck.size(); i++){
                                    if (spielerdeck.get(i).contains("blau")){
                                        blau += 1;
                                    }
                                    else if (spielerdeck.get(i).contains("rot")){
                                        rot += 1;
                                    }
                                    else if (spielerdeck.get(i).contains("gruen")){
                                        gruen += 1;
                                    }
                                    else if (spielerdeck.get(i).contains("gelb")){
                                        gelb += 1;
                                    }
                                }
                                if (blau > rot && blau > gruen && blau > gelb){
                                    common = "blau";
                                }
                                else if (rot > blau && rot > gruen && rot > gelb){
                                    common = "rot";
                                }
                                else if (gruen > blau && gruen > rot && gruen > gelb){
                                    common = "gruen";
                                }
                                else if (gelb > blau && gelb > rot && gelb > gruen){
                                    common = "gelb";
                                }
                                else{
                                    common = "";
                                }
                                if (!common.equals("")){
                                    ut.print("Am meisten hast du die Farbe " + common + " auf der Hand!");
                                }
                                while(true){
                                int farbe;
                                String input = ut.input();
                                if (input.matches("[0-9]+")){
                                    farbe = ut.stringtoint(input);
                                    
                                } else{
                                    ut.print("Bitte gib eine Zahl ein!");
                                    continue;
                                }
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
                                    
                                }}
                                
                                
                            }
                            else if (zuspielen.contains("farbwahl")){
                                farbwahl = true;
                                stapel = zuspielen;
                                spielerdeck.remove(karte-1);
                                visualisiereSpielfeld(2);
                                ut.print("Du hast eine Farbwahl Karte gespielt! Welche Farbe soll gespielt werden? [1] Rot [2] Gr\u00fcn [3] Blau [4] Gelb");
                                blau = 0;
                                rot = 0;
                                gruen = 0;
                                gelb = 0;
                                for (int i = 0; i < spielerdeck.size(); i++){
                                    if (spielerdeck.get(i).contains("blau")){
                                        blau += 1;
                                    }
                                    else if (spielerdeck.get(i).contains("rot")){
                                        rot += 1;
                                    }
                                    else if (spielerdeck.get(i).contains("gruen")){
                                        gruen += 1;
                                    }
                                    else if (spielerdeck.get(i).contains("gelb")){
                                        gelb += 1;
                                    }
                                }
                                if (blau > rot && blau > gruen && blau > gelb){
                                    common = "blau";
                                }
                                else if (rot > blau && rot > gruen && rot > gelb){
                                    common = "rot";
                                }
                                else if (gruen > blau && gruen > rot && gruen > gelb){
                                    common = "gruen";
                                }
                                else if (gelb > blau && gelb > rot && gelb > gruen){
                                    common = "gelb";
                                }
                                else{
                                    common = "";
                                }
                                if (!common.equals("")){
                                    ut.print("Am meisten hast du die Farbe " + common + " auf der Hand!");
                                }
                                while(true){
                                    int farbe;
                                    String input = ut.input();
                                    if (input.matches("[0-9]+")){
                                        farbe = ut.stringtoint(input);
                                        
                                    } else{
                                        ut.print("Bitte gib eine Zahl ein!");
                                        continue;
                                    }
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
                                        
                                    }
                                }
                            } else if (zuspielen.contains("zweiplus")){

                                if (kartenstapel.selbeFarbe(zuspielen, stapel)){
                                    zweiplus = true;
                                    zweiplusstack += 2;
                                    stapel = zuspielen;
                                    spielerdeck.remove(karte-1);
                                    visualisiereSpielfeld(2);}
                                else if (kartenstapel.gibKartenInhalt(zuspielen).equals(kartenstapel.gibKartenInhalt(stapel))){
                                    zweiplus = true;
                                    zweiplusstack += 2;
                                    stapel = zuspielen;
                                    spielerdeck.remove(karte-1);
                                    visualisiereSpielfeld(2);}
                                else{
                                    ut.print("Diese Karte kann nicht gespielt werden!");
                                    ut.input();
                                    continue;
                                }

                            } else if (zuspielen.contains("aussetzen")){

                                if (kartenstapel.selbeFarbe(zuspielen, stapel)){
                                aussetzen = true;
                                stapel = zuspielen;
                                spielerdeck.remove(karte-1);
                                visualisiereSpielfeld(2);}
                                
                                else if (kartenstapel.gibKartenInhalt(zuspielen).equals(kartenstapel.gibKartenInhalt(stapel))){
                                    aussetzen = true;
                                    stapel = zuspielen;
                                    spielerdeck.remove(karte-1);
                                    visualisiereSpielfeld(2);}
                                else{
                                    ut.print("Diese Karte kann nicht gespielt werden!");
                                    ut.input();
                                    continue;
                                }
                            }
                                

                            else{
                                ut.print("Diese Karte kann nicht gespielt werden!");
                                ut.input();
                                continue;
                            }
                        }
                        
                    }}
                    else if (farbwahl == true && spielt){
                        visualisiereSpielfeld(2);
                       
                        canplay = false;
                        
                        for (int i = 0; i < spielerdeck.size(); i++){
                            if (spielerdeck.get(i).contains(wunsch+":")){
                                temp = spielerdeck.get(i);
                                spielerdeck.remove(i);
                                spielerdeck.add(0, temp);                                

                            }
                        }
                        
                        visualisiereSpielfeld(2);
                            
                        if (canplay == false && spielergezogen == false){
                            ut.print("Du kannst keine Karte spielen! Ziehe eine Karte! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            spielerdeck.add(kartenstapel.ziehekarte());
                            spielergezogen = true;
                            visualisiereSpielfeld(2);
                            continue;
                        } else if (canplay == false && spielergezogen == true){
                            ut.print("Du kannst keine Karte spielen! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                        }
                        else{
                            ut.print("Welche Karte m\u00f6chtest du spielen? Schwarze Sonderkarten k\u00f6nnen nicht gespielt werden!\n Die gewünschte Farbe ist " + wunsch );
                            int karte;
                            String input;
                            String zuspielen;
                            while (true){
                                input = ut.input();
                                if (input.matches("[0-9]+")){
                                    karte = ut.stringtoint(input);
                                    if (karte <= spielerdeck.size()){
                                        
                                        zuspielen = spielerdeck.get(karte-1);
                                        break;
                                    } else{
                                        ut.print("Diese Karte existiert nicht!");
                                        continue;
                                    }
                                } else{
                                    ut.print("Bitte gib eine Zahl ein!");
                                    continue;
                                }
                            }
                            if (kartenstapel.selbeFarbe(zuspielen, wunsch+":")){
                                stapel = zuspielen;
                                spielerdeck.remove(karte-1);
                                visualisiereSpielfeld(2);
                                if (zuspielen.contains("zweiplus")){
                                    zweiplus = true;
                                    zweiplusstack += 2;
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
                        }}
                            
            } else if (aussetzen==true){
                visualisiereSpielfeld(2);
                aussetzen = false;
                ut.print("Du musst aussetzen! (Enter dr\u00fccken zum weiterspielen)");
                ut.input();
                
            }
            




            schongezogen = false;
            spielt = true;
            //Teil des Codes welcher den Computer handled
            if (aussetzen == false){

                if (vierplus){
                    boolean mussziehen = true;

                    for(int j = 0; j < computerdeck.size(); j++){
                        if (computerdeck.get(j).contains("vierplus")){
                            stapel = computerdeck.get(j);
                            computerdeck.remove(j);
                            vierplus = true;
                            vierplusstack += 4;
                            visualisiereSpielfeld(1);
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
                                wunsch = "blau";
                            } else if (rot > blau && rot > gelb && rot > gruen){
                                wunsch = "rot";
                            } else if (gelb > rot && gelb > blau && gelb > gruen){
                                wunsch = "gelb";
                            } else if (gruen > rot && gruen > gelb && gruen > blau){
                                wunsch = "gruen";
                            } else {
                                int random = (int) (Math.random() * 4);
                                wunsch = farben[random];
                            }
                            ut.print("Der Computer hat deine 4+ Karte \u00fcberlagert.");
                            ut.input();
                            spielt = false;
                            mussziehen = false;
                            break;
                        }
                    }
                    if (mussziehen){
                    ut.print("Der Computer zieht "+vierplusstack+" Karten! (Enter dr\u00fccken zum weiterspielen)");
                    ut.input();
                    
                    for (int i = 0; i < vierplusstack; i++){
                        computerdeck.add(kartenstapel.ziehekarte());
                    }
                    vierplusstack = 0;
                    vierplus = false;
                    
                    visualisiereSpielfeld(1);
                    

                }
                } else if (zweiplus){
                    boolean mussziehen = true;
                    for (int i = 0; i < computerdeck.size(); i++){
                        if (computerdeck.get(i).contains("zweiplus")){
                            // if he has one, he plays it
                            stapel = computerdeck.get(i);
                            computerdeck.remove(i);
                            zweiplusstack += 2;
                            zweiplus = true;
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat deine 2+ Karte \u00fcberlagert.");
                            ut.input();
                            spielt = false;
                            mussziehen = false;
                            break;
                        }
                    }
                    if (mussziehen){
                        ut.print("Der Computer zieht "+zweiplusstack+" Karten! (Enter dr\u00fccken zum weiterspielen)");
                        ut.input();
                        for (int i = 0; i < zweiplusstack; i++){
                            computerdeck.add(kartenstapel.ziehekarte());
                        }
                        zweiplusstack = 0;
                        zweiplus = false;
                    }
                }
                visualisiereSpielfeld(1);
                if (farbwahl &&spielt){
                    for (int i = 0; i < computerdeck.size(); i++){
                        if (kartenstapel.selbeFarbe(computerdeck.get(i), wunsch+":")){
                            canplay = true;
                        }
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
                        if (computerdeckkopie.size() == 0 && schongezogen == false){
                            ut.print("Der Computer kann keine Karte spielen! Er zieht eine Karte! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            computerdeck.add(kartenstapel.ziehekarte());
                            computerdeckkopie.clear();
                            computerdeckkopie.addAll(computerdeck);
                            visualisiereSpielfeld(1);
                            schongezogen = true;
                        } else if(computerdeckkopie.size() == 0 && schongezogen == true){
                            ut.print("Der Computer kann keine Karte spielen! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            break;
                        }
                        //computer.ermittleWert() gibt den Index der best moeglichen Karte zurueck
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
                                zweiplusstack += 2;
                                zweiplus = true;
                            } else if (computerdeckkopie.get(indexofbest).contains("aussetzen")){
                                aussetzen = true;
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            farbwahl = false;
                            
                            break;
                        }
                        else{
                            computerdeckkopie.remove(indexofbest);
                        }
                    }
                    
                } else if(spielt){
                    computerdeckkopie.clear();
                    computerdeckkopie.addAll(computerdeck);
                    
                    while (true){
                        if (computerdeckkopie.size() == 0 && schongezogen == false){
                            ut.print("Der Computer kann keine Karte spielen! Er zieht eine Karte! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            computerdeck.add(kartenstapel.ziehekarte());
                            computerdeckkopie.clear();  
                            computerdeckkopie.addAll(computerdeck);
                            visualisiereSpielfeld(1);
                            schongezogen = true;
                            

                        } else if (computerdeckkopie.size() == 0 && schongezogen){
                            ut.print("Der Computer kann nicht spielen! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
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
                                zweiplusstack += 2;
                            } else if (computerdeckkopie.get(indexofbest).contains("aussetzen")){
                                aussetzen = true;
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt! (Enter dr\u00fccken zum weiterspielen)");
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
                            ut.print("Der Computer hat eine 4+ Karte gespielt! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            vierplus = true;
                            vierplusstack += 4;
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
                                wunsch = "blau";
                            } else if (rot > blau && rot > gelb && rot > gruen){
                                wunsch = "rot";
                            } else if (gelb > rot && gelb > blau && gelb > gruen){
                                wunsch = "gelb";
                            } else if (gruen > rot && gruen > gelb && gruen > blau){
                                wunsch = "gruen";
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
                            ut.print("Der Computer hat eine farbwahl Karte gespielt! (Enter dr\u00fccken zum weiterspielen)");
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
                                wunsch = "blau";
                            } else if (rot > blau && rot > gelb && rot > gruen){
                                wunsch = "rot";
                            } else if (gelb > rot && gelb > blau && gelb > gruen){
                                wunsch = "gelb";
                            } else if (gruen > rot && gruen > gelb && gruen > blau){
                                wunsch = "gruen";
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
                                zweiplusstack += 2;
                            } else if (computerdeckkopie.get(indexofbest).contains("aussetzen")){
                                aussetzen = true;
                            }
                            visualisiereSpielfeld(1);
                            ut.print("Der Computer hat eine Karte gespielt (farben gekreuzt)! (Enter dr\u00fccken zum weiterspielen)");
                            ut.input();
                            break;
                        }
                        else{
                            computerdeckkopie.remove(indexofbest);
                        }
                    }
                    
                }
        
            } else {
                ut.print("Der Computer setzt aus! (Enter dr\u00fccken zum weiterspielen)");
                ut.input();
                aussetzen = false;
            }
            if (spielerdeck.size() == 0){
                    ut.print("Du hast gewonnen! (Dr\u00fccke Enter)");
                    ut.input();
                    ut.clr();
                    ut.ascii();
                    ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank f\u00fcrs spielen!");
                    ut.input();
                    break;
                }
            else if (computerdeck.size() == 0){
                ut.print("Der Computer hat gewonnen! (Dr\u00fccke Enter)");
                ut.input();
                ut.clr();
                ut.ascii();
                ut.print("\nUNO in Java!\n\nProgrammierung: Emil Toth, Ulrich Weber\nDokumentation: Jakob Schmid\nBughunting: Ulrich Weber\n\nVielen Dank f\u00fcrs spielen!");
                ut.input();
                break;
            }
        
            spielergezogen = false; }
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
