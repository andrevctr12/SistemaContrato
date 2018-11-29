package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Logradouro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogradouroDao {

    public Long cadastraLogradouro(Logradouro logradouro, java.sql.Connection c)
    {
        //TODO: arrumar isso de rua para logradouro
        String sql ="INSERT INTO `logradouro`  (nomeLogradouro) VALUES ('"+ logradouro.getNome() + "');";
        Long id = new Long(-1);
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next())
                id = rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return id;
        }
    }

    public Logradouro buscaRuaById(Long id,java.sql.Connection c )
    {
        Statement st = null;
        Logradouro logradouro = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM `logradouro` where idLogradouro  = '" + id + "'");

            while (r.next()) {
                logradouro = new Logradouro();
                logradouro.setNome(r.getString("nomeLogradouro"));
                logradouro.setId(r.getLong("idLogradouro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return logradouro;

        }
    }


}
