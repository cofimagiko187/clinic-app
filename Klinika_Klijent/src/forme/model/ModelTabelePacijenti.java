/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Pacijent;
import model.Klinika;

/**
 *
 * @author Filip
 */
public class ModelTabelePacijenti extends AbstractTableModel {
    
    List<Pacijent> lista;
    String[] kolone = {"Id", "Ime", "Prezime", "JMBG", "Mail", "Klinika"};

    public ModelTabelePacijenti(List<Pacijent> lista) {
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
        Pacijent p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getIdPacijent();
            case 1: return p.getIme();
            case 2: return p.getPrezime();
            case 3: return p.getJmbg();
            case 4: return p.getMail();
            case 5: return p.getKlinika().getAdresa();
            default: return "N/A";
        }
    }

    public List<Pacijent> getLista() {
        return lista;
    }

public void pretrazi(String ime, String prezime, String mail, String jmbg, Klinika klinika) {
    List<Pacijent> filteredList = lista.stream()
           
            .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
            
            .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            
            .filter(p -> (mail == null || mail.isEmpty() || p.getMail().toLowerCase().contains(mail.toLowerCase())))
            
            .filter(p -> (jmbg == null || jmbg.isEmpty() || p.getJmbg().toLowerCase().contains(jmbg.toLowerCase())))
            
            .filter(p -> (klinika == null || p.getKlinika().getIdKlinika() == klinika.getIdKlinika()))
            .collect(Collectors.toList());
    this.lista = filteredList;
    System.out.println(filteredList);
    fireTableDataChanged();
}
    
}
