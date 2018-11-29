package unioeste.geral.pessoa.fisica.col;

import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.contrato.cliente.bo.Cliente;

public class ColGenero {
    public long obterSexoPorSigla(Cliente cliente) {
        int idG = 0;
        if(((PessoaFisica) cliente.getPessoa()).getGenero().getNome().charAt(0) == 'M' )
            idG = 1;
        if(((PessoaFisica) cliente.getPessoa()).getGenero().getNome().charAt(0) == 'F' )
            idG = 2;
        if(((PessoaFisica) cliente.getPessoa()).getGenero().getNome().charAt(0) == 'O' )
            idG = 3;
        return idG;
    }
}
