package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Treatment extends JFrame {
    private JPanel mainPanel;
    private JLabel clienteLabel;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private JList list1;
    private JTable table1;
    private JButton novoTratamentoButton;
    private JButton encerrarTratamentoButton;
    private JButton voltarButton;
    private JLabel nomeCliente;
    private JLabel nomeAnimal;
    private JLabel nomeEspecie;
    private JTable table2;
    private Cliente cliente;
    private Animal animal;
    private Consulta consulta;
    private Tratamento tratamento;
    public Treatment(String title,Cliente cliente, Animal animal) {
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

        System.out.println(animal.getId());

        Controller.setTableModel(table1, new TreatmentTableModel(TratamentoDAO.getInstance().retrieveByAnimalId(animal.getId())));

        nomeCliente.setText(cliente.getNome());
        nomeAnimal.setText(animal.getNome());

        Especie especie = EspecieDAO.getInstance().retrieveById(animal.getIdEspecie());
        if(especie != null) {
            nomeEspecie.setText(especie.getNome());
        }


        //Recupera as consultas recentes
        try {
            List<Consulta> Consultas = ConsultaDAO.getInstance().retrieveByAnimalId(animal.getId());
            Object[] data = new Object[]{
                    "Nenhuma consulta encontrada.", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
            for (int i = 0; i < Consultas.size(); i++) {
                data[i] = Consultas.get(i).getData();
                System.out.println("Aqui");
            }
            list1.setListData(data);

        }catch(Exception e) {
            Object[] data = new Object[]{
                    "Nenhuma consulta encontrada.", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

            list1.setListData(data);
            System.out.println("Animal não encontrado: " + e.getMessage());
        }

        voltarButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        SelecionarClienteEAnimal newFrame = new SelecionarClienteEAnimal("Cliente e animal");
                        setVisible(false);
                        newFrame.setVisible(true);
                    }
                }
        );

        novoTratamentoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  TreatmentTableModel) {
                            ((GenericTableModel) table1.getModel()).addItem(Controller.adicionarTratamento(animal.getId(),animal.getNome(),"","",0));
                        }
                    }
                }
        );

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                Integer selectedRow = table1.getSelectedRow();

                //Adiciona os valores das datas em consultas recentes
                try {
                    String dataInitStr = (String) table1.getModel().getValueAt(selectedRow, 2);
                    String dataFimStr = (String) table1.getModel().getValueAt(selectedRow, 3);

                    java.util.List<Tratamento> tratamentoSelec = TratamentoDAO.getInstance().retrieveByAnimalIdInitEnd(animal.getId(), dataInitStr, dataFimStr);

                    tratamento = tratamentoSelec.get(0);



                    //Controller.setTableModel(table2, new TreatmentTableModel(TratamentoDAO.getInstance().retrieveByAnimalId(animal.getId())));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        });


        encerrarTratamentoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  TreatmentTableModel) {
                            tratamento.setTerminou(1);
                            tratamento.setIdAnimal(animal.getId());
                            TratamentoDAO.getInstance().update(tratamento);
                            System.out.println(tratamento);

                            Controller.setTableModel(table1, new TreatmentTableModel(TratamentoDAO.getInstance().retrieveByAnimalId(animal.getId())));
                        }
                    }
                }
        );


    }
}
