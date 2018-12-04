package unioeste.gestao.contrato.cliente.dao;//package unioeste.gestao.mecanica.cliente.dao;


import unioeste.geral.endereco.bo.EnderecoEspecifico;
import unioeste.geral.endereco.dao.EnderecoDao;
import unioeste.geral.pessoa.fisica.bo.CPF;
import unioeste.geral.pessoa.fisica.bo.Genero;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.CNPJ;
import unioeste.geral.pessoa.juridica.bo.PessoaJuridica;
import unioeste.contrato.cliente.bo.Cliente;

import java.sql.*;

public class ClienteDao {

    public Long cadastraCliente(Cliente cliente, Connection c)
    {
        Long id = (long) -1;
        if (cliente.getPessoa() instanceof PessoaFisica)
        {
            // Verifica qual o gênero do cliente
            int idG = 0;
            if(((PessoaFisica) cliente.getPessoa()).getGenero().getNome().charAt(0) == 'M' )
                idG = 1;
            if(((PessoaFisica) cliente.getPessoa()).getGenero().getNome().charAt(0) == 'F' )
                idG = 2;
            if(((PessoaFisica) cliente.getPessoa()).getGenero().getNome().charAt(0) == 'O' )
                idG = 3;

            // Gera a string de inserção em SQL


            String sql = "INSERT INTO `cliente` (`nome`, `CNPJ`, `CPF`, `Sexo_idSexo`, `senha`, `nro` " +
                    ", `complemento`, `idEnd` ) VALUES" +
                    "('"+cliente.getPessoa().getNomeCompleto()+"', NULL , '"+((PessoaFisica) cliente.getPessoa()).getCpf().getCpf()+"'," +
                    "'"+idG+"','"+cliente.getSenha()+"','"+cliente.getPessoa().getEndereco().getNro()+"" +
                    "','"+cliente.getPessoa().getEndereco().getComplemento()+"'," +
                    "'"+cliente.getPessoa().getEndereco().getEndereco().getId().intValue()+"')";

            PreparedStatement pst = null;
            try {
                Statement st = c.createStatement();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=0;");
                pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=1;");
                if (rs.next())
                    id = rs.getLong(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (cliente.getPessoa() instanceof PessoaJuridica)
        {
            String sql = "INSERT INTO `cliente` (`nome`, `CNPJ`, `CPF`, `Sexo_idSexo`, `senha`,`nro`, `complemento`, `idEnd`) VALUES" +
                    "('"+cliente.getPessoa().getNomeCompleto()+"','"+((PessoaJuridica) cliente.getPessoa()).getCnpj().getCnpj()+"', NULL," +
                    "NULL,'"+cliente.getSenha()+"','"+cliente.getPessoa().getEndereco().getNro()+"" +
                    "','"+cliente.getPessoa().getEndereco().getComplemento()+"'," +
                    "'"+cliente.getPessoa().getEndereco().getEndereco().getId().intValue()+"')";

            PreparedStatement pst = null;
            try {
                Statement st = c.createStatement();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=0;");
                pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                st.executeQuery("SET FOREIGN_KEY_CHECKS=1;");
                if (rs.next())
                    id = rs.getLong(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        EmailDao emailDao = new EmailDao();
        emailDao.cadastraListaEmail(cliente.getPessoa().getEmails(),id,c);

        TelefoneDao telefoneDao =  new TelefoneDao();
        telefoneDao.cadastraTel(cliente.getPessoa().getTelefones(),id,c);

        return id;
    }

    public Cliente buscaClientebyCPF(CPF cpf,Connection c)
    {
        Statement st = null;
        Cliente cliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM cliente where CPF like '" + cpf.getCpf() + "'");

            while (r.next()) {
                cliente = new Cliente();
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica.setCpf(new CPF());
                pessoaFisica.getCpf().setCpf(r.getString("CPF"));


                Genero genero = new Genero();
                pessoaFisica.setGenero(genero);
                if (r.getInt("Sexo_idSexo") == 1)
                    pessoaFisica.getGenero().setNome("M");
                if (r.getInt("Sexo_idSexo") == 2)
                    pessoaFisica.getGenero().setNome("F");
                if (r.getInt("Sexo_idSexo") == 3)
                    pessoaFisica.getGenero().setNome("O");

                System.out.println(pessoaFisica.getGenero().getNome());

                cliente.setPessoa(pessoaFisica);

                cliente.getPessoa().setNomeCompleto(r.getString("nome"));
                cliente.setId(r.getLong("idCliente"));


                EmailDao emailDao =  new EmailDao();
                cliente.getPessoa().setEmails(emailDao.buscaEmail(cliente.getId(),c));

                TelefoneDao telefoneDao = new TelefoneDao();
                cliente.getPessoa().setTelefones(telefoneDao.buscaTel(cliente.getId(),c));

                EnderecoDao enderecoDao = new EnderecoDao();

                cliente.getPessoa().setEndereco(new EnderecoEspecifico());
                cliente.getPessoa().getEndereco().setEndereco(enderecoDao.buscaEnderecoById(cliente.getId(),c));
                cliente.getPessoa().getEndereco().setComplemento(r.getString("complemento"));
                cliente.getPessoa().getEndereco().setNro(r.getInt("nro"));
                cliente.setSenha(r.getString("senha"));
                cliente.setQtdeContrato(r.getInt("qtdeContrato"));



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return cliente;

        }
    }

    public Cliente buscaClienteCNPJ(CNPJ cnpj ,Connection c )
    {
        Statement st = null;
        Cliente cliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM cliente where CNPJ like '" + cnpj.getCnpj() + "'");

            while (r.next()) {
                cliente = new Cliente();
                PessoaJuridica pessoaJuridica= new PessoaJuridica();
                pessoaJuridica.setCnpj(cnpj);

                cliente.setPessoa(pessoaJuridica);

                cliente.getPessoa().setNomeCompleto(r.getString("nome"));
                cliente.setId(r.getLong("idCliente"));


                EmailDao emailDao =  new EmailDao();
                cliente.getPessoa().setEmails(emailDao.buscaEmail(cliente.getId(),c));

                TelefoneDao telefoneDao = new TelefoneDao();
                cliente.getPessoa().setTelefones(telefoneDao.buscaTel(cliente.getId(),c));

                EnderecoDao enderecoDao = new EnderecoDao();

                cliente.getPessoa().setEndereco(new EnderecoEspecifico());
                cliente.getPessoa().getEndereco().setEndereco(enderecoDao.buscaEnderecoById(cliente.getId(),c));
                cliente.getPessoa().getEndereco().setComplemento(r.getString("complemento"));
                cliente.getPessoa().getEndereco().setNro(r.getInt("nro"));
                cliente.setSenha(r.getString("senha"));
                cliente.setQtdeContrato(r.getInt("qtdeContrato"));



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return cliente;

        }
    }

    public Cliente buscabyID(Long id, Connection c) {
        Statement st = null;
        Cliente cliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM cliente where idCliente like '" + id + "'");
            if (r.next()) {
                if (r.getString("CPF") != null) {
                    cliente = new Cliente();

                    cliente.setId(id);

                    PessoaFisica pessoaFisica = new PessoaFisica();
                    pessoaFisica.setCpf(new CPF());
                    pessoaFisica.getCpf().setCpf(r.getString("CPF"));

                    pessoaFisica.setGenero(new Genero());
                    if (r.getString("Sexo_idSexo") == "1")
                        pessoaFisica.getGenero().setNome("M");
                    if (r.getString("Sexo_idSexo") == "2")
                        pessoaFisica.getGenero().setNome("F");
                    if (r.getString("Sexo_idSexo") == "3")
                        pessoaFisica.getGenero().setNome("O");

                    cliente.setPessoa(pessoaFisica);
                }

                else {
                    cliente = new Cliente();

                    CNPJ cnpj = new CNPJ();
                    cnpj.setCnpj(r.getString("CNPJ"));

                    cliente.setId(id);

                    PessoaJuridica pessoaJuridica = new PessoaJuridica();
                    pessoaJuridica.setCnpj(cnpj);

                    cliente.setPessoa(pessoaJuridica);
                }

                cliente.getPessoa().setNomeCompleto(r.getString("nome"));
                cliente.setId(r.getLong("idCliente"));


                EmailDao emailDao = new EmailDao();
                cliente.getPessoa().setEmails(emailDao.buscaEmail(cliente.getId(), c));

                TelefoneDao telefoneDao = new TelefoneDao();
                cliente.getPessoa().setTelefones(telefoneDao.buscaTel(cliente.getId(), c));

                EnderecoDao enderecoDao = new EnderecoDao();

                cliente.getPessoa().setEndereco(new EnderecoEspecifico());
                cliente.getPessoa().getEndereco().setEndereco(enderecoDao.buscaEnderecoById(cliente.getId(), c));
                cliente.getPessoa().getEndereco().setComplemento(r.getString("complemento"));
                cliente.getPessoa().getEndereco().setNro(r.getInt("nro"));
                cliente.setSenha(r.getString("senha"));
                cliente.setQtdeContrato(r.getInt("qtdeContrato"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            return cliente;


        }
    }

}
