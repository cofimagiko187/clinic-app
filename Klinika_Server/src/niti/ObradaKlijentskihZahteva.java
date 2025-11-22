/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.net.*;
import model.*;
import komunikacija.*;
import domen.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static komunikacija.Operacija.DODAJ_USLUGU;
import static komunikacija.Operacija.UCITAJ_RACUNE;
import operacija.racun.UcitajRacunSO;
import repository.controller.Controller;

/**
 *
 * @author Filip
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        posiljalac = new Posiljalac(s);
        primalac = new Primalac(s);
    }

    @Override
    public void run() {
        try{
            while(!kraj){
                Zahtev zahtev = (Zahtev)primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        try {
                            Lekar l = (Lekar) zahtev.getParametar();
                            l = Controller.getInstance().login(l);
                            odgovor.setOdgovor(l);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_USLUGE:
                        try {
                            List<Usluga> usluge = Controller.getInstance().ucitajUsluge();
                            odgovor.setOdgovor(usluge);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_USLUGU:
                        try{
                            Usluga u = (Usluga) zahtev.getParametar();
                            Controller.getInstance().obrisiUsluga(u);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_USLUGU:
                        try {
                            Usluga novaUsluga = (Usluga) zahtev.getParametar();
                            Controller.getInstance().dodajUslugu(novaUsluga);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case IZMENI_USLUGU:
                        try {
                           Usluga uslugaZaIzmenu = (Usluga) zahtev.getParametar();
                            Controller.getInstance().izmeniUsluga(uslugaZaIzmenu);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_PACIJENTE:
                        try {
                            List<Pacijent> pacijenti = Controller.getInstance().ucitajPacijente();
                            odgovor.setOdgovor(pacijenti);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_PACIJENT:
                        try{
                            Pacijent pa = (Pacijent) zahtev.getParametar();
                            Controller.getInstance().obrisiPacijent(pa);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_KLINIKA:
                        try {
                            List<Klinika> klinike = Controller.getInstance().ucitajKlinike();
                            odgovor.setOdgovor(klinike);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_PACIJENTA:
                        try{
                            Pacijent noviPacijent = (Pacijent) zahtev.getParametar();
                            Controller.getInstance().dodajPacijenta(noviPacijent);
                            System.out.println(noviPacijent);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                            System.out.println("Error, klasa ObradaKZ: " + ex.getMessage());
                        }
                        break;
                    case DODAJ_KLINIKA:
                    try {
                        Klinika novaKlinika = (Klinika) zahtev.getParametar();
                        Controller.getInstance().dodajKliniku(novaKlinika);
                        odgovor.setOdgovor(null);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Error, klasa ObradaKZ: " + ex.getMessage());
                    }
                    break;
                    case IZMENI_PACIJENT:
                    try {
                        Pacijent pacijentZaIzmenu = (Pacijent) zahtev.getParametar();
                        Controller.getInstance().izmeniPacijent(pacijentZaIzmenu);
                        odgovor.setOdgovor(null); 
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                    }
                    break;
                    case UCITAJ_STRUCNASPREMA:
                        try {
                            List<StrucnaSprema> strucneSpreme = Controller.getInstance().ucitajStrucneSpreme();
                            odgovor.setOdgovor(strucneSpreme);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_STRUCNASPREMA:
                        try {
                            StrucnaSprema novaStrucnaSprema = (StrucnaSprema) zahtev.getParametar();
                            Controller.getInstance().dodajStrucnaSprema(novaStrucnaSprema);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                            System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                        }
                        break;
                case UCITAJ_RACUNE:
                    try {
                        List<Racun> racuni = Controller.getInstance().ucitajRacune();
                        odgovor.setOdgovor(racuni);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                case UCITAJ_STAVKE_RACUNA:
                    try {
                        List<StavkaRacuna> stavke = Controller.getInstance().ucitajStavkeRacuna((Racun)zahtev.getParametar());
                        odgovor.setOdgovor(stavke);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                case UCITAJ_LEKARE:
                    try {
                        List<Lekar> lekari = Controller.getInstance().ucitajLekare();
                        odgovor.setOdgovor(lekari);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                    case KREIRAJ_RACUN: 
                        try {
                            Racun noviRacun = (Racun) zahtev.getParametar();
                            Controller.getInstance().dodajRacun(noviRacun);
                            odgovor.setOdgovor(null); 
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex); 
                            System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                        }
                        break;
                case PRETRAZI_RACUNE:
                    try {
                        Racun racunZaPretragu = (Racun) zahtev.getParametar();
                        List<Racun> filtriraniRacuni = Controller.getInstance().pretraziRacune(racunZaPretragu);
                        odgovor.setOdgovor(filtriraniRacuni);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                    }
                    break;
                case IZMENI_RACUN:
                    try {
                        Racun racunZaIzmenu = (Racun) zahtev.getParametar();
                        Controller.getInstance().izmeniRacun(racunZaIzmenu);
                        odgovor.setOdgovor(null); 
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex); 
                        System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                    }
                    break;
                case IZMENI_LEKAR:
                    try {
                        Lekar lekarZaIzmenu = (Lekar) zahtev.getParametar();
                        Controller.getInstance().izmeniLekara(lekarZaIzmenu);
                        odgovor.setOdgovor(null);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greška prilikom izmene lekara: " + ex.getMessage());
                    }
                    break;
                    default:
                        System.out.println("Greska! Operacija ne postoji.");
                }
                posiljalac.posalji(odgovor);
            }
        }catch(Exception e){
//            e.printStackTrace();
            System.out.println("Klijent je prekinuo konekciju: "+e.getMessage());
        }
    }
    
    public void prekini(){
        kraj = true;
        try {
            s.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        interrupt();
    }
  
}
