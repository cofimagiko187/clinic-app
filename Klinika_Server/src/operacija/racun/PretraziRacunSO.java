/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.List;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class PretraziRacunSO extends ApstraktnaGenerickaOperacija {

    private List<Racun> racuni;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (!(objekat instanceof Racun)) {
            throw new Exception("Greška, parametar mora biti objekat klase Racun.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun racunZaPretragu = (Racun) objekat;
        
        StringBuilder whereKlauzula = new StringBuilder();
        
        
        whereKlauzula.append(" JOIN pacijent ON racun.pacijent = pacijent.idPacijent JOIN lekar ON racun.lekar = lekar.idLekar");
        
        
        whereKlauzula.append(" WHERE 1=1");
        
       
        if (racunZaPretragu.getIdRacun() != 0) {
            whereKlauzula.append(" AND racun.idRacun = ").append(racunZaPretragu.getIdRacun());
        }
        
        if (racunZaPretragu.getPacijent() != null) {
            whereKlauzula.append(" AND racun.pacijent = ").append(racunZaPretragu.getPacijent().getIdPacijent());
        }
        
        if (racunZaPretragu.getLekar() != null) {
            whereKlauzula.append(" AND racun.lekar = ").append(racunZaPretragu.getLekar().getIdLekar());
        }

        
        

        try {
            racuni = broker.getAll(new Racun(), whereKlauzula.toString());
        } catch (Exception e) {
            System.out.println("Greska u PretraziRacuneSO: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }
}
