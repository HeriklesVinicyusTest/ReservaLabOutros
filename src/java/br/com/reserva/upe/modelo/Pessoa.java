package br.com.reserva.upe.modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pessoa")
@SessionScoped

public class Pessoa {
    
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;

    public Pessoa(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public Pessoa(){}
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
