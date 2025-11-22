/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.List;
import model.Pacijent;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Filip
 */
public class UcitajRacunSO extends ApstraktnaGenerickaOperacija {

    private List<Racun> listaRacuna;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        String uslov = " JOIN pacijent ON racun.pacijent = pacijent.idPacijent JOIN lekar ON racun.lekar = lekar.idLekar";
        
        try{
            listaRacuna = broker.getAll(new Racun(), uslov);
        }catch(Exception e){
            System.out.println("UcitajPacijenteSO: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

}
