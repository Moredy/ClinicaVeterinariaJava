package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ConsutaEExames extends JFrame {
    private JPanel mainPanel;
    private JLabel clienteLabel;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private JList list1;
    private JTable table1;
    private JButton confirmarEncerramentoButton;
    private JButton novaConsultaButton;
    private JButton voltarButton;
    private JButton apagarExameButton;
    private JButton novoExameButton;
    private JTable table2;
    private JLabel nomeCliente;
    private JLabel nomeAnimal;
    private JLabel nomeEspecie;
    private JButton DATAButton;
    private JButton TERMINADOButton;
    private JTable table3;
    private JButton novoVeterinarioButton;
    private JButton apagarVeterinarioButton;
    private JTextField textField1;
    private JTable table4;


    private Cliente cliente;
    private Animal animal;
    private Consulta consulta;
    private Veterinario veterinario;
    private Tratamento tratamento;

    public ConsutaEExames(String title , Cliente cliente, Animal animal) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        this.setVisible(true);
        this.setSize(1000,1000); // Define o tamanho do frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sair da aplicação

        ImageIcon image = new ImageIcon("src/main/assets/logo.png"); // Cria o icone
        this.setIconImage(image.getImage()); // Troca o icone da imagem
        this.getContentPane().setBackground(new Color(123,50,250));

        this.cliente = cliente;
        this.animal = animal;

        createTable();
    }

    public static void main(String[] args) {


    }

    private void createTable () {


        Controller.setTableModel(table1, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(animal.getId())));

        Controller.setTableModel(table3, new VetTableModel(VeterinarioDAO.getInstance().retrieveAll()));

        Controller.setTableModel(table4, new TreatmentTableModel(TratamentoDAO.getInstance().retrieveByAnimalId(animal.getId())));

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

        textField1.addKeyListener(
                new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(table3.getModel() instanceof VetTableModel) {
                            ((GenericTableModel)table3.getModel()).addListOfItems(Controller.getVeterinarioNomeParecido(textField1.getText()));
                        }
                    }
                    public void keyPressed(KeyEvent e){

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                    Integer selectedRow = table1.getSelectedRow();

                    //Adiciona os valores das datas em consultas recentes
                try {
                    String dataStr = (String) table1.getModel().getValueAt(selectedRow, 0);
                    Integer horarioStr = (Integer) table1.getModel().getValueAt(selectedRow, 1);

                    java.util.List<Consulta> consultaSelec = ConsultaDAO.getInstance().retrieveByDateAndHour(dataStr, horarioStr.toString());

                    consulta = consultaSelec.get(0);



                    Controller.setTableModel(table2, new ExamTableModel(ExameDAO.getInstance().retrieveExamesByIdConsulta(consulta.getId())));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        });

        table4.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                Integer selectedRow = table4.getSelectedRow();

                //Adiciona os valores das datas em consultas recentes
                try {
                    Integer idTratamento = (Integer) table4.getModel().getValueAt(selectedRow, 0);

                    Tratamento tratamentoSelec = TratamentoDAO.getInstance().retrieveById(idTratamento);

                    tratamento = tratamentoSelec;


                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        });

        table3.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                Integer selectedRow = table3.getSelectedRow();

                //Adiciona os valores das datas em consultas recentes
                try {
                    String emailStr = (String) table3.getModel().getValueAt(selectedRow, 1);

                    java.util.List<Veterinario> veterinarioSelec = VeterinarioDAO.getInstance().retrieveBySimilarEmail(emailStr);

                    veterinario = veterinarioSelec.get(0);


                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        });


        nomeCliente.setText(cliente.getNome());
        nomeAnimal.setText(animal.getNome());

        Especie especie = EspecieDAO.getInstance().retrieveById(animal.getIdEspecie());
        if(especie != null) {
            nomeEspecie.setText(especie.getNome());
        }

        novaConsultaButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  ConsultaTableModel) {
                            if (veterinario != null && tratamento !=null)
                                ((GenericTableModel) table1.getModel()).addItem(Controller.adicionaConsulta("", -1, "", animal.getId(), veterinario.getId(), tratamento.getId()));
                        }
                    }
                }
        );

        novoVeterinarioButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table3.getModel() instanceof  VetTableModel) {
                                ((GenericTableModel) table3.getModel()).addItem(Controller.adicionarVeterionario("", "", ""));
                        }
                    }
                }
        );

        confirmarEncerramentoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  ConsultaTableModel) {
                            consulta.setTerminou(1);
                            ConsultaDAO.getInstance().update(consulta);


                            Controller.setTableModel(table1, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(animal.getId())));
                        }
                    }
                }
        );

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

        novoExameButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table2.getModel() instanceof  ExamTableModel) {
                            ((GenericTableModel) table2.getModel()).addItem(Controller.adicionarExame("",consulta.getId()));
                        }
                    }
                }
        );

        apagarExameButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table2.getModel() instanceof  ExamTableModel) {


                            if (!((String) table2.getModel().getValueAt(table2.getSelectedRow(), 0)).isEmpty()) {
                                Integer selectedRow = table2.getSelectedRow();

                                //System.out.println((String) table1.getModel().getValueAt(selectedRow, 3));

                                List<Exame> selectedExam = ExameDAO.getInstance().retrieveBySimilarName((String) table2.getModel().getValueAt(selectedRow, 0));
                                System.out.println(selectedRow);

                                ((GenericTableModel) table2.getModel()).removeItem(selectedRow);

                                ExameDAO.getInstance().delete(selectedExam.get(0));
                            }


                        }
                    }
                }
        );

        apagarVeterinarioButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table3.getModel() instanceof  VetTableModel) {


                            if (!((String) table3.getModel().getValueAt(table3.getSelectedRow(), 1)).isEmpty()) {
                                Integer selectedRow = table3.getSelectedRow();

                                //System.out.println((String) table1.getModel().getValueAt(selectedRow, 3));

                                List<Veterinario> selectVet = VeterinarioDAO.getInstance().retrieveBySimilarEmail((String) table3.getModel().getValueAt(selectedRow, 1));
                                System.out.println(selectedRow);

                                ((GenericTableModel) table3.getModel()).removeItem(selectedRow);

                                VeterinarioDAO.getInstance().delete(selectVet.get(0));
                            }


                        }
                    }
                }
        );

        DATAButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  ConsultaTableModel) {


                            Controller.setTableModel(table1, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalIdAndOrder(animal.getId())));


                        }
                    }
                }
        );

        TERMINADOButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  ConsultaTableModel) {


                            Controller.setTableModel(table1, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalIdAndOrderTerminado(animal.getId(), 1)));


                        }
                    }
                }
        );



    }
}
