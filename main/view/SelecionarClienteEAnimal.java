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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import view.AgendarConsulta;

public class SelecionarClienteEAnimal extends JFrame {
    private JPanel mainPanel;
    private JLabel clienteLabel;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private JList list1;
    private JTextField textField1;
    private JButton todosButton;
    private JButton novoClienteButton;
    private JButton apagarClienteButton;
    private JTable table1;
    private JButton novoAnimalButton;
    private JButton apagarAnimalButton;
    private JTable table2;
    private JLabel nomeCliente;
    private JLabel nomeAnimal;
    private JLabel nomEspecie;
    private JButton acessarAnimal;
    private JButton acessarTratamentosButton;
    private Cliente cliente ;
    private Animal animal;

    public SelecionarClienteEAnimal(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //this.setVisible(true);
        this.setSize(1000,1000); // Define o tamanho do frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sair da aplicação

        ImageIcon image = new ImageIcon("src/main/assets/logo.png"); // Cria o icone
        this.setIconImage(image.getImage()); // Troca o icone da imagem
        this.getContentPane().setBackground(new Color(123,50,250));

        createTable();
    }

    public static void main(String[] args) {
        JFrame frame = new SelecionarClienteEAnimal("Clinica veterinária");
        frame.setVisible(true);
        frame.setSize(1000,1000); // Define o tamanho do frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sair da aplicação

        ImageIcon image = new ImageIcon("src/main/assets/logo.png"); // Cria o icone
        frame.setIconImage(image.getImage()); // Troca o icone da imagem
        frame.getContentPane().setBackground(new Color(123,50,250));




    }

    private void createTable () {

        //Carrega os dados iniciais da tabela de clientes
        Controller.setTableModel(table1, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));



