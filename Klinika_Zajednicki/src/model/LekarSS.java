/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Filip
 */
public class LekarSS implements ApstraktniDomenskiObjekat{
    private Date datumSticanja;
    private Lekar lekar;
    private StrucnaSprema strucnaSprema;

    public LekarSS() {
    }

    public LekarSS(Date datumSticanja, Lekar lekar, StrucnaSprema strucnaSprema) {
        this.datumSticanja = datumSticanja;
        this.lekar = lekar;
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    
    
    
    @Override
    public String vratiNazivTabele() {
        return "lekarss";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()){
            Date datum = rs.getDate("lekarss.datum");
            
            int idLekar = rs.getInt("lekar.idLekar");
            String ime = rs.getString("lekar.ime");
            String prezime = rs.getString("lekar.prezime");
            String mail = rs.getString("lekar.mail");
            String username = rs.getString("lekar.username");
            String password = rs.getString("lekar.password");
            Lekar lekar = new Lekar(idLekar, ime, prezime, mail, username, password);
            
            int idStrucnaSprema = rs.getInt("strucnasprema.idStrucnaSprema");
            String naziv = rs.getString("strucnasprema.naziv");
            String opis = rs.getString("strucnasprema.opis");
            int trajanjeObrazovanja = rs.getInt("strucnasprema.trajanjeObrazovanja");
            
            StrucnaSprema ss = new StrucnaSprema(idStrucnaSprema, naziv, opis, trajanjeObrazovanja);
            
            LekarSS lekarss = new LekarSS(datum, lekar, ss);
            lista.add(lekarss);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "lekar, strucnasprema, datumSticanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return lekar.getIdLekar() + "," + strucnaSprema.getIdStrucnaSprema() +",'"+datumSticanja+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "lekar="+lekar.getIdLekar()+" AND strucnasprema="+strucnaSprema.getIdStrucnaSprema();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumSticanja='" + datumSticanja.toString() + "'";
    }
    
    
}
