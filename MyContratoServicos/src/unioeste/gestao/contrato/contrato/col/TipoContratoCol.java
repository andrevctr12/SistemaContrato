package unioeste.gestao.contrato.contrato.col;

import unioeste.contrato.contrato.bo.TipoContrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.gestao.contrato.contrato.dao.TipoContratoDao;

public class TipoContratoCol {
    public TipoContrato obterContratoPorID(Long id) throws Exception {

        TipoContratoDao dao = new TipoContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        TipoContrato tipo = dao.buscabyID(id, conexaoBD.getConexaoMySQL());

        if (tipo == null) {
            throw new Exception ("Tipo nao existe");
        }

        return tipo;
    }
}
