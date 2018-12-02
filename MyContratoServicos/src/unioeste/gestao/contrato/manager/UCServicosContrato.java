package unioeste.gestao.contrato.manager;

import unioeste.contrato.contrato.bo.Contrato;
import unioeste.contrato.contrato.bo.TipoContrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.gestao.contrato.contrato.col.ContratoCol;
import unioeste.gestao.contrato.contrato.col.TipoContratoCol;

import java.sql.SQLException;
import java.util.List;

public class UCServicosContrato {

    public UCServicosContrato() {}

    // TODO: REFAZER
    public Contrato cadastrarContrato(Contrato contrato) throws ServicoException {
        ContratoCol colCon = new ContratoCol();
        UCServicosCliente servicosCliente = new UCServicosCliente();

        try {
            servicosCliente.cadastrarCliente(contrato.getCliente());
        } catch (Exception e){
            throw new ServicoException();
        }
            String documento = "";
            if (contrato.getCliente().getPessoa() instanceof PessoaFisica){
                documento = ((PessoaFisica) contrato.getCliente().getPessoa()).getCpf().getCpf();
            }
            if (contrato.getCliente().getPessoa() instanceof PessoaJuridica){
                documento = ((PessoaJuridica) contrato.getCliente().getPessoa()).getCnpj().getCnpj();
            }

            if (colCon.obterQtdeContratoPorCliente(contrato.getDataEmissao().toString(), documento) > 2){
                throw new ServicoException("Limite de contratos validos atingido.");
            }

            ConexaoBD conexao = new ConexaoBD();
            colCon.inserirContrato(contrato);
            try {
                conexao.getConexaoMySQL().commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        return contrato;
    }

    public Contrato consultarContratoPorNumero(Long id) throws ServicoException {
        ContratoCol colCon = new ContratoCol();
        try {
            return colCon.obterContratoPorNumero(id);
        } catch (NegocioException e) {
            throw new ServicoException("Não foi possível encontrar contrato com id: " + id);
        }

    }

    public List<TipoContrato> buscaTiposContrato() throws ServicoException {
        TipoContratoCol col = new TipoContratoCol();

        try {
            return col.obterTiposContrato();
        } catch (NegocioException e) {
            throw new ServicoException("Não foi possível encontrar nenhum tipo de contrato");
        }

    }
}
