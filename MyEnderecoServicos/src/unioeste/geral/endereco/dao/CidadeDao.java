package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Cidade;

import java.sql.*;

public class CidadeDao {
    public Long cadastraCidade(Cidade cidade, Connection c)
    {
        UnidadeFederativaDao ufdao= new UnidadeFederativaDao();
        Long idUF = ufdao.cadastraUF(cidade.getUf(),c);
        cidade.getUf().setId(idUF);
        String sql ="INSERT INTO `cidade` (`nomeCidade`, `idUF`) VALUES ('"+cidade.getNome()+"', '"+idUF+"')";
        PreparedStatement pst = null;
        Long id = new Long( -1);
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

    public Cidade buscaCidadeById(Long id,Connection c)
    {
        Statement st = null;
        Cidade cidade = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM cidade where  idCidade =  '" + id + "'");
            while (r.next()) {
                cidade = new Cidade();
                cidade.setNome(r.getString("nomeCidade"));
                cidade.setId(r.getLong("idCidade"));

                UnidadeFederativaDao unidadeFederativaDao= new UnidadeFederativaDao();
                cidade.setUf(unidadeFederativaDao.buscaUFById(r.getLong("idUF"),c));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return cidade;

        }
    }
}
