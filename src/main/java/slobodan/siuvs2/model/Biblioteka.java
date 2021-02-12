/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public class Biblioteka {

    private String imeBiblioteke;
    private String adresaBiblioteke;
    private int brojKnjiga;
    private List<Knjiga> listaKnjiga;

    public static Biblioteka test() {
        Knjiga knjiga1 = new Knjiga("Mali princ", "Antoan de Sent Egziperi", 2016, "Laguna");
                Knjiga knjiga2 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
 Knjiga knjiga3 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
  Knjiga knjiga4 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
   Knjiga knjiga5 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
    Knjiga knjiga6 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
     Knjiga knjiga7 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
      Knjiga knjiga8 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
       Knjiga knjiga9 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
        Knjiga knjiga10 = new Knjiga("Cilkin put", "Heder Moris", 2021, "Laguna");
        

     /*   Knjiga knjiga3 = new Knjiga();
        knjiga2.setNaslov("Zivot na nasoj planeti");

*/
    /*    System.out.println(knjiga1);
        System.out.println(knjiga2);*/

      /*  System.out.println(knjiga3);*/

        List<Knjiga> listaKnjiga = new ArrayList<>();
        listaKnjiga.add(knjiga1);
        listaKnjiga.add(knjiga2);
        
        Biblioteka mojaBiblioteka = new Biblioteka();
        mojaBiblioteka=new Biblioteka("Narodna biblioteka", "Perina 55", 2, listaKnjiga);
        
      //umesto ovoga pravi se metod dodajKnjigu, pa se ovo ne koristi jer je odvratno 
   /*     mojaBiblioteka.getListaKnjiga().add(knjiga2);
        mojaBiblioteka.setBrojKnjiga(mojaBiblioteka.getBrojKnjiga()+1);
        */
        //iz metode dodajKnigu
         mojaBiblioteka.dodajKnjigu(knjiga3);
         mojaBiblioteka.dodajKnjigu(knjiga4);
         mojaBiblioteka.dodajKnjigu(knjiga5);
         mojaBiblioteka.dodajKnjigu(knjiga6);
         mojaBiblioteka.dodajKnjigu(knjiga7);mojaBiblioteka.dodajKnjigu(knjiga10);
         mojaBiblioteka.dodajKnjigu(knjiga8);
         mojaBiblioteka.dodajKnjigu(knjiga9);
         
         
         
         
         
        //  System.out.println(mojaBiblioteka);
        return mojaBiblioteka;

    }

    /*
    public void dodajKnjigu() {
        this.listaKnjiga.add();
        this.listaKnjiga++;
    }*/
    
    //Prazan konstruktor
    Biblioteka() {
        this.imeBiblioteke = "Pera Peric";
        this.adresaBiblioteke = "Cvijiceva";
        this.brojKnjiga = 8;
        this.listaKnjiga = new ArrayList<>();
    }

    //Konstruktor sa argumentima
    public Biblioteka(String imeBiblioteke, String adresaBiblioteke, int brojKnjiga, List<Knjiga> listaKnjiga) {
        this.imeBiblioteke = imeBiblioteke;
        this.adresaBiblioteke = adresaBiblioteke;
        this.brojKnjiga = brojKnjiga;
        this.listaKnjiga = listaKnjiga;
    }

    
        //toString metod
    public String toString() {

        String knjige = "";

        for (Knjiga knjiga : listaKnjiga) {
            knjige += knjiga.toString() + "\n";
        }
        return "Ime biblioteke je: " + imeBiblioteke + " \n"+ "Adresa biblioteke je: " + adresaBiblioteke + " \n"+ "Broj knjiga je: " + brojKnjiga + " \n"+ "Knjige su: " + listaKnjiga +"\n";
    }
    
    
    //cesto treba koristiti ovakve metode
    public void dodajKnjigu(Knjiga knjiga) {
        this.listaKnjiga.add(knjiga);
        this.brojKnjiga++;
    
    }

    
    
    
    //geteri i seteri
    
    public String getImeBiblioteke() {
        return imeBiblioteke;
    }

    public void setImeBiblioteke(String imeBiblioteke) {
        this.imeBiblioteke = imeBiblioteke;
    }

    public String getAdresaBiblioteke() {
        return adresaBiblioteke;
    }

    public void setAdresaBiblioteke(String adresaBiblioteke) {
        this.adresaBiblioteke = adresaBiblioteke;
    }

    public int getBrojKnjiga() {
        return brojKnjiga;
    }

    public void setBrojKnjiga(int brojKnjiga) {
        this.brojKnjiga = brojKnjiga;
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

}
