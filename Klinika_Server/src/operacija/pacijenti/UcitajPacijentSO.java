/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pacijenti;

import java.util.List;
import model.Pacijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class UcitajPacijentSO extends ApstraktnaGenerickaOperacija{
    
    List<Pacijent> pacijenti;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN klinika ON pacijent.klinika = klinika.idKlinika";
        
        try{
            pacijenti = broker.getAll(new Pacijent(), uslov);
        }catch(Exception e){
            System.out.println("UcitajPacijenteSO: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Pacijent> getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(List<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
    }
    
}
