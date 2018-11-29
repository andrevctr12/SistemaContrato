package unioeste.geral.pessoa.fisica.bo;

import java.io.Serializable;

public class Genero implements Serializable {
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "nome='" + nome + '\'' +
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

}
