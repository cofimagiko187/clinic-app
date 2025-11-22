/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazRacunaForma;
import forme.PrikazStavkiRacunaForma;
import forme.model.ModelTabeleRacuni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import komunikacija.Komunikacija;
import model.Pacijent;
import model.Lekar;
import model.Racun;

/**
 *
 * @author Filip
 */
public class PrikazRacunaController {

    private PrikazRacunaForma forma;
    private boolean isInitialLoad = true;

    public PrikazRacunaController(PrikazRacunaForma forma) {
        this.forma = forma;
        otvoriFormu();
        addActionListener();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        forma.setLocationRelativeTo(null);
        forma.setTitle("Prikaz racuna");
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        forma.setVisible(true);

    }

    private void pripremiFormu() {
        try {
            
            List<Racun> listaRacuna = Komunikacija.getInstance().ucitajListuRacuna();
            
            
            ModelTabeleRacuni model = new ModelTabeleRacuni(listaRacuna);
            forma.getjTableRacuni().setModel(model);
            
            TableRowSorter<ModelTabeleRacuni> sorter = new TableRowSorter<>(model);
            forma.getjTableRacuni().setRowSorter(sorter);
            
            popuniComboBoxevePretraga();

            if (isInitialLoad) {
                JOptionPane.showMessageDialog(forma, "Sistem je učitao listu računa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                isInitialLoad = false;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita listu računa. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addActionListener() {
        dodajActionListenerNaDugmePrikaziStavke();
        dodajActionListenerNaDugmePretraziRacun();
        dodajActionListenerNaDugmeResetuj();
        dodajActionListenerNaDugmeIzmeni();
        dodajActionListenerNaTabelu();
    }
    
        private void dodajActionListenerNaDugmePrikaziStavke() {
        forma.addBtnPrikaziSRActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getjTableRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Morate odabrati račun.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    int modelRow = forma.getjTableRacuni().convertRowIndexToModel(red);
                    ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
                    Racun odabraniRacun = model.getListaRacuna().get(modelRow);
                    Cordinator.getInstance().otvoriPrikazStavkiRFormu(odabraniRacun);
                }
            }
        });
    }
        
    private void dodajActionListenerNaDugmePretraziRacun() {
        forma.addBtnPretraziRacunActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziRacune();
            }
        });
    }

    private void dodajActionListenerNaDugmeResetuj() {
        forma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                forma.getjTextFieldID().setText("");
                forma.getjComboBoxPretragaLekar().setSelectedIndex(-1);
                forma.getjComboBoxPretragaPacijent().setSelectedIndex(-1);
                
                
               
                osveziTabelu();
            }
        });
    }
    
    private void dodajActionListenerNaDugmeIzmeni() {
        forma.addBtnIzmeniRacunActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniOdabraniRacun();
            }
        });
    }
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private void dodajActionListenerNaTabelu() {
        forma.getjTableRacuni().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = forma.getjTableRacuni().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (e.getClickCount() == 1) { 
                    try {
                        int modelRow = forma.getjTableRacuni().convertRowIndexToModel(red);
                        ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
                        Racun odabraniRacun = model.getListaRacuna().get(modelRow);
                        
                        
                        JOptionPane.showMessageDialog(forma, "Sistem je našao račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        String poruka = "ID računa: " + odabraniRacun.getIdRacun() + "\n"
                                      + "Ukupna iznos: " + odabraniRacun.getIznos()+ "\n"
                                      + "Datum i vreme: " + odabraniRacun.getDatumVreme().format(formatter) + "\n"
                                      + "Lekar: " + odabraniRacun.getLekar().toString() + "\n"
                                      + "Pacijent: " + odabraniRacun.getPacijent().toString();
                        
                        JOptionPane.showMessageDialog(forma, poruka, "Podaci o računu", JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void pretraziRacune() {
        int idRacuna = 0;
        if (!forma.getjTextFieldID().getText().isEmpty()) {
            try {
                idRacuna = Integer.parseInt(forma.getjTextFieldID().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(forma, "ID računa mora biti broj!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Lekar lekar = (Lekar) forma.getjComboBoxPretragaLekar().getSelectedItem();
        Pacijent pacijent = (Pacijent) forma.getjComboBoxPretragaPacijent().getSelectedItem();

        
        List<Racun> filtriraniRacuni = new ArrayList<>();
        

        try {
            
                
                Racun racunZaPretragu = new Racun(idRacuna, 0.0, null , lekar, pacijent);
                List<Racun> obradjeniRacuni = Komunikacija.getInstance().pretraziRacune(racunZaPretragu);
                filtriraniRacuni.addAll(obradjeniRacuni);

              

            
            ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
            model.setListaRacuna(filtriraniRacuni);
            model.fireTableDataChanged();

            
            if (filtriraniRacuni.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račune po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao račune po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račune po zadatim kriterijumima. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void izmeniOdabraniRacun() {
        int red = forma.getjTableRacuni().getSelectedRow();

        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati račun za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int modelRow = forma.getjTableRacuni().convertRowIndexToModel(red);
            ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
            Racun odabraniRacun = model.getListaRacuna().get(modelRow);

            Cordinator.getInstance().otvoriIzmeniRacunFormu(odabraniRacun);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita odabrani račun za izmenu. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void popuniComboBoxevePretraga() {
        try {
            List<Pacijent> pacijenti = Komunikacija.getInstance().ucitajPacijente();
            List<Lekar> lekari = Komunikacija.getInstance().ucitajLekare();

            forma.getjComboBoxPretragaPacijent().removeAllItems();
            forma.getjComboBoxPretragaPacijent().addItem(null);
            for (Pacijent pacijent : pacijenti) {
                forma.getjComboBoxPretragaPacijent().addItem(pacijent);
            }
            forma.getjComboBoxPretragaPacijent().setSelectedIndex(-1);

            forma.getjComboBoxPretragaLekar().removeAllItems();
            forma.getjComboBoxPretragaLekar().addItem(null);
            for (Lekar lekar : lekari) {
                forma.getjComboBoxPretragaLekar().addItem(lekar);
            }
            forma.getjComboBoxPretragaLekar().setSelectedIndex(-1);

           

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Greška prilikom popunjavanja polja za pretragu.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void osveziTabelu() {
        pripremiFormu();
    }
}
