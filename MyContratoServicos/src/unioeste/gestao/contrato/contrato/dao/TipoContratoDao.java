package unioeste.gestao.contrato.contrato.dao;


import unioeste.contrato.contrato.bo.TipoContrato;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoContratoDao {
    public TipoContrato buscabyID(Long id, Connection c) {
        Statement st = null;
        String sql = "SELECT * FROM TipoContrato WHERE idTipoContrato = " + id.intValue();
        TipoContrato tipo = new TipoContrato();

        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery(sql);

            if (r.next()) {
                tipo.setId(id);
                tipo.setNomeTipo(r.getString("nomeTipo"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipo;

    }

    public List<TipoContrato> busca(Connection c) {
        Statement st = null;
        String sql = "SELECT * FROM TipoContrato";
        List<TipoContrato> tipos = new ArrayList<>();

        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery(sql);

            while (r.next()) {
                TipoContrato tipo = new TipoContrato();
                tipo.setId(r.getLong("idTipoContrato"));
                tipo.setNomeTipo(r.getString("nomeTipo"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }
}
