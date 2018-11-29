package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.UnidadeFederativa;

import java.sql.*;

public class UnidadeFederativaDao {
    public Long cadastraUF(UnidadeFederativa unidadeFederativa, Connection c)
    {
        PaisDao paisDao = new PaisDao();
        Long idPais = paisDao.cadastraPais(unidadeFederativa.getPais(),c);
        unidadeFederativa.getPais().setId(idPais);
        String sql ="INSERT INTO `unidadefederativa` ( `nomeUF`, `idPais`) VALUES ( '"+unidadeFederativa.getNome()+"'," +
                " '"+idPais+"');";
        PreparedStatement pst = null;
        Long id = new Long(-1);
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

    public UnidadeFederativa buscaUFById(Long id,Connection c)
    {
        Statement st = null;
        UnidadeFederativa uf = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM unidadefederativa where idUnidadeFederativa = '" + id + "'");
            while (r.next()) {
                uf = new UnidadeFederativa();
                uf.setNome(r.getString("nomeUF"));
                uf.setId(r.getLong("idUnidadeFederativa"));

                PaisDao paisDao =new PaisDao();
                uf.setPais(paisDao.buscaPaisByID(r.getLong("idPais"),c));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return uf;

        }
    }
}
