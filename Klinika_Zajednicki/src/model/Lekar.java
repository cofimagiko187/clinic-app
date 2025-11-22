/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip
 */
public class Lekar implements ApstraktniDomenskiObjekat{
    
    private int idLekar;
    private String ime;
    private String prezime;
    private String mail;
    private String username;
    private String password;

    public Lekar() {
    }

    public Lekar(int idLekar, String ime, String prezime, String mail, String username, String password) {
        this.idLekar = idLekar;
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public Lekar(String ime, String prezime, String mail, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdLekar() {
        return idLekar;
    }

    public void setIdLekar(int idLekar) {
        this.idLekar = idLekar;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "lekar";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("lekar.idLekar");
            String ime = rs.getString("lekar.ime");
            String prezime = rs.getString("lekar.prezime");
            String mail = rs.getString("lekar.mail");
            String username = rs.getString("lekar.username");
            String password = rs.getString("lekar.password");
            Lekar l = new Lekar(id, ime, prezime, mail, username, password);
            lista.add(l);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, mail, username, password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+mail+"','"+username+"','"+password+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "lekar.idLekar="+idLekar;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + 
               "', prezime='" + prezime + 
               "', mail='" + mail + 
               "', username='" + username +  
               "', password='" + password + "'";
    }

    @Override
    public String toString() {
        return "Lekar " + ime+" "+ prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lekar other = (Lekar) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }
    
}
