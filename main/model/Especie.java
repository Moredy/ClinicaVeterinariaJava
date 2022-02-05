package model;


public class Especie {
        private int Id;
        private String nome;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Especie(int id, String nome) {
               this.Id= id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    @Override
        public String toString() {
        return "Especie{" + "nome=" + nome + '}';
    }
}
