package br.com.faasachat.client.view;

import javax.swing.table.AbstractTableModel;

import br.com.faasachat.client.controller.ClientControllerInterface;

@SuppressWarnings("serial")
public class ContactsTableModel extends AbstractTableModel {
    
    private ClientControllerInterface controller;

    private final String NICKNAME_TITLE = "Nickname";
    private final String ACTIVITY_TITLE = "Activity";

    private final String[] columns = { NICKNAME_TITLE, ACTIVITY_TITLE };
    
    public ContactsTableModel(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void update() {
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return controller.getContactsQuantity();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if(columns[columnIndex] == NICKNAME_TITLE) {
            value = controller.getContactNickname(rowIndex);
        } else if(columns[columnIndex] == ACTIVITY_TITLE) {
            value = controller.getContactActivity(rowIndex);
        }
        return value;
    }

}
