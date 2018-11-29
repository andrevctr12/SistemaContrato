package unioeste.geral.pessoa.juridica.bo;

import java.io.Serializable;

public class CNPJ implements Serializable {
    private String cnpj;

    @Override
    public String toString() {
        return "CNPJ{" +
                "cnpj='" + cnpj + '\'' +
                '}';
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
