package model;


public class Exame {
    private int id;
    private String descricao;
    private int idConsulta;    

    public Exame(int id, String descricao, int idConsulta) {
        this.id = id;
        this.descricao = descricao;
        this.idConsulta = idConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){
       String desc = "Exame{"+"desc="+descricao+ ", idConsulta="+ idConsulta+'}';
       return desc;
    }
    
}
