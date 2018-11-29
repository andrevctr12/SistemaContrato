package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class EnderecoEspecifico implements Serializable {

    public EnderecoEspecifico(Long id, int nro, String complemento, Endereco endereco) {
        this.id = id;
        this.nro = nro;
        this.complemento = complemento;
        this.endereco = endereco;
    }

    public EnderecoEspecifico() {
        this.endereco = new Endereco();
    }

    private Long id;

    private int nro;

    private String complemento;

    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "EnderecoEspecifico{" +
                "id=" + id +
                ", nro=" + nro +
                ", complemento='" + complemento + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
