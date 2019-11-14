package br.com.faasachat.client.view.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.faasachat.client.controller.observer.ContactsListObserver;
import br.com.faasachat.client.controller.strategy.AddContactStrategy;
import br.com.faasachat.client.controller.strategy.RemoveContactStrategy;
import br.com.faasachat.client.view.ContactsTableModel;
import br.com.faasachat.core.Application;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ContactsFrame extends JInternalFrame implements ContactsListObserver {

    private AddContactStrategy addContactStrategy;
    private RemoveContactStrategy removeContactStrategy;

    private ContactsTableModel tableModel;
    private JTable table;
    private JButton btnAddContact;
    private JButton btnRemoveContact;

    public ContactsFrame(ContactsTableModel tableModel, AddContactStrategy addContactStrategy, RemoveContactStrategy removeContactStrategy) {
        this.tableModel = tableModel;
        this.addContactStrategy = addContactStrategy;
        this.removeContactStrategy = removeContactStrategy;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        btnAddContact = new JButton("Add Contact");
        btnAddContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nickname = JOptionPane.showInputDialog(null, "Enter with new contact nickname: ");
                    addContactStrategy.execute(nickname);
                } catch(Exception ex) {
                    Application.getInstance().getExceptionCatcher().catchException(ex);
                }
            }
        });
        
        btnRemoveContact = new JButton("Remove Contact");
        btnRemoveContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >= 0) {
                    int result = JOptionPane.showConfirmDialog(null, "Do you really wants remove selected contact?");
                    if(result == JOptionPane.YES_OPTION) {
                        try {
                            removeContactStrategy.execute(selectedRow);
                        } catch(Exception ex) {
                            Application.getInstance().getExceptionCatcher().catchException(ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a contact to remove.");
                }
            }
        });
    }

    private void addComponents() {
        setLayout(new FlowLayout());
        add(btnAddContact);
        add(btnRemoveContact);
        add(table);
    }
    
    @Override
    public void updateContactsList() {
        tableModel.update();
    }
    
}
