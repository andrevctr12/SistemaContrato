package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Pais;

import java.sql.*;

public class PaisDao {

    public Long cadastraPais(Pais pais, Connection c ) {
        String sql = "INSERT INTO pais (nomePais) VALUES ('" + pais.getNome() + "');";
        PreparedStatement pst = null;
        Long id = new Long(-1);
        try {
            pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next())
                id = rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }

    public Pais buscaPaisByID(Long id,Connection c)
    {
        Statement st = null;
        Pais pais = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM pais where idPais = '" + id + "'");

            while (r.next()) {
                pais = new Pais();
                pais.setNome(r.getString("nomePais"));
                pais.setId(r.getLong("idPais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return pais;

        }
    }

}
