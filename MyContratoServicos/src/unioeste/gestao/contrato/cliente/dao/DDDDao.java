package unioeste.gestao.contrato.cliente.dao;

import unioeste.geral.pessoa.bo.DDD;

import java.sql.*;

public class DDDDao {
    public Long cadastraDDD(DDD ddd, Connection c)
    {
        //SQL a ser executado
        String sql ="INSERT INTO `ddd` (`DDD`) VALUES ('"+ddd.getDdd()+"');";
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
