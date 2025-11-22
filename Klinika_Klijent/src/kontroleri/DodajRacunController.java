/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajRacunForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.model.ModelTabeleStavkaRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Usluga;
import model.Pacijent;
import model.Lekar;
import model.Racun;
import model.StavkaRacuna;

public class DodajRacunController {
    
    private final DodajRacunForma drf;
    private Racun racun;
    private ModelTabeleStavkaRacuna modelStavke;
    Lekar lekar = Cordinator.getInstance().getUlogovani();
    
    public DodajRacunController(DodajRacunForma drf, FormaMod mod, Racun racun) throws Exception {
        this.drf = drf;
        this.racun = racun;
        
        drf.setTitle("Ubacivanje/Izmena racuna");
        drf.setLocationRelativeTo(null);
        drf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        popuniComboBoxeve();
        dodajActionListenere();
        pripremiFormu(mod);
    }
    
    public void otvoriFormu(){
        drf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                drf.getjButtonIzmeniRacun().setEnabled(false);
                drf.getjButtonKreirajRacun().setVisible(true);
                drf.getjTextFieldID().setEnabled(false);
                drf.getjTextFieldCenaStavke().setEditable(false);
                drf.getjTextFieldIznosStavke().setEditable(false);
                drf.getjTextFieldLekar().setText(lekar.getIme() +" "+lekar.getPrezime());
                drf.getjTextFieldLekar().setEditable(false);
                modelStavke = new ModelTabeleStavkaRacuna(new ArrayList<>());
                break;
            case IZMENI:
                drf.getjButtonKreirajRacun().setEnabled(false);
                drf.getjButtonIzmeniRacun().setVisible(true);
                drf.getjTextFieldID().setEditable(false);
                drf.getjTextFieldCenaStavke().setEditable(false);
                drf.getjTextFieldIznosStavke().setEditable(false);
                drf.getjTextFieldLekar().setText(lekar.getIme()+" "+lekar.getPrezime());
                drf.getjTextFieldLekar().setEditable(false);
                popuniFormuZaIzmenu(racun);
                break;
        }
        drf.getjTableStavke().setModel(modelStavke);
    }
    
    private void popuniFormuZaIzmenu(Racun racun) {
        drf.getjTextFieldID().setText(String.valueOf(racun.getIdRacun()));
        
        for (int i = 0; i < drf.getjComboBoxPacijenti().getItemCount(); i++) {
            Pacijent p = (Pacijent) drf.getjComboBoxPacijenti().getItemAt(i);
            if (p != null && p.getIdPacijent()== racun.getPacijent().getIdPacijent()) {
                drf.getjComboBoxPacijenti().setSelectedItem(p);
                break;
            }
        }
        
        
        
        try {
            List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavkeRacuna(racun);
            modelStavke = new ModelTabeleStavkaRacuna(stavke);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(drf, "Sistem ne može da učita stavke računa.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void dodajActionListenere() {
        drf.getjComboBoxUsluga().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drf.getjComboBoxUsluga().getSelectedItem() instanceof Usluga) {
                    Usluga odabranaUsluga = (Usluga) drf.getjComboBoxUsluga().getSelectedItem();
                    drf.getjTextFieldCenaStavke().setText(String.valueOf(odabranaUsluga.getCena()));
                }
            }
        });
        
        drf.getjButtonDodajStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }
        });
        
        drf.getjButtonObrisiStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku();
            }
        });
        
        drf.getjButtonKreirajRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajRacun();
            }
        });
        
        drf.getjButtonIzmeniRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniRacun();
            }
        });
        
        
    }

    private void dodajStavku() {
        if (drf.getjComboBoxUsluga().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(drf, "Morate izabrati uslugu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int kolicina = 0;
        try {
            kolicina = Integer.parseInt(drf.getjTextFieldKolicinaStavke().getText().trim());
            if (kolicina <= 0) {
                JOptionPane.showMessageDialog(drf, "Količina mora biti veća od nule.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(drf, "Količina mora biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usluga usluga = (Usluga) drf.getjComboBoxUsluga().getSelectedItem();
        
        boolean stavkaPostoji = false;
       
        for (StavkaRacuna stavka : modelStavke.getListaStavki()) {
            if (stavka.getUsluga().equals(usluga)) {
                
                stavka.setKolicina(stavka.getKolicina() + kolicina);
                stavka.setCena(stavka.getCena());
                stavka.setIznos(stavka.getIznos()+ (usluga.getCena() * kolicina));
                stavkaPostoji = true;
                break;
            }
        }
        
        if (!stavkaPostoji) {
           
            StavkaRacuna novaStavka = new StavkaRacuna();
            novaStavka.setUsluga(usluga);
            novaStavka.setKolicina(kolicina);
            novaStavka.setCena(usluga.getCena());
            novaStavka.setIznos(usluga.getCena() * kolicina);
            modelStavke.dodajStavku(novaStavka);
        }

        modelStavke.fireTableDataChanged();

        drf.getjComboBoxUsluga().setSelectedIndex(-1);
        drf.getjTextFieldCenaStavke().setText("");
        drf.getjTextFieldKolicinaStavke().setText("");
    }
    
    private void obrisiStavku() {
        int red = drf.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(drf, "Niste izabrali nijednu stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        modelStavke.obrisiStavku(red);
    }
    
    private void kreirajRacun() {
        try {
            Pacijent pacijent = (Pacijent) drf.getjComboBoxPacijenti().getSelectedItem();
            if(pacijent == null) {
                JOptionPane.showMessageDialog(drf, "Morate izabrati pacijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<StavkaRacuna> stavkeRacuna = modelStavke.getListaStavki();
            if(stavkeRacuna.isEmpty()){
                JOptionPane.showMessageDialog(drf, "Račun mora imati barem jednu stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            

            Racun noviRacun = new Racun();
            noviRacun.setPacijent(pacijent);
            noviRacun.setLekar(lekar);
            noviRacun.setStavke(stavkeRacuna);
            noviRacun.setDatumVreme(drf.getDatumVreme());


            double iznos = 0.0;
            for (StavkaRacuna stavka : stavkeRacuna) {
                iznos += stavka.getIznos();
            }
            noviRacun.setIznos(iznos);
            


            Komunikacija.getInstance().kreirajRacun(noviRacun);

            JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            prikaziPodatkeORacunuKreiranje(noviRacun);

            drf.dispose();
            Cordinator.getInstance().vratiSeNaGlavnuFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
    
    private void izmeniRacun() {
        try {
            
            
            Pacijent pacijent = (Pacijent) drf.getjComboBoxPacijenti().getSelectedItem();
            
            if (pacijent == null || lekar == null) {
                JOptionPane.showMessageDialog(drf, "Morate izabrati pacijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            this.racun.setPacijent(pacijent);
            this.racun.setLekar(lekar);
            
            
            
            List<StavkaRacuna> stavkeRacuna = modelStavke.getListaStavki();
            double iznos = 0.0;
            for (StavkaRacuna stavka : stavkeRacuna) {
                iznos += stavka.getIznos();
            }
            this.racun.setIznos(iznos);
            
            this.racun.setStavke(stavkeRacuna); 
            
            Komunikacija.getInstance().izmeniRacun(this.racun);
            JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            prikaziPodatkeORacunuIzmena(this.racun);
            drf.dispose();
            Cordinator.getInstance().vratiSeNaGlavnuFormu();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void popuniComboBoxeve() throws Exception {
        List<Pacijent> pacijenti = Komunikacija.getInstance().ucitajPacijente();
        List<Usluga> usluge = Komunikacija.getInstance().ucitajUsluge();
        drf.getjComboBoxPacijenti().removeAllItems();
        drf.getjComboBoxUsluga().removeAllItems();
        
        for (Usluga usluga : usluge) {
            drf.getjComboBoxUsluga().addItem(usluga);
        }
        drf.getjComboBoxUsluga().setSelectedIndex(-1);
        
        for (Pacijent p : pacijenti) {
            drf.getjComboBoxPacijenti().addItem(p);
        }
        drf.getjComboBoxPacijenti().setSelectedIndex(-1);
        

        
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private void prikaziPodatkeORacunuIzmena(Racun racun) {
        String poruka =
                "ID računa: " + racun.getIdRacun() + "\n"
                + "Ukupan iznos: " + racun.getIznos()+ "\n"
                + "Datum i vreme: " + racun.getDatumVreme().format(formatter) +"\n"
                + "Lekar: " + racun.getLekar().toString() + "\n"
                + "Pacijent: " + racun.getPacijent().toString();
        
        JOptionPane.showMessageDialog(drf, poruka, "Podaci o računu", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void prikaziPodatkeORacunuKreiranje(Racun racun) {
        String poruka =
                 "Ukupan iznos: " + racun.getIznos()+ "\n"
                + "Datum i vreme: " + racun.getDatumVreme().format(formatter) +"\n"
                + "Lekar: " + racun.getLekar().toString() + "\n"
                + "Pacijent: " + racun.getPacijent().toString();
        
        JOptionPane.showMessageDialog(drf, poruka, "Podaci o računu", JOptionPane.INFORMATION_MESSAGE);
    }
}