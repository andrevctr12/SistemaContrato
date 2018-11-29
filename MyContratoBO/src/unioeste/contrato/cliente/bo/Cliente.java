package unioeste.contrato.cliente.bo;

import unioeste.geral.pessoa.bo.Pessoa;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;


public class Cliente {
    private Long id;
    private Pessoa pessoa;
    private String senha;
    private int qtdeContrato;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "pessoa=" + pessoa +
                "Qtd Contrato=" + qtdeContrato +
                '}';
    }

    public void validarObjeto () throws Exception {
        //if (pessoa.getNomeCompleto().isEmpty()){
        //    throw new Exception("Nome Invalido");
        //}

        // TODO: verificar se a solução abaixo funciona
        if (pessoa instanceof PessoaFisica){
            String[] split = pessoa.getNomeCompleto().split(" ");

            for (int i = 1; i <= split.length; i++){
                if (split[i-1].length() < 3){
                    throw new Exception(i + "º Nome Invalido");
                }
            }

            if (((PessoaFisica) pessoa).getCpf() == null){
                throw new Exception("CPF nao informado");
            }
        } else if (pessoa instanceof PessoaJuridica){
            if (pessoa.getNomeCompleto().length() < 3){
                throw new Exception("Nome Invalido");
            }

            if (((PessoaJuridica) pessoa).getCnpj() == null){
                throw new Exception("CNPJ nao informado");
            }
        }


    }

    public int getQtdeContrato() {
        return qtdeContrato;
    }

    public void setQtdeContrato(int qtdeContrato) {
        this.qtdeContrato = qtdeContrato;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
