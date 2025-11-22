
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.lekar;

import java.util.List;
import model.Lekar;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class UcitajLekarSO extends ApstraktnaGenerickaOperacija{
    
    List<Lekar> lekari;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lekari = broker.getAll(new Lekar(), "");
    }

    public List<Lekar> getLekari() {
        return lekari;
    }

    public void setLekari(List<Lekar> lekari) {
        this.lekari = lekari;
    }
    
}
