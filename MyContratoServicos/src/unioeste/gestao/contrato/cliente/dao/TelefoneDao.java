package unioeste.gestao.contrato.cliente.dao;

import unioeste.geral.pessoa.bo.DDD;
import unioeste.geral.pessoa.bo.DDI;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDao {
    public void cadastraTel(List<Telefone> telefones, Long idCliente, Connection c)
    {
        for (Telefone telefone: telefones) {
            String sql = "INSERT INTO `telcliente` (`idTelCliente`,`idCliente`,  `telefone`,`DDD_DDD`,`DDI` ) " +
                    "VALUES (NULL, '" + idCliente.intValue()+"',"+ telefone.getTelefone()+ ",'"+telefone.getDdd().getDdd()+"" +
                    "','"+telefone.getDdi().getDdi()+"');";
            PreparedStatement pst = null;
            try {

                Statement st = c.createStatement();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=0;");
                pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.executeUpdate();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=1;");
                ResultSet rs = pst.getGeneratedKeys();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Telefone> buscaTel(Long id, Connection c) {
        List<Telefone> telefones = new ArrayList<>();
        Statement st = null;
        String sql = "SELECT * FROM `telcliente` WHERE `idTelCliente` = " + id.intValue();

        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery(sql);

            while (r.next()) {
                Telefone telefone = new Telefone();
                telefone.setDdi(new DDI());
                telefone.getDdi().setDdi(r.getInt("DDI"));

                telefone.setDdd(new DDD());
                telefone.getDdd().setDdd(r.getInt("DDD_DDD"));

                telefone.setTelefone(r.getString("telefone"));

                System.out.println(telefone);
                telefones.add(telefone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return telefones;
        }
    }
}
