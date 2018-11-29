package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Bairro;

import java.sql.*;

public class BairroDao {
    public Long cadastraBairro(Bairro bairro, Connection c)
    {
        //SQL a ser executado
        String sql ="INSERT INTO bairro (nomeBairro) VALUES ('"+ bairro.getNome() + "');";
        PreparedStatement pst = null;

        //id default em caso de erro
        Long id = new Long(-1);
        try {
            //executa o sql
            pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                //retorna a chave criada
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return id;
        }

    }

    public Bairro buscaBairroById(Long id, Connection c)
    {
        Statement st = null;
        Bairro bairro = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM bairro where idBairro = '" + id + "'");

            while (r.next()) {
                bairro = new Bairro();
                bairro.setNome(r.getString("nomeBairro"));
                bairro.setId(r.getLong("idBairro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return bairro;

        }
    }

}
