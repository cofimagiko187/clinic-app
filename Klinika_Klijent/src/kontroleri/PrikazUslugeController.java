/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import model.Usluga;
import java.util.*;
import komunikacija.Komunikacija;
import forme.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import cordinator.Cordinator;
import javax.swing.JFrame;

/**
 *
 * @author Filip
 */
public class PrikazUslugeController {
    private final PrikazUslugeForma puf;

    public PrikazUslugeController(PrikazUslugeForma puf) {
        this.puf = puf;
        puf.setLocationRelativeTo(null);
        puf.setTitle("Prikaz usluga");
        puf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        puf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Usluga> usluge = Komunikacija.getInstance().ucitajUsluge();
        ModelTabeleUsluge mtu = new ModelTabeleUsluge(usluge);
        puf.getjTablePrikazUsluga().setModel(mtu);
    }

    private void addActionListener() {
        puf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = puf.getjTablePrikazUsluga().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(puf, "Nije odabrana nijedna usluga za brisanje!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ModelTabeleUsluge mtk = (ModelTabeleUsluge) puf.getjTablePrikazUsluga().getModel();
                    Usluga u = mtk.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obrisiUslugu(u);
                        JOptionPane.showMessageDialog(puf, "Sistem je obrisao uslugu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(puf, "Sistem ne moze da obrise uslugu.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        puf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = puf.getjTablePrikazUsluga().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(puf, "Nije odabrana nijedna usluga za izmenu!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ModelTabeleUsluge mtu = (ModelTabeleUsluge) puf.getjTablePrikazUsluga().getModel();
                    Usluga u = mtu.getLista().get(red);
                    Cordinator.getInstance().dodajParam("usluga", u);
                    Cordinator.getInstance().otvoriIzmeniUsluguFormu();
                }
            }
        });
        
        puf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = puf.getjTextFieldPretragaNaziv().getText().trim();
                String opis = puf.getjTextFieldPretragaOpis().getText().trim();
                
                ModelTabeleUsluge mtu = (ModelTabeleUsluge) puf.getjTablePrikazUsluga().getModel();
                mtu.pretrazi(naziv, opis);
            }
        });
        
        puf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puf.getjTextFieldPretragaNaziv().setText("");
                puf.getjTextFieldPretragaOpis().setText("");
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
}
