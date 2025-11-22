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
public class IzmeniUslugaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Usluga)){
            throw new Exception("Sistem ne može da doda uslugu.");
        }
        Usluga u = (Usluga) objekat;
        if(u.getNaziv()==null || u.getNaziv().isEmpty()){
            throw new Exception("Greska naziv usluge!");
        }else if(u.getOpis()==null || u.getOpis().isEmpty()){
            throw new Exception("Greska opis usluge!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Usluga) objekat);
    }
    
}
