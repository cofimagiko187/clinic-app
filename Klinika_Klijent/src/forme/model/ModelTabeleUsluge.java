/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import javax.swing.table.AbstractTableModel;
import model.Usluga;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Filip
 */
public class ModelTabeleUsluge extends AbstractTableModel {
    
    List<Usluga> lista;
    String[] kolone = {"Id", "Naziv", "Cena", "Opis"};

    public ModelTabeleUsluge(List<Usluga> lista) {
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
        Usluga u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return u.getIdUsluga();
            case 1: return u.getNaziv();
            case 2: return u.getCena();
            case 3: return u.getOpis();
            default: return "N/A";
        }
    }

    public List<Usluga> getLista() {
        return lista;
    }

    public void pretrazi(String naziv, String opis) {
        List<Usluga> filteredList = lista.stream()
                .filter(k -> (naziv == null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(k -> (opis == null || opis.isEmpty() || k.getOpis().toLowerCase().contains(opis.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
}
