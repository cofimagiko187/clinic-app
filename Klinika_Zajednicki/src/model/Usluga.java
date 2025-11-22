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
public class Usluga implements ApstraktniDomenskiObjekat{
    
    private int idUsluga;
    private String naziv;
    private double cena;
    private String opis;
    

    public Usluga() {
    }

    public Usluga(int idUsluga, String naziv, double cena, String opis) {
        this.idUsluga = idUsluga;
        this.naziv = naziv;
        this.cena = cena;
        this.opis = opis;
        
    }

    public int getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(int idUsluga) {
        this.idUsluga = idUsluga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return  naziv;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
       List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
       while (rs.next()){
           int idUsl = rs.getInt("usluga.idUsluga");
           String naziv = rs.getString("usluga.naziv");
           double cena = rs.getDouble("usluga.cena");
           String opis = rs.getString("usluga.opis");
           
           
           Usluga u = new Usluga(idUsl, naziv, cena, opis);
           lista.add(u);
       }
       return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, cena, opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return "'" + naziv + "','" + cena + "','" + opis+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "usluga.idUsluga="+idUsluga;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int idUsluga = rs.getInt("usluga.idUsluga");
            String naziv = rs.getString("usluga.naziv");
            String opis = rs.getString("usluga.opis");
            double cena = rs.getDouble("usluga.cena");
            return new Usluga(idUsluga, naziv, cena, opis);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', cena='" + cena + "', opis='" + opis+"'";
    }
    
}
