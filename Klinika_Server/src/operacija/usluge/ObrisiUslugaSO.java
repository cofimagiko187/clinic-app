/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.usluge;

import model.Usluga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class ObrisiUslugaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Usluga)){
            throw new Exception("Sistem ne može da obriše uslugu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Usluga) objekat);
    }
    
}
