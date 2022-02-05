package controller;

import model.*;
import view.GenericTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Controller {

    public static void setTableModel(JTable table , GenericTableModel tableModel) {
        table.setModel(tableModel);
    }


    public static List<Object> getClienteNomeParecido(String nome) {
        return ClienteDAO.getInstance().retrieveBySimilarName(nome);
    }

    public static List<Object> getVeterinarioNomeParecido(String nome) {
        return VeterinarioDAO.getInstance().retrieveBySimilarName(nome);
    }

    public static Cliente adicionaCliente(String nome , String end, String cep , String email, String telefone) {
        return ClienteDAO.getInstance().create(nome,end,cep,email,telefone);
    }

    public static Veterinario adicionarVeterionario(String nome , String email, String telefone) {
        return VeterinarioDAO.getInstance().create(nome,email,telefone);
    }

    public static Consulta adicionaConsulta(String data , Integer horario, String comentario , Integer idAnimal, Integer idVet, Integer idTratamento) {
        return ConsultaDAO.getInstance().create(data,horario,comentario,idAnimal,idVet,idTratamento);
    }

    public static Tratamento adicionarTratamento(Integer idAnimal , String nome, String dataInit , String dataFim, Integer terminado) {
        return TratamentoDAO.getInstance().create(idAnimal, nome, dataInit,dataFim,terminado);
    }

    public static Exame adicionarExame(String nome , Integer idConsulta) {
        return ExameDAO.getInstance().create(nome,idConsulta);
    }

    public static Animal adicionaAnimal(String nome , String anoNasc, String sexo , Integer idEsp , Integer idCli) {
        return AnimalDAO.getInstance().create(nome,anoNasc,sexo,idEsp,idCli);
    }

}
