package unioeste.gestao.contrato.contrato.col;

import unioeste.contrato.contrato.bo.Contrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.gestao.contrato.contrato.dao.ContratoDao;

public class ColContrato {

    public Long inserirContrato(Contrato contrato){
        ContratoDao dao = new ContratoDao();
        ConexaoBD conexaobd = new ConexaoBD();

        Long id;

        id = dao.cadastraContrato(contrato, conexaobd.getConexaoMySQL());

        return id;
    }

    public Contrato obterContratoPorNumero(Long id) throws Exception {

        ContratoDao dao = new ContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        Contrato contrato = dao.buscaContratoPorNumero(id, conexaoBD.getConexaoMySQL());

        if (contrato == null) {
            throw new Exception ("Contrato nao existe");
        }

        return contrato;
    }

    public int obterQtdeContratoPorCliente(String data, String documento) {

        ContratoDao dao = new ContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        return dao.buscaQtdeContratosPorCliente(data, documento, conexaoBD.getConexaoMySQL());
    }
}
