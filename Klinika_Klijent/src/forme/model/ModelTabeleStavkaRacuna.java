
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaRacuna;

/**
 *
 * @author Filip
 */
public class ModelTabeleStavkaRacuna extends AbstractTableModel {
    private List<StavkaRacuna> listaStavki;
    private String[] kolone = {"R.B.", "Cena", "Iznos", "Količina", "Usluga"};

    public ModelTabeleStavkaRacuna(List<StavkaRacuna> listaStavki) {
        this.listaStavki = listaStavki;
    }
    
    public StavkaRacuna getStavka(int rowIndex) {
        return listaStavki.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
        if (listaStavki == null) {
            return 0;
        }
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna stavka = listaStavki.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getCena();
            case 2:
                return stavka.getIznos();
            case 3:
                return stavka.getKolicina();
            case 4:
                return stavka.getUsluga().getNaziv();
            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna stavka) {
        int trenutniRb = listaStavki.size() + 1;
        stavka.setRb(trenutniRb);
        listaStavki.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        listaStavki.remove(red);
        fireTableDataChanged();
    }

    public List<StavkaRacuna> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaRacuna> listaStavki) {
        this.listaStavki = listaStavki;
    }
    
}
