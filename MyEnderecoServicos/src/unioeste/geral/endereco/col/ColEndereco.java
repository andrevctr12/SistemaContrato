package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.dao.EnderecoDao;
import unioeste.geral.infra.ConexaoBD;

public class ColEndereco {

    public ColEndereco(){}

    public Endereco obterEnderecoPorID(long id) throws Exception {
        // Instância de EnderecoDao
        EnderecoDao daoend = new EnderecoDao();

        // Início da conexão com BD
        ConexaoBD conexaoBD = new ConexaoBD();

        // Endereço obtido a partir do ID com auxílio da classe EnderecoDao
        Endereco endereco = daoend.buscaEnderecoById(id, conexaoBD.getConexaoMySQL());

        // Se endereço não existe, retornar erro.
        if (endereco == null) {
            throw new Exception("Cliente nao existe");
        }

        // Retornar endereço.
        return endereco;
    }
}