        //Busca na tabela de clientes
        textField1.addKeyListener(
                new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                    if(table1.getModel() instanceof ClienteTableModel) {
                        ((GenericTableModel)table1.getModel()).addListOfItems(Controller.getClienteNomeParecido(textField1.getText()));
                    }
                    }
                    public void keyPressed(KeyEvent e){

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        todosButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((GenericTableModel)table1.getModel()).addListOfItems(ClienteDAO.getInstance().retrieveAll());
                        textField1.setText("");
                    }
                }

        );

        novoClienteButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  ClienteTableModel) {
                            ((GenericTableModel) table1.getModel()).addItem(Controller.adicionaCliente("", "", "", "", ""));
                        }
                    }
                }
        );

        apagarClienteButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table1.getModel() instanceof  ClienteTableModel) {


                            if (!((String) table1.getModel().getValueAt(table1.getSelectedRow(), 3)).isEmpty()) {
                                Integer selectedRow = table1.getSelectedRow();

                                //System.out.println((String) table1.getModel().getValueAt(selectedRow, 3));

                                List<Cliente> selectedClient = ClienteDAO.getInstance().retrieveBySimilarEmail((String) table1.getModel().getValueAt(selectedRow, 3));
                                System.out.println(selectedRow);

                                ((GenericTableModel) table1.getModel()).removeItem(selectedRow);

                                ClienteDAO.getInstance().delete(selectedClient.get(0));
                            }


                        }
                    }
                }
        );

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

                try {

                    String nomeClienteStr = (String) table1.getModel().getValueAt(table1.getSelectedRow(), 0);
                    String emailClienteStr = (String) table1.getModel().getValueAt(table1.getSelectedRow(), 3);

                    nomeCliente.setText(nomeClienteStr);

                    nomeAnimal.setText("Nenhum animal selecionado");
                    nomEspecie.setText("Nenhum animal selecionado");


                    if (!emailClienteStr.isEmpty()) {
                        Integer selectedRow = table1.getSelectedRow();

                        List<Cliente> selectedClient = ClienteDAO.getInstance().retrieveBySimilarEmail(emailClienteStr);


                        //Carrega os dados iniciais da tabela de animais

                        Integer idCliente = selectedClient.get(0).getId();

                        cliente = selectedClient.get(0);

                        System.out.println(idCliente);

                        if (idCliente != -1) {
                            Controller.setTableModel(table2, new AnimalTableModel(AnimalDAO.getInstance().retrieveByIdCliente(idCliente)));
                        }

                    }
                } catch(Exception e) {

                    Controller.setTableModel(table2, new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
                    System.out.println("Cliente não encontrado na seleção: " + e.getMessage());
                }




            }
        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

                    try {

                        String nomeAnimalStr = (String) table2.getModel().getValueAt(table2.getSelectedRow(), 0);
                        String especieAnimalStr = (String) table2.getModel().getValueAt(table2.getSelectedRow(), 3);
                        nomeAnimal.setText(nomeAnimalStr);
                        nomEspecie.setText(especieAnimalStr);
                    } catch (Exception e) {
                        System.out.println("Animal não encontrado na seleção: " + e.getMessage());
                    }


                    try {
                        Integer selectedRow = table2.getSelectedRow();

                        //Adiciona os valores das datas em consultas recentes
                        String nomeAnimalStr = (String) table2.getModel().getValueAt(selectedRow, 0);
                        List<Animal> animalSelec = AnimalDAO.getInstance().retrieveBySimilarNameAndClienteId(nomeAnimalStr, cliente.getId());

                        animal = animalSelec.get(0);

                        if (animalSelec != null) {
                            List<Consulta> Consultas = ConsultaDAO.getInstance().retrieveByAnimalId(animalSelec.get(0).getId());
                            Object[] data = new Object[]{
                                    "Nenhuma consulta encontrada.", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
                            for (int i = 0; i < Consultas.size(); i++) {
                                data[i] = Consultas.get(i).getData();
                                System.out.println("Aqui");
                            }
                            list1.setListData(data);
                        }
                    }catch(Exception e) {
                        Object[] data = new Object[]{
                                "Nenhuma consulta encontrada.", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

                        list1.setListData(data);
                        System.out.println("Animal não encontrado: " + e.getMessage());
                    }




            }
        });


        apagarAnimalButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table2.getModel() instanceof  AnimalTableModel) {


                            if (!((String) table2.getModel().getValueAt(table2.getSelectedRow(), 0)).isEmpty()) {
                                Integer selectedRow = table2.getSelectedRow();

                                //System.out.println((String) table1.getModel().getValueAt(selectedRow, 3));

                                List<Animal> selectedAnimal = AnimalDAO.getInstance().retrieveBySimilarName((String) table2.getModel().getValueAt(selectedRow, 0));
                                System.out.println(selectedRow);

                                ((GenericTableModel) table2.getModel()).removeItem(selectedRow);

                                AnimalDAO.getInstance().delete(selectedAnimal.get(0));
                            }


                        }
                    }
                }
        );

        novoAnimalButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table2.getModel() instanceof  AnimalTableModel) {

                            ((GenericTableModel) table2.getModel()).addItem(Controller.adicionaAnimal("", "", "", 1, cliente.getId()));
                        }
                    }
                }
        );


        acessarAnimal.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Aqui");

                        if (cliente != null && animal !=null) {
                            //AgendarConsulta newFrame = new AgendarConsulta("Agendar consulta", cliente, animal);


                            ConsutaEExames newFrame = new ConsutaEExames("Agendar consulta", cliente, animal);
                            setVisible(false);
                            newFrame.setVisible(true);
                        }
                    }
                }

        );

        acessarTratamentosButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Aqui");

                        if (cliente != null && animal !=null) {
                            //AgendarConsulta newFrame = new AgendarConsulta("Agendar consulta", cliente, animal);


                            Treatment newFrame = new Treatment("Tramento", cliente, animal);
                            setVisible(false);
                            newFrame.setVisible(true);
                        }
                    }
                }

        );




    }

}
