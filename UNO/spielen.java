public class spielen {
    public Main ausfuehren = new Main();
    public werkzeuge ut = new werkzeuge();
    public spielen(){
        ausfuehren.ausfuehren();
        while (true){
            ut.print("Zum neustarten Enter drücken");
            ut.input();
            ausfuehren.ausfuehren();
        }
    }
}
