import java.util.Scanner;
public class Spiel {
 private static Scanner sc=new Scanner(System.in);
 public static Spieler spieler1,spieler2;
 public static Spielfeld sf;
 public static int zeile=-1,spalte=-1;
 public static void main(String[] args){
  System.out.println("Herzlich Willkommen zur Schatzsuche");
  int eingabe;
  do{
   eingabe=menu();
   if(eingabe==1){
    System.out.println("Gib den Namen von Spieler 1 ein:");
    String name1=sc.nextLine();
    System.out.println("Gib den Namen von Spieler 2 ein:");
    String name2=sc.nextLine();
    spieler1=new Spieler(name1,1);spieler2=new Spieler(name2,2);
    System.out.println("Gib die Groesse des Spielfelds ein:");
    int n;
    do{n=liesZahl(sc);if(n<1||n>20)System.out.println("Bitte eine Groesse von 1 bis 20 eingeben.");}while(n<1||n>20);
    sf=new Spielfeld(n);spielen();
   }
  }while(eingabe!=2);
  sc.close();
 }
 public static int menu(){
  int res;
  do{System.out.println("1 - Spielen");System.out.println("2 -programm beenden");res=liesZahl(sc);}while(!checkEingabeMenu(res));
  return res;
 }
 public static boolean checkEingabeMenu(int eingabe){return eingabe==1||eingabe==2;}
 public static void spielen(){
  do{spielerSpielt(spieler1);if(weiterspielen())spielerSpielt(spieler2);}while(weiterspielen());
  System.out.println(sf);System.out.println(auswertung());
 }
 public static void spielerSpielt(Spieler s){
  boolean treffer;
  do{
   System.out.println(sf);eingabe(s);treffer=sf.treffer(zeile,spalte);
   System.out.println(sf.deckeAuf(zeile,spalte,s));
   if(treffer)s.setPunkte(s.getPunkte()+1);
   System.out.println(sf);
  }while(treffer&&weiterspielen());
 }
 public static void eingabe(Spieler s){
  do{
   System.out.println(s.getName()+" gib eine Zeile an: ");zeile=liesZahl(sc);
   System.out.println(s.getName()+" gib eine Spalte an: ");spalte=liesZahl(sc);
  }while(!sf.gueltigeEingabe(zeile,spalte));
 }
 public static boolean weiterspielen(){return spieler1.getPunkte()+spieler2.getPunkte()<sf.getN();}
 public static String auswertung(){
  if(spieler1.getPunkte()>spieler2.getPunkte())return spieler1.getName()+" hat mit "+spieler1.getPunkte()+":"+spieler2.getPunkte()+" gewonnen!";
  if(spieler2.getPunkte()>spieler1.getPunkte())return spieler2.getName()+" hat mit "+spieler2.getPunkte()+":"+spieler1.getPunkte()+" gewonnen!";
  return "Das Spiel endet unentschieden";
 }
 private static int liesZahl(Scanner scanner){
  while(true)try{return Integer.parseInt(scanner.nextLine().trim());}
  catch(NumberFormatException e){System.out.println("Bitte eine ganze Zahl eingeben.");}
 }
}