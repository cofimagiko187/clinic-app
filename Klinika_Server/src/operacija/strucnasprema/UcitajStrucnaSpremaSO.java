/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.strucnasprema;

import model.StrucnaSprema;
import operacija.ApstraktnaGenerickaOperacija;
import java.util.*;

/**
 *
 * @author Filip
 */
public class UcitajStrucnaSpremaSO extends ApstraktnaGenerickaOperacija{
    
    List<StrucnaSprema> strucneSpreme;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        strucneSpreme = broker.getAll(new StrucnaSprema(), null);
    }

    public List<StrucnaSprema> getStrucneSpreme() {
        return strucneSpreme;
    }

    public void setStrucneSpreme(List<StrucnaSprema> strucneSpreme) {
        this.strucneSpreme = strucneSpreme;
    }
    
}
