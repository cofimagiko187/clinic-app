/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class Racun implements ApstraktniDomenskiObjekat{
    
    private int idRacun;
    private double iznos;
    private LocalDateTime datumVreme;
    private Lekar lekar;
    private Pacijent pacijent;
    private List<StavkaRacuna> stavke;
    
    
    
    
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Racun() {
    }

    public Racun(int idRacun, double iznos, LocalDateTime datumVreme, Lekar lekar, Pacijent pacijent) {
        this.idRacun = idRacun;
        this.iznos = iznos;
        this.datumVreme = datumVreme;
        this.lekar = lekar;
        this.pacijent = pacijent;
        stavke = new ArrayList<>();
    }

    

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Račun #" + idRacun + " - " + iznos + " RSD";
    }

    
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()){
            int idR = rs.getInt("racun.idRacun");
            double iznos = rs.getDouble("racun.iznos");
            LocalDateTime datum = rs.getTimestamp("racun.datumVreme").toLocalDateTime();
            
            int idLekar = rs.getInt("lekar.idLekar");
            String imeLekar = rs.getString("lekar.ime");
            String usernameLekar = rs.getString("lekar.username");
            String prezimeLekar = rs.getString("lekar.prezime");
            String mailLekar = rs.getString("lekar.mail");
            Lekar lekar = new Lekar(idLekar, imeLekar, prezimeLekar, mailLekar, usernameLekar, null);
            
            int idPacijent = rs.getInt("pacijent.idPacijent");
            String imePacijenta = rs.getString("pacijent.ime");
            String prezimePacijenta = rs.getString("pacijent.prezime");
            String jmbg = rs.getString("pacijent.jmbg");
            String mail = rs.getString("pacijent.mail");
            
            Klinika klinikaPacijenta = null;
            
            Pacijent pacijent = new Pacijent(idPacijent, imePacijenta, jmbg, prezimePacijenta, mail, klinikaPacijenta);
            
            Racun racun = new Racun(idR, iznos, datum, lekar, pacijent);
            lista.add(racun);
        
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "iznos, datumVreme, lekar, pacijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return iznos+", '"+datumVreme.format(formatter)+"',"+lekar.getIdLekar()+","+pacijent.getIdPacijent();
    }
    //return iznos+", '"+datumVreme.format(formatter)+"',"+lekar.getIdLekar()+","+pacijent.getIdPacijent();

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRacun="+idRacun;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       
        return "iznos=" + iznos + ", datumVreme='" + datumVreme.format(formatter) + 
               "', lekar=" + lekar.getIdLekar() + 
               ", pacijent=" + pacijent.getIdPacijent();
    }
    
}
