/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.*;
import java.util.List;
import model.Pacijent;
import komunikacija.Komunikacija;
import forme.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Usluga;
import model.Klinika;

/**
 *
 * @author Filip
 */
public class PrikazPacijenataController {
    private final PrikazPacijenataForma ppaf;

    public PrikazPacijenataController(PrikazPacijenataForma ppaf) {
        this.ppaf = ppaf;
        ppaf.setLocationRelativeTo(null);
        ppaf.setTitle("Prikaz pacijenata");
        ppaf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppaf.setVisible(true);
        ucitajKlinike();
    }

    private void pripremiFormu() {
        List<Pacijent> pacijenti = Komunikacija.getInstance().ucitajPacijente();
        ModelTabelePacijenti mtpa = new ModelTabelePacijenti(pacijenti);
        ppaf.getjTablePrikazPacijenata().setModel(mtpa);
    }

    private void addActionListener() {
        ppaf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppaf.getjTablePrikazPacijenata().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ppaf, "Nije odabran nijedan pacijent za brisanje!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(ppaf, "Sistem je nasao pacijenta.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabelePacijenti mtpa = (ModelTabelePacijenti) ppaf.getjTablePrikazPacijenata().getModel();
                    Pacijent pa = mtpa.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiPacijenta(pa);
                        JOptionPane.showMessageDialog(ppaf, "Sistem je obrisao pacijenta.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                        ppaf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ppaf, "Sistem ne moze da obrise pacijenta.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        ppaf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppaf.getjTablePrikazPacijenata().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ppaf, "Nije odabran nijedan pacijent za izmenu!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(ppaf, "Sistem je nasao pacijenta.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabelePacijenti mtpa = (ModelTabelePacijenti) ppaf.getjTablePrikazPacijenata().getModel();
                    Pacijent pa = mtpa.getLista().get(red);
                    Cordinator.getInstance().dodajParam("pacijent", pa);
                    Cordinator.getInstance().otvoriIzmeniPacijentFormu();
                }
            }
        });
        
        ppaf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = ppaf.getjTextFieldPretragaIme().getText().trim();
                String prezime = ppaf.getjTextFieldPretragaPrezime().getText().trim();
                String jmbg = ppaf.getjTextFieldPretragaJMBG().getText().trim();
                String mail = ppaf.getjTextFieldPretragaMail().getText().trim();
                Klinika klinika = (Klinika) ppaf.getjComboBoxKlinika().getSelectedItem();
                
                ModelTabelePacijenti mtpa = (ModelTabelePacijenti) ppaf.getjTablePrikazPacijenata().getModel();
                mtpa.pretrazi(ime,prezime,mail,jmbg, klinika);
                if (mtpa.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(ppaf, "Sistem ne moze da nadje pacijente po zadatim kriterijumima.", "Greska!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ppaf, "Sistem je našao pacijente po zadatim kriterijumima.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        
        ppaf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ppaf.getjTextFieldPretragaIme().setText("");
                ppaf.getjTextFieldPretragaPrezime().setText("");
                ppaf.getjTextFieldPretragaMail().setText("");
                ppaf.getjTextFieldPretragaJMBG().setText("");
                ppaf.getjComboBoxKlinika().setSelectedIndex(-1);
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
    private void ucitajKlinike() {
    try {
        List<Klinika> sveKlinike = Komunikacija.getInstance().ucitajKlinike();
        ppaf.getjComboBoxKlinika().addItem(null); 
        for (Klinika k : sveKlinike) {
            ppaf.getjComboBoxKlinika().addItem(k);
        }
        ppaf.getjComboBoxKlinika().setSelectedIndex(-1);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(ppaf, "Sistem ne može da učita listu klinika.", "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
    
}
