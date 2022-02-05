package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TratamentoDAO getInstance() {
        return (instance == null ? (instance = new TratamentoDAO()) : instance);
    }

    // CRUD
    public Tratamento create(int id_animal, String nome, String dataIni, String dataFim, int terminado) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO tratamento (id_animal, nome, dataIni, dataFim, terminado) VALUES (?,?,?,?,?)");
            stmt.setInt(1, id_animal);
            stmt.setString(2, nome);
            stmt.setString(3, dataIni);
            stmt.setString(4, dataFim);
            stmt.setInt(5, terminado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento", "id"));
    }

    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty() {
        Tratamento lastTratamento = this.retrieveById(lastId("tratamento", "id"));
        if (lastTratamento != null) {
            return lastTratamento.getNome().isBlank();
        }
        return false;
    }

    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        try {
            tratamento = new Tratamento(rs.getInt("id"), rs.getString("nome"), rs.getString("dataIni"), rs.getString("dataFim"), rs.getInt("id_animal"), rs.getInt("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List <Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }

    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM tratamento");
    }

    // RetrieveLast
    public List retrieveLast() {
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("tratamento", "id"));
    }

    public List retrieveByAnimalId(Integer idAnimal) {
        return this.retrieve("SELECT * FROM tratamento WHERE id_animal = " + idAnimal);
    }

    public List retrieveByAnimalIdInitEnd(Integer idAnimal, String init, String end) {
        return this.retrieve("SELECT * FROM tratamento WHERE id_animal = " + idAnimal + " AND dataIni LIKE '" + init + "' AND dataFim LIKE '" + end + "'");
    }


    // RetrieveById
    public Tratamento retrieveById(int id) {
        List <Tratamento> tratamentos = this.retrieve("SELECT * FROM tratamento WHERE id = " + id);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }

    // Updade
    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("UPDATE tratamento SET id_animal=?, nome=?, dataIni=?, dataFim=?, terminado=? WHERE id=?");
            stmt.setInt(1, tratamento.getIdAnimal());
            stmt.setString(2, tratamento.getNome());
            stmt.setString(3, tratamento.getDtInicio());
            stmt.setString(4, tratamento.getDtFim());
            stmt.setInt(5, tratamento.getTerminou());
            stmt.setInt(6, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    // Delete
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = getConnection().prepareStatement("DELETE FROM tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}