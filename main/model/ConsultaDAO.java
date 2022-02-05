package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    // CRUD
    public Consulta create(String data, int horario, String comentario, int id_animal, int id_vet, int id_tratamento) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, id_tratamento) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, data);
            stmt.setInt(2, horario);
            stmt.setString(3, comentario);
            stmt.setInt(4, id_animal);
            stmt.setInt(5, id_vet);
            stmt.setInt(6, id_tratamento);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta", "id"));
    }

    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty() {
        Consulta lastConsulta = this.retrieveById(lastId("consulta", "id"));
        if (lastConsulta != null) {
            return lastConsulta.getComentario().isBlank();
        }
        return false;
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            consulta = new Consulta(rs.getInt("id"), rs.getString("data"), rs.getInt("horario"), rs.getString("comentario"), rs.getInt("id_animal"), rs.getInt("id_vet"), rs.getInt("id_tratamento"), rs.getInt("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List <Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }

    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }

    // RetrieveLast
    public List retrieveLast() {
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta", "id"));
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List <Consulta> consulta = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consulta.isEmpty() ? null : consulta.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM consulta WHERE comentario LIKE '%" + nome + "%'");
    }

    public List retrieveByAnimalId(Integer idAnimal) {
        return this.retrieve("SELECT * FROM consulta WHERE id_animal = " + idAnimal);
    }

    public List retrieveByAnimalIdAndOrder(Integer idAnimal) {
        return this.retrieve( "SELECT * FROM consulta as table1 INNER JOIN (SELECT substr(data, 7, 4)||'-'||substr(data, 4,2)||'-'||substr(data, 1,2) as data1, id from consulta order by date(data1) asc) as table2 ON table1.id = table2.id WHERE id_animal = " + idAnimal);
    }

    public List retrieveByAnimalIdAndOrderTerminado(Integer idAnimal, Integer terminado) {
        return this.retrieve( "SELECT * FROM consulta as table1 INNER JOIN (SELECT substr(data, 7, 4)||'-'||substr(data, 4,2)||'-'||substr(data, 1,2) as data1, id from consulta order by date(data1) asc) as table2 ON table1.id = table2.id WHERE id_animal = " + idAnimal + " AND terminado = "+ terminado);
    }


    public List retrieveByDateAndHour(String date, String horario ) {
        return this.retrieve("SELECT * FROM consulta WHERE data LIKE '" + date + "' AND horario LIKE '" + horario + "'");
    }




    // Updade
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_vet=?, id_tratamento=?, terminado=?   WHERE id=?");
            stmt.setString(1, consulta.getData());
            stmt.setInt(2, consulta.getHora());
            stmt.setString(3, consulta.getComentario());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVet());
            stmt.setInt(6, consulta.getIdTratamento());
            stmt.setInt(7, consulta.isTerminou());
            stmt.setInt(8, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    // Delete
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}