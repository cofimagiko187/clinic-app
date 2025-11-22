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
public class Pacijent implements ApstraktniDomenskiObjekat{
    private int idPacijent;
    private String ime;
    private String jmbg;
    private String prezime;
    private String mail;
    private Klinika klinika;

    public Pacijent() {
    }

    public Pacijent(int idPacijent, String ime, String jmbg, String prezime, String mail, Klinika klinika) {
        this.idPacijent = idPacijent;
        this.ime = ime;
        this.jmbg = jmbg;
        this.prezime = prezime;
        this.mail = mail;
        this.klinika = klinika;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
    }

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
        this.idPacijent = idPacijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

     
    
    
    @Override
    public String vratiNazivTabele() {
        return "pacijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
       List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
       while (rs.next()){
           int id = rs.getInt("pacijent.idPacijent");
           String ime = rs.getString("pacijent.ime");
           String prezime = rs.getString("pacijent.prezime");
           String mail = rs.getString("pacijent.mail");
           String jmbg = rs.getString("pacijent.jmbg");
           
           int idKlinike = rs.getInt("klinika.idKlinika");
            String adresa = rs.getString("klinika.adresa");
            String grad = rs.getString("klinika.grad");
            String telefon = rs.getString("klinika.telefon");
            Klinika k = new Klinika(idKlinike, adresa, grad, telefon);
            
            Pacijent p = new Pacijent(id, ime, jmbg, prezime, mail, k);
            lista.add(p);
           
       }
       return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, jmbg, prezime, mail, klinika";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+jmbg+"','"+prezime+"', '"+mail+"',"+klinika.getIdKlinika();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "pacijent.idPacijent= "+idPacijent;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + 
               "', jmbg='" + jmbg + 
               "', prezime='" + prezime + 
               "', mail='" + mail + 
               "', klinika="+klinika.getIdKlinika();
    }
    
}
