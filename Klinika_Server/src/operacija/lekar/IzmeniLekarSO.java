/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.lekar;

/**
 *
 * @author Filip
 */

import model.Lekar;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniLekarSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Lekar)) {
            throw new Exception("Sistem ne može da izmeni lekara. Greška u prosleđenom parametru.");
        }
        Lekar l = (Lekar) objekat;
        
        if (l.getIme() == null || l.getIme().isEmpty() || l.getPrezime() == null || l.getPrezime().isEmpty()) {
            throw new Exception("Ime i prezime lekara ne sme biti prazno!");
        }
        if (l.getUsername()== null || l.getUsername().isEmpty()) {
            throw new Exception("Korisničko ime lekara ne sme biti prazno!");
        }
        if (l.getPassword()== null || l.getPassword().isEmpty()) {
            throw new Exception("Šifra lekara ne sme biti prazna!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Lekar) objekat);
    }
}
