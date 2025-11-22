/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajUsluguForma;
import forme.DodajPacijentaForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Usluga;
import model.Pacijent;
import model.Klinika;

/**
 *
 * @author Filip
 */
public class DodajPacijentaController {
    private final DodajPacijentaForma dpaf;

    public DodajPacijentaController(DodajPacijentaForma dpaf) {
        this.dpaf = dpaf;
        dpaf.setTitle("Ubacivanje/Izmena pacijenta");
        dpaf.setLocationRelativeTo(null);
        dpaf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
        ucitajKlinike();
    }
    
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);
        dpaf.setVisible(true);
    }

    private void addActionListener() {
            dpaf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
            private void dodaj(ActionEvent e){
                String ime = dpaf.getjTextFieldIme().getText().trim();
                String prezime = dpaf.getjTextFieldPrezime().getText().trim();
                String mail = dpaf.getjTextFieldMail().getText().trim();
                String jmbg = dpaf.getjTextFieldJMBG().getText().trim();
                Klinika izabranaKlinika = (Klinika) dpaf.getjComboBoxKlinika().getSelectedItem();
                
                if(ime.isEmpty() || izabranaKlinika == null || prezime.isEmpty() || mail.isEmpty() || jmbg.isEmpty()){
                     JOptionPane.showMessageDialog(dpaf, "Sistem ne moze da zapamti pacijenta.", "Greska!", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                
                Pacijent noviPacijent = new Pacijent(-1, ime, jmbg ,prezime, mail, izabranaKlinika);
                
                
                try{
                    Komunikacija.getInstance().dodajPacijenta(noviPacijent);
                    JOptionPane.showMessageDialog(dpaf, "Sistem je zapamtio pacijenta.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dpaf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dpaf, "Sistem ne moze da zapamti pacijenta.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
            
        dpaf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
            private void izmeni(ActionEvent e){
                int id = Integer.parseInt(dpaf.getjTextFieldIdPacijenta().getText().trim());
                String ime = dpaf.getjTextFieldIme().getText().trim();
                String prezime = dpaf.getjTextFieldPrezime().getText().trim();
                String mail = dpaf.getjTextFieldMail().getText().trim();
                String jmbg = dpaf.getjTextFieldJMBG().getText().trim();
                Klinika izabranaKlinika = (Klinika) dpaf.getjComboBoxKlinika().getSelectedItem();
                
                Pacijent noviPacijent = new Pacijent(id, ime, jmbg ,prezime, mail, izabranaKlinika);
                
                try{
                    Komunikacija.getInstance().izmeniPacijenta(noviPacijent);
                    JOptionPane.showMessageDialog(dpaf, "Sistem je zapamtio pacijenta.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dpaf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dpaf, "Sistem ne moze da zapamti pacijenta.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dpaf.getjTextFieldIdPacijenta().setEnabled(false);
                dpaf.getjButtonIzmeni().setVisible(false);
                dpaf.getjButtonDodaj().setVisible(true);
                dpaf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dpaf.getjButtonIzmeni().setVisible(true);
                dpaf.getjButtonDodaj().setVisible(false);
                dpaf.getjButtonIzmeni().setEnabled(true);
                Pacijent pa = (Pacijent) Cordinator.getInstance().vratiParam("pacijent");
                dpaf.getjTextFieldIdPacijenta().setText(pa.getIdPacijent()+"");
                dpaf.getjTextFieldIme().setText(pa.getIme());
                dpaf.getjTextFieldPrezime().setText(pa.getPrezime());
                dpaf.getjTextFieldMail().setText(pa.getMail());
                dpaf.getjTextFieldJMBG().setText(pa.getJmbg());
                
                
                for (int i = 0; i < dpaf.getjComboBoxKlinika().getItemCount(); i++) {
                    Klinika k = (Klinika) dpaf.getjComboBoxKlinika().getItemAt(i);
                    
                    if (k.getIdKlinika()== pa.getKlinika().getIdKlinika()) {
                        dpaf.getjComboBoxKlinika().setSelectedItem(k);
                        break; 
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    
        public void ucitajKlinike() {
        try {
            List<Klinika> klinike = Komunikacija.getInstance().ucitajKlinike();
            dpaf.getjComboBoxKlinika().removeAllItems();
            for (Klinika klinika : klinike) {
                dpaf.getjComboBoxKlinika().addItem(klinika);
            }
            dpaf.getjComboBoxKlinika().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dpaf, "Greška prilikom učitavanja klinika: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
