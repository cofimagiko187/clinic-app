/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klinika;

import java.util.List;
import model.Usluga;
import model.Klinika;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class UcitajKlinikeSO extends ApstraktnaGenerickaOperacija{
    List<Klinika> klinike;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        klinike = broker.getAll(new Klinika(), "");
    }

    public List<Klinika> getKlinike() {
        return klinike;
    }

    public void setKlinike(List<Klinika> klinike) {
        this.klinike = klinike;
    }

}
