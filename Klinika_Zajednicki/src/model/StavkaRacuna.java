/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class StavkaRacuna implements ApstraktniDomenskiObjekat {

    private int rb;
    private double cena;
    private double iznos;
    private int kolicina;
    private Usluga usluga;
    private Racun racun;

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    public StavkaRacuna() {
    }

    public StavkaRacuna(int rb, double cena, double iznos, int kolicina, Usluga usluga, Racun racun) {
        this.rb = rb;
        this.cena = cena;
        this.iznos = iznos;
        this.kolicina = kolicina;
        this.usluga = usluga;
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    @Override

    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("rb");
            int kolicina = rs.getInt("kolicina");
            double cena = rs.getDouble("cena");
            double iznos = rs.getDouble("iznos");

            int idUsluge = rs.getInt("usluga");  
            Usluga u = new Usluga();
            u.setIdUsluga(idUsluge);

            int idRacuna = rs.getInt("racun");
            Racun r = new Racun();
            r.setIdRacun(idRacuna);

            StavkaRacuna stavka = new StavkaRacuna(rb, cena, iznos, kolicina, u, r);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "cena, iznos, kolicina, usluga, racun";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return cena + "," + iznos + "," + +kolicina + "," + usluga.getIdUsluga() + ", " + racun.getIdRacun();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rb=" + rb + " AND racun=" + racun.getIdRacun();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int rb = rs.getInt("rb");
            int kolicina = rs.getInt("kolicina");
            double cena = rs.getDouble("cena");
            int idUsluge = rs.getInt("usluga");
            double iznos = cena * kolicina;
            Usluga u = new Usluga();
            u.setIdUsluga(idUsluge);
            int idRacun = rs.getInt("racun");
            Racun r = new Racun();
            r.setIdRacun(idRacun);
            return new StavkaRacuna(rb, cena, iznos, kolicina, u, r);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "cena=" + cena + ", iznos=" + iznos
                + ", kolicina=" + kolicina
                + ", usluga=" + usluga.getIdUsluga();
    }

}
