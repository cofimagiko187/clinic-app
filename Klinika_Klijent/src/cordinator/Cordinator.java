/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import forme.DodajUsluguForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.*;
import forme.PrikazUslugeForma;
import forme.PrikazPacijenataForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.*;
import model.Lekar;
import model.Racun;
import forme.IzmenaLekaraForma;

/**
 *
 * @author Filip
 */
public class Cordinator {
    private static Cordinator instance;
    private Lekar ulogovani;
    private LogInController logInController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazUslugeController prikazUslugeController;
    private DodajUsluguController duController;
    private DodajPacijentaController dpaController;
    private DodajKlinikuController dKController;
    private PrikazStrucnaSpremaController psController;
    private DodajStrucnuSpremuController dsController;
    private PrikazRacunaController prController;
    private PrikazStavkiRacunaController psrController;
    private DodajRacunController drController;
    private IzmenaLekaraController ilController;
    private Map<String, Object> parametri;
    
    private PrikazPacijenataController prikazPacijenataController;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstance() {
        if(instance == null)
            instance = new Cordinator();
        return instance;
    }

    public Lekar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Lekar ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriLogInFormu() {
        logInController = new LogInController(new LogInForma());
        logInController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }
    
    public void otvoriDodajUsluguFormu() {
        duController = new DodajUsluguController(new DodajUsluguForma());
        duController.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void otvoriPrikazUslugeFormu() {
        prikazUslugeController = new PrikazUslugeController(new PrikazUslugeForma());
        prikazUslugeController.otvoriFormu();
    }

    public void otvoriIzmeniUsluguFormu() {
        duController = new DodajUsluguController(new DodajUsluguForma());
        duController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        prikazUslugeController.osveziFormu();
    }
    
    public void otvoriPrikazPacijenataFormu() {
        prikazPacijenataController = new PrikazPacijenataController(new PrikazPacijenataForma());
        prikazPacijenataController.otvoriFormu();
    }

    public void otvoriDodajPacijentaFormu() {
        dpaController = new DodajPacijentaController(new DodajPacijentaForma());
        dpaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriDodajKlinikuFormu() {
        dKController = new DodajKlinikuController(new DodajKlinikuForma(), dpaController);
        dKController.otvoriFormu();
    }

    public void otvoriIzmeniPacijentFormu() {
        dpaController = new DodajPacijentaController(new DodajPacijentaForma());
        dpaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuPacijent() {
        prikazPacijenataController.osveziFormu();
    }

    public void otvoriPrikaziStrucnaSpremaFormu() {
        psController = new PrikazStrucnaSpremaController(new PrikazStrucnaSpremaForma());
        psController.otvoriFormu();
    }

    public void otvoriDodajStrucnuSpremuFormu() {
        dsController = new DodajStrucnuSpremuController(new DodajStrucnuSpremuForma());
        dsController.otvoriFormu();
    }
    
    public void otvoriPrikazRacunaFormu() {
        prController = new PrikazRacunaController(new PrikazRacunaForma());
        prController.otvoriFormu();
    }

    public void otvoriPrikazStavkiRFormu(Racun odabraniRacun) {
        psrController = new PrikazStavkiRacunaController(new PrikazStavkiRacunaForma(), odabraniRacun);
        psrController.otvoriFormu();
    }

    public void otvoriDodajracunFormu() throws Exception {
        drController = new DodajRacunController(new DodajRacunForma(), FormaMod.DODAJ, null);
        drController.otvoriFormu();
    }
    
    public void otvoriIzmeniRacunFormu(Racun odabraniRacun) throws Exception {
        drController = new DodajRacunController(new DodajRacunForma(), FormaMod.IZMENI, odabraniRacun);
        drController.otvoriFormu();
    }
    
    public void vratiSeNaGlavnuFormu() {
        if (prController != null) {
            prController.osveziTabelu();
        }
    }
    
    public void otvoriIzmeniLekaraFormu(Lekar lekarZaIzmenu) {
        IzmenaLekaraForma ilf = new IzmenaLekaraForma();
        ilController = new IzmenaLekaraController(ilf, lekarZaIzmenu);
        ilController.otvoriFormu();
    }
    
    public void osveziPrikazLekara() {
        if (glavnaFormaController != null) {
            glavnaFormaController.osveziPrikazUlogovanogLekara();
        }
    }
}
