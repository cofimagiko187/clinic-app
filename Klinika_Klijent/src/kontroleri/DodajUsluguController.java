/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import static forme.FormaMod.*;
import forme.model.ModelTabeleUsluge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Usluga;
import cordinator.Cordinator;
import javax.swing.JFrame;

/**
 *
 * @author Filip
 */
public class DodajUsluguController {
    private final DodajUsluguForma duf;

    public DodajUsluguController(DodajUsluguForma duf) {
        this.duf = duf;
        duf.setTitle("Ubacivanje/Izmena usluge");
        duf.setLocationRelativeTo(null);
        duf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        duf.getjTextAreaNoviOpis().setLineWrap(true);
        duf.getjTextAreaNoviOpis().setWrapStyleWord(true);
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);
        duf.setVisible(true);
    }

    private void addActionListener() {
            duf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
            private void dodaj(ActionEvent e){
                String noviNaziv = duf.getjTextFieldNoviNazivUsluge().getText().trim();
                String noviOpis = duf.getjTextAreaNoviOpis().getText();
                double cena;
                
                try {
                    cena = Double.parseDouble(duf.getjTextFieldCenaUsluge().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(duf, "Cena mora biti broj.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                Usluga novaUsluga = new Usluga(-1, noviNaziv, cena, noviOpis);
                
                try{
                    Komunikacija.getInstance().dodajUslugu(novaUsluga);
                    JOptionPane.showMessageDialog(duf, "Sistem je zapamtio uslugu.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    duf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(duf, "Sistem ne moze da zapamti uslugu.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
            
            duf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
            private void izmeni(ActionEvent e){
                int sifra = Integer.parseInt(duf.getjTextFieldIdUsluge().getText());
                String noviNaziv = duf.getjTextFieldNoviNazivUsluge().getText().trim();
                String noviOpis = duf.getjTextAreaNoviOpis().getText();
                double novaCena;

                try {
                    novaCena = Double.parseDouble(duf.getjTextFieldCenaUsluge().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(duf, "Cena mora biti broj.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
               
                Usluga novaUsluga = new Usluga(sifra, noviNaziv, novaCena, noviOpis);
                
                try{
                    Komunikacija.getInstance().izmeniUslugu(novaUsluga);
                    JOptionPane.showMessageDialog(duf, "Sistem je zapamtio uslugu.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    duf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(duf, "Sistem ne moze da zapamti uslugu.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                duf.getjTextFieldIdUsluge().setEnabled(false);
                duf.getjButtonIzmeni().setVisible(false);
                duf.getjButtonDodaj().setVisible(true);
                duf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                duf.getjButtonIzmeni().setVisible(true);
                duf.getjButtonDodaj().setVisible(false);
                duf.getjButtonIzmeni().setEnabled(true);
                
                Usluga u = (Usluga) Cordinator.getInstance().vratiParam("usluga");
                duf.getjTextFieldIdUsluge().setText(u.getIdUsluga()+"");
                duf.getjTextFieldNoviNazivUsluge().setText(u.getNaziv());
                duf.getjTextAreaNoviOpis().setText(u.getOpis());
                duf.getjTextFieldCenaUsluge().setText(String.valueOf(u.getCena()));

                break;
            default:
                throw new AssertionError();
        }
    }
    
    
}
