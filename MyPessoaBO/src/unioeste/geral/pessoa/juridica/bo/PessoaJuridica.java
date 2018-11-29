package unioeste.geral.pessoa.juridica.bo;

import unioeste.geral.pessoa.bo.Pessoa;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {
    private CNPJ cnpj;

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                super.toString() +
                "cnpj=" + cnpj +
                '}';
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public void setCnpj(CNPJ cnpj) {
        this.cnpj = cnpj;
    }
}
