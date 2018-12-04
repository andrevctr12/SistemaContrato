package unioeste.gestao.contrato.contrato.col;

import unioeste.contrato.cliente.bo.Cliente;
import unioeste.contrato.contrato.bo.Contrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.gestao.contrato.cliente.col.ColCliente;
import unioeste.gestao.contrato.contrato.dao.ContratoDao;
import unioeste.gestao.contrato.manager.NegocioException;

import java.sql.Connection;
import java.sql.SQLException;

public class ContratoCol {

    public Contrato inserirContrato(Contrato contrato) throws Exception {
        ContratoDao dao = new ContratoDao();
        ColCliente colCliente = new ColCliente();
        ConexaoBD conexaoBD = new ConexaoBD();

        Long id;

        Connection connection = conexaoBD.getConexaoMySQL();

        id = dao.cadastraContrato(contrato, connection);

        try {
            conexaoBD.getConexaoMySQL().commit();

            contrato = dao.buscaContratoPorNumero(id, connection);

        } catch (SQLException e) {
            throw new NegocioException("Contrato nao cadastrado");
        }
        finally {
            conexaoBD.closeConexaoMySQL();
        }


        return contrato;
    }

    public Contrato obterContratoPorNumero(Long id) throws NegocioException {

        ContratoDao dao = new ContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        Contrato contrato = dao.buscaContratoPorNumero(id, conexaoBD.getConexaoMySQL());

        conexaoBD.closeConexaoMySQL();

        if (contrato == null) {
            throw new NegocioException("Contrato nao existe");
        }

        return contrato;
    }

    public int obterQtdeContratoValidoPorCliente(String dataInicio, String dataFim, Cliente cliente) throws NegocioException {

        ContratoDao dao = new ContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        int contratos = dao.buscaQtdeContratosPorCliente(dataInicio, dataFim, cliente, conexaoBD.getConexaoMySQL());

        conexaoBD.closeConexaoMySQL();

        if (contratos >= 3){
            throw new NegocioException("Limite de contratos atingido");
        }



        return contratos;
    }
}
