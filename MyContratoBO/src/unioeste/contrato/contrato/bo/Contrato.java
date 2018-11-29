package unioeste.contrato.contrato.bo;

import unioeste.contrato.cliente.bo.Cliente;

public class Contrato {
    private Long id;
    private float valor;
    private Cliente cliente;
    private String descricao;
    private TipoContrato tipo;
    private String dataEmissao;
    private String validadeInicio;
    private String validadeFim;

    public Contrato(Long id, float valor, Cliente cliente, String descricao, TipoContrato tipo, String dataEmissao, String validadeInicio, String validadeFim) {
        this.id = id;
        this.valor = valor;
        this.cliente = cliente;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataEmissao = dataEmissao;
        this.validadeInicio = validadeInicio;
        this.validadeFim = validadeFim;
    }

    public Contrato() {
    }

    @Override
    public String toString() {
        return "Contrato{ " +
                "Nro. =" + id +
                ", Data de Emissao =" + dataEmissao + "\n" +
                "Periodo de Validade = " + validadeInicio + " a " + validadeFim + "\n" +
                "Tipo de Contrato = " + tipo + "\n" +
                "Cliente = " + cliente + "\n" +
                "Valor = R$ " + valor + "\n" +
                "Descricao = " + descricao +
                " }";
    }

    public void validarObjeto()throws Exception {
        if (cliente.getQtdeContrato() > 3){
            throw new Exception("Cliente ja possui 3 contratos validos");
        }
        if (valor <= 1.0){
            throw new Exception("Valor invalido");
        }

        if (descricao.length() <= 1){
            throw new Exception("Descricao Invalida");
        }

        String[] emissao = dataEmissao.split("/");
        String[] inicio = validadeInicio.split("/");
        String[] fim = validadeFim.split("/");
        int dataEm =  Integer.parseInt(emissao[2]) * 100 + Integer.parseInt(emissao[1]) * 10 + Integer.parseInt(emissao[0]) ;
        int dataIni =  Integer.parseInt(inicio[2]) * 100 + Integer.parseInt(inicio[1]) * 10 + Integer.parseInt(inicio[0]) ;
        int dataFim =  Integer.parseInt(fim[2]) * 100 + Integer.parseInt(fim[1]) * 10 + Integer.parseInt(fim[0]) ;


        if (dataIni < dataEm || dataFim < dataIni){
            throw new Exception("Data invalida");
        }
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoContrato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContrato tipo) {
        this.tipo = tipo;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getValidadeInicio() {
        return validadeInicio;
    }

    public void setValidadeInicio(String validadeInicio) {
        this.validadeInicio = validadeInicio;
    }

    public String getValidadeFim() {
        return validadeFim;
    }

    public void setValidadeFim(String validadeFim) {
        this.validadeFim = validadeFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
