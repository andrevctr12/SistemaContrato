package unioeste.gestao.contrato.cliente.dao;

import unioeste.geral.pessoa.bo.DDI;

import java.sql.*;

public class DDIDao {
    public Long cadastraDDI(DDI ddi, Connection c)
    {
        //SQL a ser executado
        String sql ="INSERT INTO `ddi` (`ddi`) VALUES ('"+ddi.getDdi()+"');";
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
}
