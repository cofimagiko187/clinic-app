/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Racun;

/**
 *
 * @author Filip
 */
public class ModelTabeleRacuni extends AbstractTableModel {
    private List<Racun> listaRacuna;
    private String[] kolone = {"ID Računa", "Ukupan iznos", "Datum i vreme", "Lekar", "Pacijent"};

    public ModelTabeleRacuni(List<Racun> listaRacuna) {
        this.listaRacuna = listaRacuna;
    }

    @Override
    public int getRowCount() {
        if (listaRacuna == null) {
            return 0;
        }
        return listaRacuna.size();
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
        Racun racun = listaRacuna.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return racun.getIdRacun();
            case 1:
                return racun.getIznos();
            case 2:
                return racun.getDatumVreme().toString();
            case 3:
                return racun.getLekar().getIme()+" "+racun.getLekar().getPrezime();
            case 4:
                return racun.getPacijent().getIme()+" "+racun.getPacijent().getPrezime();
            default:
                return null;
        }
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

    public void setListaRacuna(List<Racun> listaRacuna) {
        this.listaRacuna = listaRacuna;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
    /**
     
     */
        switch (columnIndex) {
            case 0: 
                return Long.class;
            case 1: 
                return Double.class;
            case 2: 
                return LocalDateTime.class;
            case 3: 
                return String.class; 
            default:
                return Object.class;
        }
    }
    
}