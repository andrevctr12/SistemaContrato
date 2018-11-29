package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Logradouro implements Serializable {

    public Logradouro(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Logradouro() { }

    private Long id;

    @Override
    public String toString() {
        return "Logradouro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    private String nome;

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
}
