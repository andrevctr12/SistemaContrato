package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Cidade implements Serializable {
    public Cidade(Long id, String nome, UnidadeFederativa uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf=" + uf +
                '}';
    }

    public Cidade() {
        this.uf = new UnidadeFederativa();
    }

    private Long id;

    private String nome;

    private UnidadeFederativa uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }
}
