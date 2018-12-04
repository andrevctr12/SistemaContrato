package unioeste.gestao.contrato.manager;

import unioeste.contrato.cliente.bo.Cliente;
import unioeste.contrato.contrato.bo.Contrato;
import unioeste.contrato.contrato.bo.TipoContrato;
import unioeste.geral.infra.ConexaoBD;
import unioeste.geral.infra.ServerDate;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.gestao.contrato.cliente.col.ColCliente;
import unioeste.gestao.contrato.contrato.col.ContratoCol;
import unioeste.gestao.contrato.contrato.col.TipoContratoCol;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UCServicosContrato {

    public UCServicosContrato() {}

    public Contrato cadastrarContrato(Contrato contrato) throws Exception {
        ContratoCol colCon = new ContratoCol();
        ColCliente colCli = new ColCliente();

        TipoContratoCol colTipo = new TipoContratoCol();

        LocalDate localDate = LocalDate.now();
        String formattedString = localDate.format(ServerDate.formatter());
        contrato.setDataEmissao(formattedString);

        Cliente cliente = colCli.obterClientePorId(contrato.getCliente().getId());

        TipoContrato tipoContrato = colTipo.obterTipoContratoPorID(contrato.getTipo().getId());

//        contrato.setCliente(cliente);
//        contrato.setTipo(tipoContrato);

        contrato.validarObjeto();

        colCon.obterQtdeContratoValidoPorCliente(contrato.getValidadeInicio(), contrato.getValidadeFim(), cliente);


        ConexaoBD conexao = new ConexaoBD();
        contrato = colCon.inserirContrato(contrato);

        conexao.getConexaoMySQL().commit();


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
