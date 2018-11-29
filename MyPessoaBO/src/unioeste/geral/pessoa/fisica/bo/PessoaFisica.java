package unioeste.geral.pessoa.fisica.bo;

import unioeste.geral.pessoa.bo.Pessoa;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {
    private CPF cpf;

    private Genero genero;

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public Genero getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                super.toString() +
                "cpf=" + cpf +
                ", genero=" + genero +
                '}';
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
