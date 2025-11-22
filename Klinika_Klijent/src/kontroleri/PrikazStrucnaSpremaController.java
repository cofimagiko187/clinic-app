/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import forme.model.ModelTabeleStrucnaSprema;
import java.util.List;
import javax.swing.JFrame;
import model.StrucnaSprema;
import komunikacija.Komunikacija;

/**
 *
 * @author Filip
 */
public class PrikazStrucnaSpremaController {
    private final PrikazStrucnaSpremaForma psf;

    public PrikazStrucnaSpremaController(PrikazStrucnaSpremaForma psf) {
        this.psf = psf;
        psf.setLocationRelativeTo(null);
        psf.setTitle("Prikaz strucna sprema");
        psf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }
    
    public void otvoriFormu() {
        pripremiFormu();
        psf.setVisible(true);
    }
    
    private void pripremiFormu() {
        List<StrucnaSprema> strucneSpreme = Komunikacija.getInstance().ucitajStrucneSpreme();
        ModelTabeleStrucnaSprema mts = new ModelTabeleStrucnaSprema(strucneSpreme);
        psf.getjTableStrucneSpreme().setModel(mts);
    }

    private void addActionListener() {
    }

}
