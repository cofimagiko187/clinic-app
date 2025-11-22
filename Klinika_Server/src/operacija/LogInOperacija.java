/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.List;
import model.Lekar;

/**
 *
 * @author Filip
 */
public class LogInOperacija extends ApstraktnaGenerickaOperacija{
    Lekar lekar;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Lekar)){
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            List<Lekar> sviLekari = broker.getAll((Lekar) objekat, null);
            System.out.println("Klasa LogInOperacija SO: "+sviLekari);
            
            
        
        
        
        if(sviLekari.contains((Lekar) objekat)){
            for (Lekar l : sviLekari) {
                if(l.equals((Lekar) objekat)){
                    lekar = l;
                    return;
                }
            }
        }else{
            lekar = null;
        }
        } catch (ClassNotFoundException e) {
            System.out.println("Greška: ClassNotFoundException! "+e.getMessage());
    throw e;
        }
        
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }
    
}
