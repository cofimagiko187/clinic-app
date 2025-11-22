/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pacijenti;

import model.Usluga;
import model.Pacijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class ObrisiPacijentSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Pacijent)){
            throw new Exception("Sistem ne može da obriše pacijenta.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Pacijent) objekat);
    }
    
}
