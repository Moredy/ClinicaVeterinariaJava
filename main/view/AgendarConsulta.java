package view;

import controller.Controller;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AgendarConsulta extends JFrame {
    private JPanel mainPanel;
    private JLabel clienteLabel;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private JPanel box4;
    private JList list1;
    private JTextField textField1;
    private JButton todosButton;
    private JButton novoVetButton;
    private JButton apagarVetButton;
    private JTable table1;
    private JPanel box5;
    private JTextField textField2;
    private JCheckBox iniciarNovoTratamentoCheckBox;
    private JPanel box6;
    private JList list2;
    private JTextArea textArea1;
    private JButton agendarButton;
    private JLabel nomeCliente;
    private JLabel nomeAnimal;
    private JLabel nomeEspecie;
    private Cliente cliente;
    private Animal animal;

    public AgendarConsulta(String title ,Cliente cliente, Animal animal) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        this.setSize(1000,1000); // Define o tamanho do frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sair da aplicação

        ImageIcon image = new ImageIcon("src/main/assets/logo.png"); // Cria o icone
        this.setIconImage(image.getImage()); // Troca o icone da imagem
        this.getContentPane().setBackground(new Color(123,50,250));

        this.cliente = cliente;
        this.animal = animal;

        createTable();
    }



    private void createTable () {
        Controller.setTableModel(table1, new VetTableModel(VeterinarioDAO.getInstance().retrieveAll()));

        //Busca na tabela de veterinarios
        textField1.addKeyListener(
                new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(table1.getModel() instanceof VetTableModel) {
                            ((GenericTableModel)table1.getModel()).addListOfItems(Controller.getVeterinarioNomeParecido(textField1.getText()));
                        }
                    }
                    public void keyPressed(KeyEvent e){

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );


        nomeCliente.setText(cliente.getNome());
        nomeAnimal.setText(animal.getNome());

        Especie especie = EspecieDAO.getInstance().retrieveById(animal.getIdEspecie());
        if(especie != null) {
            nomeEspecie.setText(especie.getNome());
        }



    }
}
