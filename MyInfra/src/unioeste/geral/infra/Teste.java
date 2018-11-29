package unioeste.geral.infra;

import unioeste.contrato.cliente.bo.Cliente;
import unioeste.geral.endereco.bo.*;
import unioeste.geral.endereco.dao.EnderecoDao;
import unioeste.geral.pessoa.bo.*;
import unioeste.geral.pessoa.fisica.bo.CPF;
import unioeste.geral.pessoa.fisica.bo.PessoaFisica;
import unioeste.geral.pessoa.juridica.bo.CNPJ;
import unioeste.gestao.contrato.cliente.dao.ClienteDao;
import unioeste.gestao.contrato.cliente.dao.DDDDao;
import unioeste.gestao.contrato.cliente.dao.DDIDao;
import unioeste.gestao.contrato.cliente.dao.TelefoneDao;


import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {

        Bairro bairro = new Bairro();
        bairro.setNome("Santa rosa");

        Logradouro logradouro = new Logradouro();
        logradouro.setNome("Cambuquira");

        Pais pais = new Pais();
        pais.setNome("Brasil");

        UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
        unidadeFederativa.setNome("Paraná");
        unidadeFederativa.setPais(pais);

        Cidade cidade = new Cidade();
        cidade.setNome("Foz do Iguaçu");

        cidade.setUf(unidadeFederativa);

        Endereco endereco = new Endereco();
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setLogradouro(logradouro);
        endereco.setCep("1233213");

        ConexaoBD conexaoBD = new ConexaoBD();

        EnderecoDao enderecoDao =  new EnderecoDao();

        Long a = enderecoDao.cadastraEndereco(endereco,conexaoBD.getConexaoMySQL());

        Cliente cliente = new Cliente();

        Telefone telefone = new Telefone();
        telefone.setTelefone("123321");
        DDD ddd = new DDD();
        ddd.setDdd(45);
        DDI ddi = new DDI();
        ddi.setDdi(51);

        List<Telefone> telefones = new ArrayList<>();

        telefones.add(telefone);

        telefone.setDdd(ddd);
        telefone.setDdi(ddi);

        DDDDao dddDao =  new DDDDao();
     //   dddDao.cadastraDDD(ddd, conexaoBD.getConexaoMySQL());
        DDIDao ddiDao = new  DDIDao();
     //   ddiDao.cadastraDDI(ddi,conexaoBD.getConexaoMySQL());

        TelefoneDao telefoneDao = new TelefoneDao();
        Long bla = new Long(1);

        Email email = new Email();
        email.setEmail("afcflecha@gmail.com");

        List<Email> emails = new ArrayList<>();
        emails.add(email);

        cliente.setSenha("12333231");
        PessoaFisica pessoaFisica =new PessoaFisica();

        CPF cpf = new CPF();
        cpf.setCpf("3");

        pessoaFisica.setCpf(cpf);

        pessoaFisica.setEmails(emails);

        cliente.setPessoa(pessoaFisica);
        cliente.getPessoa().setTelefones(telefones);
        cliente.getPessoa().setEmails(emails);
        cliente.getPessoa().setEndereco(new EnderecoEspecifico());
        cliente.getPessoa().setNomeCompleto("Tinha esquecido de setar o nome");
        cliente.getPessoa().getEndereco().setEndereco(endereco);
        cliente.getPessoa().getEndereco().setComplemento("casa");
        cliente.getPessoa().getEndereco().setNro(12);
        cliente.getPessoa().getEndereco().getEndereco().setId(a);
        ClienteDao clienteDao = new ClienteDao();
        //clienteDao.cadastraCliente(cliente,conexaoBD.getConexaoMySQL());

        cliente = null;
        CNPJ cnpj = new CNPJ();
        cnpj.setCnpj("1");
        cliente = clienteDao.buscaClienteCNPJ(cnpj,conexaoBD.getConexaoMySQL());
        System.out.println(cliente);

        /*enderecoDao.cadastraEndereco(endereco,conexaoBD.getConexaoMySQL());

        Long a = new Long(1);
        endereco = enderecoDao.buscaEnderecoById(a,conexaoBD.getConexaoMySQL());
        System.out.println(endereco);*/


    }
}
