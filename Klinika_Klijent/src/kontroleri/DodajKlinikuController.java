/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Klinika;

/**
 *
 * @author Filip
 */
public class DodajKlinikuController {
    
    private final DodajKlinikuForma forma;
    private final DodajPacijentaController roditeljskiKontroler; 

    public DodajKlinikuController(DodajKlinikuForma forma, DodajPacijentaController roditeljskiKontroler) {
        this.forma = forma;
        this.roditeljskiKontroler = roditeljskiKontroler;
        forma.setTitle("Ubacivanje nove klinike");
        forma.setLocationRelativeTo(null);
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }
    
    private void addActionListener() {
        forma.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajKliniku();
            }

            private void sacuvajKliniku() {
                try {
                    String adresaKlinike = forma.getjTextFieldAdresa().getText().trim();
                    String grad = forma.getjTextFieldGrad().getText().trim();
                    String telefon = forma.getjTextFieldTelefon().getText().trim();
                    
                    if (adresaKlinike.isEmpty() || grad.isEmpty() || telefon.isEmpty()) {
                        JOptionPane.showMessageDialog(forma, "Sva polja moraju biti popunjena!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    
                    Klinika novaKlinika = new Klinika(-1, adresaKlinike, grad, telefon);
                    

                    Komunikacija.getInstance().dodajKliniku(novaKlinika);
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio kliniku.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    roditeljskiKontroler.ucitajKlinike();
                    
                    forma.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(forma, "Greška prilikom dodavanja klinike: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
