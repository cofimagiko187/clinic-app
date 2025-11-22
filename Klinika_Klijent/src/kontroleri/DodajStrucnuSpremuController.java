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
import model.StrucnaSprema;
import komunikacija.Komunikacija;

/**
 *
 * @author Filip
 */
public class DodajStrucnuSpremuController {
    
    private DodajStrucnuSpremuForma dsf;

    public DodajStrucnuSpremuController(DodajStrucnuSpremuForma dsf) {
        this.dsf = dsf;
        pripremiFormu();

        dsf.setTitle("Ubacivanje strucne spreme");
        dsf.setLocationRelativeTo(null);
        dsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addActionListener();
    }

    private void pripremiFormu() {
    }
    
    private void addActionListener() {
        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   
                    String nazivStrucneSpreme = dsf.getjTextFieldNazivStrucneSpreme().getText().trim();
                    String opis = dsf.getjTextFieldOpis().getText().trim();
                    String trajanjeObrazovanja = dsf.getjTextFieldTrajanje().getText().trim();
                    
                   
                    if (nazivStrucneSpreme.isEmpty() || nazivStrucneSpreme.isEmpty() || trajanjeObrazovanja.isEmpty()) {
                        throw new Exception("Morate uneti sve podatke o strucnoj spremi.");
                    }
                    
                    
                    
                    
                    
                    int trajanjeObraz = Integer.valueOf(dsf.getjTextFieldTrajanje().getText().trim());
                    
                    StrucnaSprema novaStrucnaSprema = new StrucnaSprema(-1, nazivStrucneSpreme, opis, trajanjeObraz);
                    
                    
                    Komunikacija.getInstance().dodajStrucnaSprema(novaStrucnaSprema);

                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio strucnu spremu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    
                    dsf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti strucnu spremu.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        dsf.setVisible(true);
    }
}
