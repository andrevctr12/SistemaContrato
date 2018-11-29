package unioeste.geral.infra;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private Connection connection = null;

    public ConexaoBD() {
        try {
            Context ctx;
            //java:comp/env
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/contratopool");
            connection = ds.getConnection();
            //ds = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
        }catch (SQLException se) {
            System.out.println("conexao falhou!");
        } catch (NamingException e) {
            e.printStackTrace();
        }

//        try {
//            Context initialcontext = new javax.naming.InitialContext();
//            Context context = (javax.naming.Context) initialcontext.lookup("java:comp/env");
//            DataSource datasource = (javax.sql.DataSource) context.lookup("jdbc/contratopool");
//
//            connection = datasource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
    }

    public Connection getConexaoMySQL() {
        return connection;
    }

}

