package unioeste.gestao.contrato.manager;

import unioeste.contrato.cliente.bo.Cliente;
import unioeste.geral.infra.ConexaoBD;
import unioeste.geral.pessoa.fisica.bo.CPF;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.fisica.col.ColGenero;
import unioeste.geral.pessoa.juridica.bo.CNPJ;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.gestao.contrato.cliente.col.ColCliente;
import unioeste.gestao.contrato.cliente.col.ColEmailCliente;
import unioeste.gestao.contrato.cliente.col.ColFoneCliente;

public class UCServicosCliente {

    public UCServicosCliente() {}

    public Cliente cadastrarCliente(Cliente cliente) throws Exception  {
        // Controle de cliente
        ColCliente colCli = new ColCliente();

        // Falar com professor sobre posicionamento desta função.
        cliente.validarObjeto();

        // Verificar tipo de cliente para consultar por cpf ou cnpj.
        if (cliente.getPessoa() instanceof PessoaFisica) {
            try {
                cliente = colCli.obterClientePorCPF(((PessoaFisica) cliente.getPessoa()).getCpf());
            } catch (Exception ee) {}
        } else if (cliente.getPessoa() instanceof PessoaJuridica) {
            try {
                cliente = colCli.obterClientePorCNPJ(((PessoaJuridica) cliente.getPessoa()).getCnpj());
            } catch (Exception ee) {}
        }

        // Se foi encontrado cliente com CPF/CNPJ, retorne um erro.
        if (cliente != null) {
            throw new Exception("Cliente já existe");
        }

        cliente.validarObjeto();

        // Controle de
        ColFoneCliente colfone = new ColFoneCliente();
        ColEmailCliente colemail = new ColEmailCliente();
        ColGenero colsexo = new ColGenero();

        colfone.validarFones(cliente.getPessoa().getTelefones());

        if (cliente.getPessoa() instanceof PessoaFisica) {
            ((PessoaFisica) cliente.getPessoa()).getGenero().setId(colsexo.obterSexoPorSigla(cliente));
        }


        // iniciar transação
        ConexaoBD conexao = new ConexaoBD();

        // Guardar ID de cliente.
        Long idCliente;
        idCliente = colCli.inserirCliente(cliente);

        colfone.inserirListaFones(cliente.getPessoa().getTelefones(), idCliente, conexao.getConexaoMySQL());
        colemail.inserirListaEmails(cliente.getPessoa().getEmails());

        //TODO: pegar endereço a partir do ID por razões que desconheço.

        // fechar transacao
        conexao.getConexaoMySQL().commit();

        return cliente;
    }

    public Cliente consultarCliente(CPF cpf, CNPJ cnpj) throws Exception {
        ColCliente colCli = new ColCliente();
        Cliente cliente = null;

        if (cpf == null){
            cliente = colCli.obterClientePorCNPJ(cnpj);
        } else {
            cliente = colCli.obterClientePorCPF(cpf);
        }

        return cliente;
    }

}
