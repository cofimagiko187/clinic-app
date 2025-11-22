/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class StrucnaSprema implements ApstraktniDomenskiObjekat{
    private int idStrucnaSprema;
    private String naziv;
    private String opis;
    private int trajanjeObrazovanja;

    public StrucnaSprema() {
    }

    public StrucnaSprema(int idStrucnaSrpema, String naziv, String opis, int trajanjeObrazovanja) {
        this.idStrucnaSprema = idStrucnaSrpema;
        this.naziv = naziv;
        this.opis = opis;
        this.trajanjeObrazovanja = trajanjeObrazovanja;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getTrajanjeObrazovanja() {
        return trajanjeObrazovanja;
    }

    public void setTrajanjeObrazovanja(int trajanjeObrazovanja) {
        this.trajanjeObrazovanja = trajanjeObrazovanja;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "strucnasprema";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()){
            int idStrucnaSprema = rs.getInt("strucnasprema.idStrucnaSprema");
            String naziv = rs.getString("strucnasprema.naziv");
            String opis = rs.getString("strucnasprema.opis");
            int trajanjeObrazovanja = rs.getInt("strucnasprema.trajanjeObrazovanja");
            
            StrucnaSprema ss = new StrucnaSprema(idStrucnaSprema, naziv, opis, trajanjeObrazovanja);
            lista.add(ss);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, opis, trajanjeObrazovanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
         return "'"+naziv+"','"+opis+"','"+trajanjeObrazovanja+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "strucnasprema.idStrucnaSprema="+idStrucnaSprema;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       return "naziv='" + naziv + 
               "', opis='" + opis + 
               "', trajanjeObrazovanja='" + trajanjeObrazovanja + "'";
    }
    
}
