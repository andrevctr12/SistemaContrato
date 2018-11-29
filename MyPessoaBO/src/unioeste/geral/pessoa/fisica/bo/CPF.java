package unioeste.geral.pessoa.fisica.bo;

import java.io.Serializable;

public class CPF implements Serializable {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean validarCPF() {
        return true;
    }

    @Override
    public String toString() {
        return "CPF{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
