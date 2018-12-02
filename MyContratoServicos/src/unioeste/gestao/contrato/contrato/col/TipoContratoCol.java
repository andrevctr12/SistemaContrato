package unioeste.gestao.contrato.contrato.col;

import unioeste.contrato.contrato.bo.TipoContrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.gestao.contrato.contrato.dao.TipoContratoDao;
import unioeste.gestao.contrato.manager.NegocioException;

import java.util.List;

public class TipoContratoCol {
    public TipoContrato obterTipoContratoPorID(Long id) throws NegocioException {

        TipoContratoDao dao = new TipoContratoDao();

        ConexaoBD conexaoBD = new ConexaoBD();

        TipoContrato tipo = dao.buscabyID(id, conexaoBD.getConexaoMySQL());

        if (tipo == null) {
            throw new NegocioException("Tipo nao existe");
        }
        conexaoBD.closeConexaoMySQL();
        return tipo;
    }

    public List<TipoContrato> obterTiposContrato() throws NegocioException {
        TipoContratoDao dao = new TipoContratoDao();
        ConexaoBD conexaoBD = new ConexaoBD();

        List<TipoContrato> tipo = dao.busca(conexaoBD.getConexaoMySQL());

        if (tipo.isEmpty()) {
            throw new NegocioException("Não existe nenhum tipo");
        }
        conexaoBD.closeConexaoMySQL();

        return tipo;
    }
}
