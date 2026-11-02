import java.util.Random;
public class Spielfeld {
 private boolean[][] schatzkarte;
 private int[][] offenesSpielfeld;
 private static Random rd=new Random();
 private int n;
 public Spielfeld(boolean[][] schatzkarte,int[][] offenesSpielfeld){
  n=schatzkarte.length;this.schatzkarte=schatzkarte;this.offenesSpielfeld=offenesSpielfeld;
 }
 public Spielfeld(int n){this.n=n;schatzkarte=new boolean[n][n];offenesSpielfeld=new int[n][n];initialisiereSpielfeld();}
 private void initialisiereSpielfeld(){
  int anzahl=0;
  do{int zeile=rd.nextInt(n),spalte=rd.nextInt(n);
   if(!schatzkarte[zeile][spalte]){schatzkarte[zeile][spalte]=true;anzahl++;}
  }while(anzahl!=n);
 }
 public boolean[][] getSchatzkarte(){return schatzkarte;}
 public int getOffenesSpielfeld(int zeile,int spalte){return offenesSpielfeld[zeile][spalte];}
 public int getN(){return n;}
 public boolean gueltigeEingabe(int zeile,int spalte){
  return zeile>=0&&zeile<n&&spalte>=0&&spalte<n&&offenesSpielfeld[zeile][spalte]==0;
 }
 public boolean treffer(int zeile,int spalte){return schatzkarte[zeile][spalte];}
 public boolean angrenzend(int zeile,int spalte){
  return (zeile+1<n&&schatzkarte[zeile+1][spalte])||(zeile>0&&schatzkarte[zeile-1][spalte])||
   (spalte+1<n&&schatzkarte[zeile][spalte+1])||(spalte>0&&schatzkarte[zeile][spalte-1]);
 }
 public String deckeAuf(int zeile,int spalte,Spieler s){
  if(treffer(zeile,spalte)){offenesSpielfeld[zeile][spalte]=s.getSpielernummer();return "Schatz gefunden! Punkt fuer "+s.getName()+"!";}
  if(angrenzend(zeile,spalte)){offenesSpielfeld[zeile][spalte]=-2;return "Ein Schatz befindet sich in mind. einem angrenzenden Feld.";}
  offenesSpielfeld[zeile][spalte]=-1;return "Kein Schatz in der Naehe.";
 }
 public String toString(){
  String ausgabe="    ";
  for(int j=0;j<n;j++)ausgabe+=j+"   ";
  ausgabe+="\n";
  for(int i=0;i<n;i++){
   ausgabe+=i+"   ";
   for(int j=0;j<n;j++){
    int k=offenesSpielfeld[i][j];
    ausgabe+=(k==0?"_   ":k==1?"S1  ":k==2?"S2  ":k==-1?"X   ":"?   ");
   }
   ausgabe+="\n";
  }
  return ausgabe;
 }
}