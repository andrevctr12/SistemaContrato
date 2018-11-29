package unioeste.gestao.contrato.cliente.col;

import unioeste.geral.pessoa.bo.Telefone;
import unioeste.gestao.contrato.cliente.dao.TelefoneDao;

import java.sql.Connection;
import java.util.List;

public class ColFoneCliente {
    public void inserirListaFones(List<Telefone> telefones, Long idCliente, Connection connection) {
        TelefoneDao daotel = new TelefoneDao();
        ColCliente colcliente = new ColCliente();

        daotel.cadastraTel(telefones, idCliente, connection);
    }

    public void validarFones(List<Telefone> telefones) {
    }
}
