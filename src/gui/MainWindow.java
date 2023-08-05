package gui;

import db.Dao;
import db.TransportMachine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainWindow extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JScrollPane scrollPane;
    private JButton createButton;
    private JButton inputButton;
    private JTextField makeTextField;
    private JButton findButton;
    private JTextField modelTextField;
    private JButton refreshButton;
    private Vector<TransportMachine> transportMachines;

    public void refreshTable(String query){
        transportMachines = Dao.get(query);
        DefaultTableModel tModel = (DefaultTableModel) table1.getModel();
        while (tModel.getRowCount()!=0)
            tModel.removeRow(0);
        for (int i=0; i<transportMachines.toArray().length; i++)
            tModel.addRow(transportMachines.get(i).toVector());
    }

    MainWindow(){
        table1.setModel(new DefaultTableModel(
                null,
                new String[]{
                        "id", "Make", "Model", "Category", "Registration number", "Machine type", "Year", "Trailer"
                }
        ));
        this.setTitle("Transport machines");
        this.setSize(750,550);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InputWindow();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable("select * from transport_machines");
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String makeText = makeTextField.getText();
                String modelText = modelTextField.getText();
                String query = "select * from transport_machines where ";

                if (makeText.isEmpty() && modelText.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Fill in any or both search fields!");
                    return;
                }
                if (!makeText.isEmpty() && !modelText.isEmpty())
                    query += "make='" + makeText + "', model='" + modelText + "';";
                else
                    if (!makeText.isEmpty())
                        query += "make='" + makeText + "';";
                    else
                        query += "model='" + modelText + "';";

                refreshTable(query);
            }
        });
    }

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow();
        mainWindow.setContentPane(mainWindow.panel1);
        mainWindow.setTitle("Table");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
