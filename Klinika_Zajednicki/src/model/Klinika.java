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
public class Klinika implements ApstraktniDomenskiObjekat{
    private int idKlinika;
    private String adresa;
    private String grad;
    private String telefon;

    public Klinika() {
    }

    public Klinika(int idKlinika, String adresa, String grad, String telefon) {
        this.idKlinika = idKlinika;
        this.adresa = adresa;
        this.grad = grad;
        this.telefon = telefon;
    }

    public int getIdKlinika() {
        return idKlinika;
    }

    public void setIdKlinika(int idKlinika) {
        this.idKlinika = idKlinika;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Klinika(String adresa, String grad, String telefon) {
        this.adresa = adresa;
        this.grad = grad;
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Klinika na adresi: "+ adresa + ", " + grad;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "klinika";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
      List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("klinika.idKlinika");
            String adresa = rs.getString("klinika.adresa");
            String grad = rs.getString("klinika.grad");
            String telefon = rs.getString("klinika.telefon");
            Klinika k = new Klinika(id, adresa, grad, telefon);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
       return "adresa, grad, telefon";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+adresa+"', '"+grad+"', '"+telefon+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "klinika.idKlinika="+idKlinika;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "adresa='" + adresa + 
               "', grad='" + grad + "', telefon="+telefon+"'";
              
    }
    
    
}
