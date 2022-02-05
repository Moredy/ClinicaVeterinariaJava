package model;

public class Animal {
    private int id;
    private String nome;
    private String anoNasc;
    private int idEspecie;
    private String sexo; // 0- macho, 1 -femea
    private int idCliente;

    public Animal(int id, String nome, String anoNasc, String sexo, int idEspecie, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(String anoNasc) {
        this.anoNasc = anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
        @Override
    public String toString() {
        return "Animal{" + "nome=" + nome + '}';
    }
}
