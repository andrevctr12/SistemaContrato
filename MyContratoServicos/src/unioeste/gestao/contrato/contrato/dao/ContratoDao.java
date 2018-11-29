package unioeste.gestao.contrato.contrato.dao;

import unioeste.contrato.contrato.bo.Contrato;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.gestao.contrato.cliente.dao.ClienteDao;

import java.sql.*;

public class ContratoDao {

    public Long cadastraContrato(Contrato contrato, Connection c){
        Long id = 0L;
        String documento = "";
        if (contrato.getCliente().getPessoa() instanceof PessoaFisica){
            documento = ((PessoaFisica) contrato.getCliente().getPessoa()).getCpf().getCpf();
        }
        if (contrato.getCliente().getPessoa() instanceof PessoaJuridica){
            documento = ((PessoaJuridica) contrato.getCliente().getPessoa()).getCnpj().getCnpj();
        }

        String sql = "INSERT INTO `Contrato` (`idContrato`,`dataEmissao`,  `valor`,`descricao`,`validadeInicio`,`validadeFim`,`documento`,`TipoContrato_idTipoContrato` ) " +
                "VALUES (NULL, '" + contrato.getDataEmissao() +"',"+ contrato.getValor() + ",'"+ contrato.getDescricao() +"" +
                "','"+ contrato.getValidadeInicio()+ "','"+ contrato.getValidadeFim() + "','"
                 + documento + "','" + contrato.getTipo().getId() + "');";
        PreparedStatement pst = null;
        try {

            Statement st = c.createStatement();
            st.executeQuery("SET FOREIGN_KEY_CHECKS=0;");
            pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            st.executeQuery("SET FOREIGN_KEY_CHECKS=1;");
            ResultSet rs = pst.getGeneratedKeys();
            id = rs.getLong(1);
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
                contrato.setDataEmissao(r.getString("dataEmissao"));
                contrato.setValor(r.getFloat("valor"));
                contrato.setDescricao(r.getString("descricao"));
                ClienteDao clientedao = new ClienteDao();

                TipoContratoDao contDao = new TipoContratoDao();
                contrato.setCliente(clientedao.buscabyID(r.getLong("Cliente_idCliente"), conexao));
                contrato.setTipo(contDao.buscabyID(r.getLong("TipoContrato_idContrato"), conexao));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return contrato;

        }
    }

    public int buscaQtdeContratosPorCliente(String data, String documento, Connection conexao){
        Statement st = null;
        int contrato = 0;
        String[] values = data.split("/");
        int date =  Integer.parseInt(values[2]) * 100 + Integer.parseInt(values[1]) * 10 + Integer.parseInt(values[0]) ;
        try {
            st = conexao.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM contrato where documento like '" + documento + "'");

            while (r.next()) {
                String dataComeco= r.getString("validadeInicio");
                String[] comecoSplit = dataComeco.split("/");
                String dataFim= r.getString("validadeFim");
                String[] fimSplit = dataFim.split("/");
                int dateComeco =  Integer.parseInt(comecoSplit[2]) * 100 + Integer.parseInt(comecoSplit[1]) * 10 + Integer.parseInt(comecoSplit[0]) ;
                int dateFim = Integer.parseInt(fimSplit[2]) * 100 + Integer.parseInt(fimSplit[1]) * 10 + Integer.parseInt(fimSplit[0]) ;
                if (date >= dateComeco && date <= dateFim){
                    contrato++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return contrato;
        }
    }
}
