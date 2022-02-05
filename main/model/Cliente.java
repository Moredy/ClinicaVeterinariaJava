package model;

import java.util.ArrayList;
import java.util.List;


public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String cep;
    private String endereco;
    private String email;
    private List <Animal> animais;

    public Cliente(int id, String nome, String endereco, String cep,   String email ,String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
        this.endereco = endereco;
        this.email = email;
        this.animais = new ArrayList<Animal>();
        
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean addAnimal(Animal animal){
        if(!animal.getNome().isBlank()){
            animais.add(animal);
            return true;
        }
        return false;
    }
    public List<Animal> getAnimais(){
        List<Animal>copia = new ArrayList<Animal>(animais);
        return copia;
    }
        @Override
    public String toString() {        
        String desc = "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + ", email=" + email + '}';
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }    
   
  
}

       