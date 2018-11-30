package unioeste.gestao.contrato.manager;

import unioeste.contrato.contrato.bo.Contrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.gestao.contrato.contrato.col.ColContrato;

import java.sql.SQLException;

public class UCServicosContrato {

    public UCServicosContrato() {}

    // TODO: REFAZER
    public Contrato cadastrarContrato(Contrato contrato) throws NegocioException {
        ColContrato colCon = new ColContrato();
        UCServicosCliente servicosCliente = new UCServicosCliente();

        try {
            servicosCliente.cadastrarCliente(contrato.getCliente());
        } catch (Exception e){
        } finally {
            String documento = "";
            if (contrato.getCliente().getPessoa() instanceof PessoaFisica){
                documento = ((PessoaFisica) contrato.getCliente().getPessoa()).getCpf().getCpf();
            }
            if (contrato.getCliente().getPessoa() instanceof PessoaJuridica){
                documento = ((PessoaJuridica) contrato.getCliente().getPessoa()).getCnpj().getCnpj();
            }

            if (colCon.obterQtdeContratoPorCliente(contrato.getDataEmissao().toString(), documento) > 2){
                throw new NegocioException("Limite de contratos validos atingido.");
            }

            ConexaoBD conexao = new ConexaoBD();
            colCon.inserirContrato(contrato);
            try {
                conexao.getConexaoMySQL().commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return contrato;
    }

    public Contrato consultarContratoPorNumero(Long id) throws NegocioException {
        return null;
    }
}
