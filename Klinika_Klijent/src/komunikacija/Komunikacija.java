/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.*;
import model.*;
import komunikacija.Komunikacija;
import cordinator.Cordinator;

/**
 *
 * @author Filip
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    public void konekcija(){
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Greska pri povezivanju sa serverom!");
        }
    }

    public Lekar logIn(String user, String pass) {
        Lekar l = new Lekar();
        l.setUsername(user);
        l.setPassword(pass);
        
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, l);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        l = (Lekar) odgovor.getOdgovor();
        return l;
    }

    public List<Usluga> ucitajUsluge() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_USLUGE, null);
        List<Usluga> usluge = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        usluge = (List<Usluga>) odgovor.getOdgovor();
        return usluge;
    }

    public void obrisiUslugu(Usluga u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_USLUGU, u);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Sistem je obrisao uslugu.");
        }else{
            System.out.println("Greska!");
            ((Exception)odgovor.getOdgovor()).getMessage();
            throw new Exception("Greska!");
        }
    }

    public void dodajUslugu(Usluga novaUsluga) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_USLUGU, novaUsluga);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }else{
            System.out.println("Greska!!");
        }
    }

    public void izmeniUslugu(Usluga novaUsluga) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_USLUGU, novaUsluga);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
            Cordinator.getInstance().osveziFormu();
        }else{
            System.out.println("Greska!");
        }
    }

    public List<Pacijent> ucitajPacijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PACIJENTE, null);
        List<Pacijent> pacijenti = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        pacijenti = (List<Pacijent>) odgovor.getOdgovor();
        return pacijenti;
    }

    public void obrisiPacijenta(Pacijent pa) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_PACIJENT, pa);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Sistem je obrisao pacijenta.");
        }else{
            System.out.println("Greska!");
            ((Exception)odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greska!");
        }
    }

    public List<Klinika> ucitajKlinike() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLINIKA, null);
        List<Klinika> klinike = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        klinike = (List<Klinika>) odgovor.getOdgovor();
        return klinike;
    }

    public void dodajPacijenta(Pacijent noviPacijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_PACIJENTA, noviPacijent);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }

    public void dodajKliniku(Klinika novaKlinika) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLINIKA, novaKlinika);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }

    public void izmeniPacijenta(Pacijent noviPacijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_PACIJENT, noviPacijent);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
            Cordinator.getInstance().osveziFormuPacijent();
        }
    }

    public List<StrucnaSprema> ucitajStrucneSpreme() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STRUCNASPREMA, null);
        List<StrucnaSprema> strucneSpreme = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        strucneSpreme = (List<StrucnaSprema>) odgovor.getOdgovor();
        return strucneSpreme;
    }
    
    public void dodajStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_STRUCNASPREMA, strucnaSprema);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public List<Racun> ucitajListuRacuna() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Racun>) odgovor.getOdgovor();
    }

    public List<StavkaRacuna> ucitajStavkeRacuna(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_RACUNA, racun);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<StavkaRacuna>) odgovor.getOdgovor();
    }

    public List<Lekar> ucitajLekare() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LEKARE, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Lekar>) odgovor.getOdgovor();
    }
    
    
    public void kreirajRacun(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_RACUN, racun);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

       
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je uspešno kreirao račun.");
        }
    }
    
    public List<Racun> pretraziRacune(Racun racunZaPretragu) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_RACUNE, racunZaPretragu);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Racun>) odgovor.getOdgovor();
    }
    
    public void izmeniRacun(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_RACUN, racun);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }
    
    public void izmeniLekara(Lekar lekar) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_LEKAR, lekar);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
    }
    
}