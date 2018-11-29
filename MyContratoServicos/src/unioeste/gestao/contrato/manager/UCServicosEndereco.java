package unioeste.gestao.contrato.manager;

import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.col.ColEndereco;

public class UCServicosEndereco {
    public UCServicosEndereco() {}

    public Endereco obterEnderecoPorID(long id) throws Exception {
        ColEndereco colend = new ColEndereco();
        Endereco endereco = new Endereco();
        endereco = colend.obterEnderecoPorID(id);
        
        return endereco;
    }

}
