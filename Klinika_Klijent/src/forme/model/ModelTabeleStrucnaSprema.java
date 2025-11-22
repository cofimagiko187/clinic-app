/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StrucnaSprema;

/**
 *
 * @author Filip
 */
public class ModelTabeleStrucnaSprema extends AbstractTableModel {
    List<StrucnaSprema> lista;
    String[] kolone = {"Naziv", "Opis", "Trajanje obrazovanja"};

    public ModelTabeleStrucnaSprema(List<StrucnaSprema> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
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
            StrucnaSprema strucnaSprema = lista.get(rowIndex);
    
            switch (columnIndex) {
                case 0:
                    return strucnaSprema.getNaziv();
                case 1:
                    return strucnaSprema.getOpis();
                case 2:
                    return strucnaSprema.getTrajanjeObrazovanja();
                default:
                    return null;
            }
    }
    
}
