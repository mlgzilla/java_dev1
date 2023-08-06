package gui;

import db.Dao;
import db.TransportMachine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InputWindow extends JFrame {
    private JPanel panelMain;
    private JFormattedTextField idTextField;
    private JTextField makeTextField;
    private JTextField modelTextField;
    private JTextField categoryTextField;
    private JTextField regNumbTextField;
    private JLabel id_label;
    private JLabel make_label;
    private JFormattedTextField yearTextField;
    private JLabel model_label;
    private JComboBox mTypeComboBox;
    private JCheckBox hasTrailerCheckBox;
    private JButton closeButton;
    private JButton saveButton;
    private JRadioButton updateRadioButton;
    private JRadioButton createRadioButton;

    public InputWindow() {
        this.setContentPane(this.panelMain);
        this.setTitle("Input");
        this.setSize(750, 550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mTypeComboBox.setModel(new DefaultComboBoxModel(new String[]{"Passenger transport", "Cargo transport", "Heavy cargo transport"}));
        createRadioButton.setSelected(true);
        idTextField.setEnabled(false);
        idTextField.setText("0");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(idTextField.getText(), "") || Objects.equals(makeTextField.getText(), "") ||
                        Objects.equals(modelTextField.getText(), "") || Objects.equals(categoryTextField.getText(), "") ||
                        Objects.equals(regNumbTextField.getText(), "") || Objects.equals(yearTextField.getText(), "")){
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                    return;
                }
                Integer tempId = null, tempYear = null;
                try {
                    tempId = Integer.parseInt(idTextField.getText());
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Wrong input for id field");
                    return;
                }

                try {
                    tempYear = Integer.parseInt(yearTextField.getText());
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Wrong input for year field");
                    return;
                }

                TransportMachine transportMachine = new TransportMachine(
                        tempId,
                        makeTextField.getText(),
                        modelTextField.getText(),
                        categoryTextField.getText(),
                        regNumbTextField.getText(),
                        mTypeComboBox.getSelectedItem().toString(),
                        tempYear,
                        hasTrailerCheckBox.isSelected()
                );

                if (createRadioButton.isSelected())
                    Dao.create(transportMachine);
                else
                    Dao.update(transportMachine);

                dispose();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        createRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRadioButton.setSelected(false);
                idTextField.setEnabled(false);
                idTextField.setText("0");
            }
        });
        updateRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRadioButton.setSelected(false);
                idTextField.setEnabled(true);
                idTextField.setText("");
            }
        });
    }

    public static void main(String[] args){
        new InputWindow();
    }

}
