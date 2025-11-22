/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

/**
 *
 * @author Filip
 */

import cordinator.Cordinator;
import forme.FormaMod;
import forme.IzmenaLekaraForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Lekar;

public class IzmenaLekaraController {
    
    private final IzmenaLekaraForma ilf;
    private Lekar lekar;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

    public IzmenaLekaraController(IzmenaLekaraForma ilf, Lekar lekar) {
        this.ilf = ilf;
        this.lekar = lekar;
        
        ilf.setTitle("Izmena lekara");
        ilf.setLocationRelativeTo(null);
        ilf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        pripremiFormu();
        dodajActionListenere();
    }
    
    public void otvoriFormu() {
        ilf.setVisible(true);
    }

    private void pripremiFormu() {
        
        if (lekar != null) {
            ilf.getjTextFieldID().setText(String.valueOf(lekar.getIdLekar()));
            ilf.getjTextFieldID().setEditable(false); 
            ilf.getjTextFieldIme().setText(lekar.getIme());
            ilf.getjTextFieldPrezime().setText(lekar.getPrezime());
            
            
            ilf.getjTextFieldMail().setText(lekar.getMail());
            
            ilf.getjTextFieldKorisnickoIme().setText(lekar.getUsername());
            ilf.getjTextFieldSifra().setText(lekar.getPassword());
        }
    }
    
    private void dodajActionListenere() {
        ilf.addBtnSacuvajIzmeneActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmene();
            }
        });
    }

    private void sacuvajIzmene() {
        try {
           
            String ime = ilf.getjTextFieldIme().getText().trim();
            String prezime = ilf.getjTextFieldPrezime().getText().trim();
            String mail = ilf.getjTextFieldMail().getText().trim();
            String korisnickoIme = ilf.getjTextFieldKorisnickoIme().getText().trim();
            String sifra = ilf.getjTextFieldSifra().getText().trim();

            if (ime.isEmpty() || prezime.isEmpty() || mail.isEmpty() || korisnickoIme.isEmpty() || sifra.isEmpty()) {
                JOptionPane.showMessageDialog(ilf, "Sva polja moraju biti popunjena!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
           
            
            
            this.lekar.setIme(ime);
            this.lekar.setPrezime(prezime);
            this.lekar.setMail(mail);
            this.lekar.setUsername(korisnickoIme);
            this.lekar.setPassword(sifra);

            
            Komunikacija.getInstance().izmeniLekara(this.lekar);
            
          
            Cordinator.getInstance().setUlogovani(this.lekar);

            JOptionPane.showMessageDialog(ilf, "Sistem je zapamtio izmene lekara.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Cordinator.getInstance().osveziPrikazLekara();
            ilf.dispose(); 
            
       
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ilf, "Sistem ne može da zapamti izmene lekara.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
