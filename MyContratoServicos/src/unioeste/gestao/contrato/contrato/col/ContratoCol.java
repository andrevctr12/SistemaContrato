package unioeste.gestao.contrato.contrato.col;

import unioeste.contrato.contrato.bo.Contrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.gestao.contrato.contrato.dao.ContratoDao;
import unioeste.gestao.contrato.manager.NegocioException;

public class ContratoCol {

    public Long inserirContrato(Contrato contrato){
        ContratoDao dao = new ContratoDao();
        ConexaoBD conexaoBD = new ConexaoBD();

        Long id;

        id = dao.cadastraContrato(contrato, conexaoBD.getConexaoMySQL());
        conexaoBD.closeConexaoMySQL();
        return id;
    }

    public Contrato obterContratoPorNumero(Long id) throws NegocioException {

        ContratoDao dao = new ContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        Contrato contrato = dao.buscaContratoPorNumero(id, conexaoBD.getConexaoMySQL());

        if (contrato == null) {
            throw new NegocioException("Contrato nao existe");
        }
        conexaoBD.closeConexaoMySQL();
        return contrato;
    }

    public int obterQtdeContratoPorCliente(String data, String documento) {

        ContratoDao dao = new ContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        return dao.buscaQtdeContratosPorCliente(data, documento, conexaoBD.getConexaoMySQL());
    }
}
