package unioeste.gestao.contrato.contrato.dao;

import com.sun.security.ntlm.Server;
import unioeste.contrato.cliente.bo.Cliente;
import unioeste.contrato.contrato.bo.Contrato;
import unioeste.geral.infra.ServerDate;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.gestao.contrato.cliente.dao.ClienteDao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContratoDao {

    public Long cadastraContrato(Contrato contrato, Connection c){
        Long id = 0L;

        String sql = "INSERT INTO `Contrato` (`idContrato`,`dataEmissao`,  `valor`,`descricao`,`validadeInicio`,`validadeFim`,`idCliente`,`idTipoContrato` ) " +
                "VALUES (NULL, '" + contrato.getDataEmissao() +"',"+ contrato.getValor() + ",'"+ contrato.getDescricao() +"" +
                "','"+ contrato.getValidadeInicio()+ "','"+ contrato.getValidadeFim() + "','"
                + contrato.getCliente().getId() + "','" + contrato.getTipo().getId() + "');";
        Statement st = null;
        try {

            System.out.println(sql);

            st = c.createStatement();
            st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                //retorna a chave criada
                id = rs.getLong(1);
            }

            String alteraQtde = "update `cliente` set qtdecontrato = qtdecontrato + 1 where `idCliente` = " + contrato.getCliente().getId();

            st.executeUpdate(alteraQtde);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public Contrato buscaContratoPorNumero(Long id, Connection conexao){
        Statement st = null;
        Contrato contrato = null;
        try {
            st = conexao.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM contrato where idContrato like '" + id + "'");

            while (r.next()) {
                contrato = new Contrato();
                contrato.setId(r.getLong("idContrato"));
                contrato.setDataEmissao(r.getString("dataEmissao"));
                contrato.setValidadeInicio(r.getString("validadeInicio"));
                contrato.setValidadeFim(r.getString("validadeFim"));
                contrato.setValor(r.getFloat("valor"));
                contrato.setDescricao(r.getString("descricao"));
                ClienteDao clientedao = new ClienteDao();

                TipoContratoDao contDao = new TipoContratoDao();
                contrato.setCliente(clientedao.buscabyID(r.getLong("idCliente"), conexao));
                contrato.setTipo(contDao.buscabyID(r.getLong("idTipoContrato"), conexao));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return contrato;

        }
    }

    public int buscaQtdeContratosPorCliente(String dtIni, String dtFim, Cliente cliente, Connection conexao){
        Statement st = null;
        int contrato = 0;


        LocalDate inicio = LocalDate.parse(dtIni, ServerDate.formatter());
        LocalDate fim = LocalDate.parse(dtFim, ServerDate.formatter());

        try {
            st = conexao.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM contrato where idCliente like '" + cliente.getId() + "'");

            while (r.next()) {
                String comeco = r.getString("validadeInicio");
                String finali = r.getString("validadeFim");

                LocalDate contratoComeco = LocalDate.parse(comeco, ServerDate.formatter());
                LocalDate contratoFim = LocalDate.parse(finali, ServerDate.formatter());

                if (fim.isEqual(contratoFim) || fim.isBefore(contratoFim)
                        || inicio.isEqual(contratoComeco) || inicio.isAfter(contratoComeco)){
                    contrato++;
                }
                System.out.println("QTDE de Contrato: " + contrato);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contrato;

    }
}
