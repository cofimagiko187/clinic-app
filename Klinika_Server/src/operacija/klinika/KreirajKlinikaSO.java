/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klinika;

import java.util.List;
import model.Klinika;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class KreirajKlinikaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (!(objekat instanceof Klinika)) {
            throw new Exception("Parametar nije tipa Klinika!");
        }
        
        Klinika klinika = (Klinika) objekat;
        List<Klinika> sveKlinike = (List<Klinika>) broker.getAll(new Klinika(), null);

        for (Klinika postojecaKlinika : sveKlinike) {
            
            if (postojecaKlinika.getAdresa().equalsIgnoreCase(klinika.getAdresa())) {
                throw new Exception("Klinika sa tom adresom vec postoji u bazi!");
            }
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Klinika)objekat);
    }
    
}
