/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.usluge;

import model.Usluga;
import java.util.*;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class UcitajUslugeSO extends ApstraktnaGenerickaOperacija{
    List<Usluga> usluge;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        usluge = broker.getAll(new Usluga(), "");
    }

    public List<Usluga> getUsluge() {
        return usluge;
    }

    public void setUsluge(List<Usluga> usluge) {
        this.usluge = usluge;
    }
    
}
