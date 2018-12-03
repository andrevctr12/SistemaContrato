package unioeste.gestao.contrato.cliente.col;

import unioeste.geral.pessoa.fisica.bo.CPF;
import unioeste.geral.pessoa.juridica.bo.CNPJ;
import unioeste.geral.infra.ConexaoBD;
import unioeste.contrato.cliente.bo.Cliente;
import unioeste.gestao.contrato.cliente.dao.ClienteDao;

public class ColCliente {
    public ColCliente() {};

    public Cliente obterClientePorCPF(CPF cpf) throws Exception  {

        // Instância de ClienteDao
        ClienteDao daocliente = new ClienteDao();

        // Início da conexão com BD
        ConexaoBD conexaoBD = new ConexaoBD();

        // Cliente obtido a partir do CPF com auxílio da classe ClienteDao
        Cliente cliente = daocliente.buscaClientebyCPF(cpf,conexaoBD.getConexaoMySQL());

        // Se cliente não existe, retornar erro.
        if (cliente == null) {
            throw new Exception ("Cliente nao existe");
        }

        conexaoBD.closeConexaoMySQL();

        // Retornar cliente.
        return cliente;
    }

    public Cliente obterClientePorCNPJ(CNPJ cnpj) throws Exception  {

        // Instância de ClienteDao
        ClienteDao daocliente = new ClienteDao();

        // Início da conexão com BD
        ConexaoBD conexaoBD = new ConexaoBD();
        // Cliente obtido a partir do CNPJ com auxílio da classe ClienteDao
        Cliente cliente = daocliente.buscaClienteCNPJ(cnpj, conexaoBD.getConexaoMySQL());

        // Se cliente não existe, retornar erro.
        if (cliente == null) {
            throw new Exception ("Cliente nao existe");
        }

        // Retornar cliente.

        conexaoBD.closeConexaoMySQL();

        return cliente;
    }

    public Long inserirCliente(Cliente cliente) {
        ClienteDao daocliente = new ClienteDao();
        ConexaoBD conexaobd = new ConexaoBD();

        Long id;

        id = daocliente.cadastraCliente(cliente, conexaobd.getConexaoMySQL());

        return id;
    }

}
