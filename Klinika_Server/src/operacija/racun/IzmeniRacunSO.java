/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

/**
 *
 * @author Filip
 */
import java.util.List;
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Racun)) {
            throw new Exception("Sistem ne može da zapamti račun.");
        }
        Racun racun = (Racun) objekat;
        if (racun.getPacijent() == null || racun.getLekar() == null) {
            throw new Exception("Morate odabrati pacijenta i lekara za izmenu računa.");
        }
        if (racun.getStavke() == null || racun.getStavke().isEmpty()) {
            throw new Exception("Račun mora da sadrži barem jednu stavku.");
        }
    }

    @Override

    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun racun = (Racun) objekat;

        
        broker.edit(racun);

        
        List<StavkaRacuna> stavkeServer = broker.getAll(new StavkaRacuna(), " WHERE racun=" + racun.getIdRacun());

       
        List<StavkaRacuna> stavkeKlijent = racun.getStavke();

        
        for (StavkaRacuna stavkaServer : stavkeServer) {
            boolean postojiNaKlijentu = false;
            for (StavkaRacuna stavkaKlijent : stavkeKlijent) {
                if (stavkaServer.getRb() == stavkaKlijent.getRb()) {
                    postojiNaKlijentu = true;
                    
                    stavkaKlijent.setRacun(racun); 
                    broker.edit(stavkaKlijent);
                    break;
                }
            }
            if (!postojiNaKlijentu) {
                
                broker.delete(stavkaServer);
            }
        }

        
        for (StavkaRacuna stavkaKlijent : stavkeKlijent) {
            boolean postojiNaServeru = false;
            for (StavkaRacuna stavkaServer : stavkeServer) {
                if (stavkaKlijent.getRb() == stavkaServer.getRb()) {
                    postojiNaServeru = true;
                    break;
                }
            }
            if (!postojiNaServeru) {
                stavkaKlijent.setRacun(racun);
                broker.add(stavkaKlijent);
            }
        }
    }

}
