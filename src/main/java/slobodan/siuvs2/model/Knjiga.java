/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

/**
 *
 * @author Aleksandra
 */
public class Knjiga {

    private String naslov;
    private String pisac;
    private int godinaIzdavanja;
    private String izdavac;

    //Prazan konstruktor
    public Knjiga() {
        this.naslov = "Na Drini cuprija";
        this.pisac = "Ivo Andric";
        this.godinaIzdavanja = 1945;
        this.izdavac = "Delfi";
    }

    //Konstruktor sa argumentima
    Knjiga(String naslov, String pisac, int godinaIzdavanja, String izdavac) {
        this.naslov = naslov;
        this.pisac = pisac;
        this.godinaIzdavanja = godinaIzdavanja;
        this.izdavac = izdavac;
    }


    //toString metod
    public String toString() {

        return "Naslov knjige je: " + this.naslov +" \n" + "Pisac je: " + this.pisac + " \n" + "Godina izdavanja je: " + this.godinaIzdavanja + " \n" + "Izdavac je: " + this.izdavac +" \n";
    }

    
    
        //geteri i seteri

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getPisac() {
        return pisac;
    }

    public void setPisac(String pisac) {
        this.pisac = pisac;
    }

    public int getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(int godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

}
