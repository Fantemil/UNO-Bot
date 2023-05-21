import java.util.Scanner;

public class werkzeuge
{

    public void ascii(){
        clr();
        String asciiArt = "======================\n" +
                          "  _    _ _   _  ____  \n" +
                          " | |  | | \\ | |/ __ \\ \n" +
                          " | |  | |  \\| | |  | |\n" +
                          " | |  | |   ` | |  | |\n" +
                          " | |__| | |\\  | |__| |\n" +
                          "  \\____/|_| \\_|\\____/ \n" +
                            "======================";
        this.print(asciiArt);
    }
    public void clr() {
        System.out.print("\f");
    }
    public String input() {
        return input("");
    }
    public String input(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public void print(String text) {
        System.out.println(text);
    }
    public void prints(String text) {
        System.out.print(text);
    }
    public  String hochstellen(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);

    }
    public int stringtoint(String str){
        return Integer.parseInt(str);
    }
    public String inttostring(int i){
        return Integer.toString(i);
    }



}
