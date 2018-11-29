package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class UnidadeFederativa implements Serializable {
    public UnidadeFederativa(Long id, String sigla, String nome, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public UnidadeFederativa() {
        this.pais = new Pais();
    }

    private Long id;

    private String nome;

    private Pais pais;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UnidadeFederativa{" +
                "id=" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
