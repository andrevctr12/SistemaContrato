package unioeste.gestao.contrato.cliente.dao;

import unioeste.geral.pessoa.bo.Email;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailDao {
    public void cadastraListaEmail(List<Email> emails, Long idCliente, Connection c)
    {
        for (Email email: emails) {

            String sql = "INSERT INTO `email` (`idEmail`, `email` , `idCliente` ) VALUES (NULL, '" + email.getEmail() +
                    "','"+idCliente+"');";
            PreparedStatement pst = null;
            try {
                Statement st = c.createStatement();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=0;");
                pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=1;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Email> buscaEmail(Long id,Connection c )
    {
        List<Email> emails=  new ArrayList();
        Statement st = null;
        String sql = "SELECT * FROM `email` WHERE `idCliente` = " + id.intValue();

        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery(sql);

            while (r.next())
            {
                Email email = new Email();
                email.setEmail(r.getString("email"));
                email.setId(r.getLong("idEmail"));

                System.out.println(email);
                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return emails;
        }
    }
}
