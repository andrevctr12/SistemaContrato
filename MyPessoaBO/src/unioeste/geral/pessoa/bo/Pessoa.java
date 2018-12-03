package unioeste.geral.pessoa.bo;

import unioeste.geral.endereco.bo.EnderecoEspecifico;

import java.io.Serializable;
import java.util.List;

public abstract class Pessoa implements Serializable {

    private String nomeCompleto;
    private List<Email> emails;
    private List<Telefone> telefones;
    private EnderecoEspecifico endereco;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public EnderecoEspecifico getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEspecifico endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", emails=" + emails +
                ", telefones=" + telefones +
                ", endereco=" + endereco +
                '}';
    }
}
