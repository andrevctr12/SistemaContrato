package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Endereco implements Serializable {

    public Endereco(Long id, String cep, Logradouro logradouro, Bairro bairro, Cidade cidade) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public Endereco() {
        this.logradouro = new Logradouro();
        this.bairro = new Bairro();
        this.cidade = new Cidade();
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro=" + logradouro +
                ", bairro=" + bairro +
                ", cidade=" + cidade +
                '}';
    }

    private Long id;

    private String cep;

    private Logradouro logradouro;

    private Bairro bairro;

    private Cidade cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public static boolean validaCep(String cep) {
        if (!cep.matches("\\d{8}")) {
            return false;
        }

        return true;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
