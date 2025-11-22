/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.controller;

import operacija.racun.UcitajRacuneSO;
import operacija.usluge.UcitajUslugeSO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usluga;
import model.Pacijent;
import model.Klinika;
import model.Lekar;
import model.Racun;
import model.StrucnaSprema;
import model.StavkaRacuna;
import operacija.*;
import operacija.usluge.*;
import operacija.pacijenti.*;
import operacija.klinika.*;
import operacija.strucnasprema.*;
import operacija.racun.*;
import operacija.lekar.IzmeniLekarSO;
import operacija.lekar.UcitajLekarSO;


//import rs.ac.bg.fon.ps.repository.Repository;


/**
 *
 * @author Filip
 */
public class Controller {

    private static Controller instance;


    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Lekar login(Lekar l) throws Exception {
        LogInOperacija operacija = new LogInOperacija();
        operacija.izvrsi(l, null);
        System.out.println("Klasa controller: " + operacija.getLekar());
        return operacija.getLekar();
    }

    public List<Usluga> ucitajUsluge() throws Exception {
        UcitajUslugeSO operacija = new UcitajUslugeSO();
        operacija.izvrsi(null, null);
        return operacija.getUsluge();
    }

    public void obrisiUsluga(Usluga u) throws Exception {
        ObrisiUslugaSO operacija = new ObrisiUslugaSO();
        operacija.izvrsi(u, null);
    }

    public void dodajUslugu(Usluga novaUsluga) throws Exception {
        KreirajUslugaSO operacija = new KreirajUslugaSO();
        operacija.izvrsi(novaUsluga, null);
    }

    public void izmeniUsluga(Usluga uslugaZaIzmenu) throws Exception {
        IzmeniUslugaSO operacija = new IzmeniUslugaSO();
        operacija.izvrsi(uslugaZaIzmenu, null);
    }

    public List<Pacijent> ucitajPacijente() throws Exception {
        UcitajPacijentSO operacija = new UcitajPacijentSO();
        operacija.izvrsi(null, null);
        return operacija.getPacijenti();
    }

    public void obrisiPacijent(Pacijent pa) throws Exception {
        ObrisiPacijentSO operacija = new ObrisiPacijentSO();
        operacija.izvrsi(pa, null);
    }

    public List<Klinika> ucitajKlinike() throws Exception {
        UcitajKlinikeSO operacija = new UcitajKlinikeSO();
        operacija.izvrsi(null, null);
        return operacija.getKlinike();
    }

    public void dodajPacijenta(Pacijent noviPacijent) throws Exception {
        KreirajPacijentSO operacija = new KreirajPacijentSO();
        operacija.izvrsi(noviPacijent, null);
    }

    public void dodajKliniku(Klinika novaKlinika) throws Exception {
        KreirajKlinikaSO operacija = new KreirajKlinikaSO();
        operacija.izvrsi(novaKlinika, null);
    }

    public void izmeniPacijent(Pacijent pacijentZaIzmenu) throws Exception {
        IzmeniPacijentSO operacija = new IzmeniPacijentSO();
        operacija.izvrsi(pacijentZaIzmenu, null); 
    }

    public List<StrucnaSprema> ucitajStrucneSpreme() throws Exception {
        UcitajStrucnaSpremaSO operacija = new UcitajStrucnaSpremaSO();
        operacija.izvrsi(null, null);
        return operacija.getStrucneSpreme();
    }

    public void dodajStrucnaSprema(StrucnaSprema novaStrucnaSprema) throws Exception {
        KreirajStrucnaSpremaSO operacija = new KreirajStrucnaSpremaSO();
        operacija.izvrsi(novaStrucnaSprema, null);
    }

    public List<Racun> ucitajRacune() throws Exception {
        UcitajRacunSO operacija = new UcitajRacunSO();
        operacija.izvrsi(null, null);
        return operacija.getListaRacuna();
    }

    public List<StavkaRacuna> ucitajStavkeRacuna(Racun racun) throws Exception {
        UcitajRacuneSO operacija = new UcitajRacuneSO();
        operacija.izvrsi(racun, null);
        return operacija.getListaStavki();
    }

    public List<Lekar> ucitajLekare() throws Exception {
        UcitajLekarSO operacija = new UcitajLekarSO();
        operacija.izvrsi(null, null);
        return operacija.getLekari();
    }
    
    public void dodajRacun(Racun noviRacun) throws Exception {
        KreirajRacunSO operacija = new KreirajRacunSO();
        operacija.izvrsi(noviRacun, null);
    }

    public List<Racun> pretraziRacune(Racun racunZaPretragu) throws Exception {
        PretraziRacunSO operacija = new PretraziRacunSO();
        operacija.izvrsi(racunZaPretragu, null);
        return operacija.getRacuni();
    }
    
    public void izmeniRacun(Racun racunZaIzmenu) throws Exception {
        IzmeniRacunSO operacija = new IzmeniRacunSO();
        operacija.izvrsi(racunZaIzmenu, null);
    }

    public void izmeniLekara(Lekar lekarZaIzmenu) throws Exception {
        IzmeniLekarSO operacija = new IzmeniLekarSO();
        operacija.izvrsi(lekarZaIzmenu, null);
    }
    

}
