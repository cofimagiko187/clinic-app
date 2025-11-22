/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.strucnasprema;

import model.StrucnaSprema;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class KreirajStrucnaSpremaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof StrucnaSprema)) {
            throw new Exception("Nisu poslati ispravni podaci za kreiranje strucne spreme.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((StrucnaSprema)objekat);
    }
}
