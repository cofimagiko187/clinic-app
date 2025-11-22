/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Lekar;

/**
 *
 * @author Filip
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
        pripremi();
    }

    private void addActionListeners() {
        gf.addBtnIzmeniProfilActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lekar ulogovaniLekar = Cordinator.getInstance().getUlogovani();
                
                if (ulogovaniLekar != null) {
                    Cordinator.getInstance().otvoriIzmeniLekaraFormu(ulogovaniLekar);
                } else {
                    JOptionPane.showMessageDialog(gf, "Nije pronađen ulogovani lekar.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        Lekar ulogovani = Cordinator.getInstance().getUlogovani();
        String ime = ulogovani.getIme();
        String Prezime = ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getjLabelDobrodosao().setText("Dobrodošli!");
    }
    
    private void pripremi() {
        gf.setLocationRelativeTo(null);
        gf.setTitle("Sistem za poslovanje klinike");
        
        Lekar ulogovani = Cordinator.getInstance().getUlogovani();
        
        if (ulogovani != null) {
            
            gf.getjLabelIme().setText(" " + ulogovani.getIme());
            gf.getjLabelPrezime().setText(" " + ulogovani.getPrezime());
            gf.getjLabelMail().setText(" " + ulogovani.getMail());
            
           

            
            gf.getjLabelKorisnickoIme().setText(" " + ulogovani.getUsername());
        }
        
        
        
    }

    public void osveziPrikazUlogovanogLekara() {
        pripremi();
    }
}
