package model;


public class Consulta {
    int id;
    private String data;
    private int hora;
    private String comentarios;
    private int idAnimal;
    private int idVet;
    private int idTratamento;
    private int terminou;  

    public Consulta(int id,String data, int hora, String comentarios, int idAnimal, int idVet, int idTratamento, int terminou) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.comentarios = comentarios;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
        this.idTratamento = idTratamento;
        this.terminou = terminou;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getComentario() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int isTerminou() {
        return terminou;
    }

    public void setTerminou(int terminou) {
        this.terminou = terminou;
    }
    @Override
        public String toString() {
        return "Consulta{" + "comentarios=" + comentarios + '}';
    }
}
