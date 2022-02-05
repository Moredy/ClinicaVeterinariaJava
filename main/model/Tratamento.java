package model;


public class Tratamento {
    private int id;
    private String nome;
    private String dtInicio;
    private String dtFim;
    private int idAnimal;
    private int terminou;    

    public Tratamento(int id, String nome, String dtInicio, String dtFim, int idAnimal, int terminou) {
        this.id = id;
        this.nome = nome;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.idAnimal = idAnimal;
        this.terminou = terminou;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getTerminou() {
        return terminou;
    }

    public void setTerminou(int terminou) {
        this.terminou = terminou;
    }
    
        @Override
    public String toString() {        
        String desc = "Tratamento{" + "nome=" + nome + ", dtInicio=" + dtInicio + ", dtFim=" + dtFim + ", idAnimal=" + idAnimal + ", terminou=" + terminou + '}';
        return desc;
    }    
    
}