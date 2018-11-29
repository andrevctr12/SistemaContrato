package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Endereco;

import java.sql.*;

public class EnderecoDao {
    public Long cadastraEndereco(Endereco endereco, Connection c)
    {
        CidadeDao cidadeDao = new CidadeDao();
        LogradouroDao ruaDao = new LogradouroDao();
        BairroDao bairroDao = new BairroDao();
        Long idRua = ruaDao.cadastraLogradouro(endereco.getLogradouro(),c);
        Long idBairro = bairroDao.cadastraBairro(endereco.getBairro(),c);
        Long idCidade = cidadeDao.cadastraCidade(endereco.getCidade(),c);

        endereco.getLogradouro().setId(idRua);
        endereco.getBairro().setId(idBairro);
        endereco.getCidade().setId(idCidade);

        String sql ="INSERT INTO `endereco` (`idLogradouro`, `idBairro`, `Cidade_idCidade`, `cep`) VALUES ('"+idRua+"', '"+idBairro+"'," +
                " '"+idCidade+"', '"+endereco.getCep()+"')";
        PreparedStatement pst = null;
        Long id = new Long(-1);
        try {
            pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return id;
        }
    }

    public Endereco buscaEnderecoById(Long id,Connection c)
    {
        Statement st = null;
        Endereco endereco = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM endereco where  idEndereco=  '" + id + "'");
            while (r.next()) {
                endereco = new Endereco();
                LogradouroDao logradouroDao = new LogradouroDao();
                endereco.setLogradouro(logradouroDao.buscaRuaById(r.getLong("idLogradouro"),c));

                BairroDao bairroDao = new BairroDao();
                endereco.setBairro(bairroDao.buscaBairroById(r.getLong("idBairro"),c));

                CidadeDao cidadeDao = new CidadeDao();
                endereco.setCidade(cidadeDao.buscaCidadeById(r.getLong("Cidade_idCidade"),c));

                endereco.setCep(r.getString("cep"));

                endereco.setId(r.getLong("idEndereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return endereco;

        }
    }
}
