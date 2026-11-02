public class Spieler {
 private String name; private int spnr; private int punkte;
 public Spieler(String name,int spnr){this.name=name;this.spnr=spnr;}
 public String getName(){return name;}
 public int getSpielernummer(){return spnr;}
 public void setPunkte(int punkte){this.punkte=punkte;}
 public int getPunkte(){return punkte;}
}