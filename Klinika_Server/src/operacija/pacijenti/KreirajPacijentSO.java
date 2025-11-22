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
public class KreirajPacijentSO extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Pacijent)){
            throw new Exception("Sistem ne može da doda pacijenta.");
        }
        Pacijent p = (Pacijent) objekat;
        if(p.getIme()==null || p.getIme().isEmpty() || p.getPrezime()==null || p.getPrezime().isEmpty()){
            throw new Exception("Greska ime i prezime pacijenta!");
        
        }else if(p.getKlinika()==null){
            throw new Exception("Greska klinika!");
        }
        if(!p.getMail().contains("@")){
            throw new Exception("Mail mora sadrzati '@'");
        } else if(p.getKlinika()==null){
            throw new Exception("Greska klinika!");
        }
        if(p.getJmbg().length()!=13){
            throw new Exception("JMBG mora imati 13 cifara!");
        }else if(p.getKlinika()==null){
            throw new Exception("Greska klinika!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Pacijent)objekat);
    }
}
