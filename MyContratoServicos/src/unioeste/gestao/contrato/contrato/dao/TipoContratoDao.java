package unioeste.gestao.contrato.contrato.dao;


import unioeste.contrato.contrato.bo.TipoContrato;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TipoContratoDao {
    public TipoContrato buscabyID(Long id, Connection c) {
        Statement st = null;
        String sql = "SELECT * FROM TipoContrato WHERE idTipoContrato = " + id.intValue();
        TipoContrato tipo = new TipoContrato();

        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery(sql);

            while (r.next()) {
                tipo.setId(id);
                tipo.setNomeTipo(r.getString("nomeTipo"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return tipo;
        }
    }
}
