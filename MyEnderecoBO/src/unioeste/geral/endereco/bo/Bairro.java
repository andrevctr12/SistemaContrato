package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Bairro implements Serializable {
    public Bairro(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Bairro() {

    }

    private Long id;

    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Bairro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

}
