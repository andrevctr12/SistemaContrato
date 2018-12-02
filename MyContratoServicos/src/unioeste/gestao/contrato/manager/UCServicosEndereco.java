package unioeste.gestao.contrato.manager;

import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.col.ColEndereco;

public class UCServicosEndereco {
    public UCServicosEndereco() {}

    public Endereco obterEnderecoPorID(long id) throws ServicoException {
        ColEndereco colend = new ColEndereco();
        Endereco endereco = new Endereco();
        try {
            endereco = colend.obterEnderecoPorID(id);
        } catch (Exception e) {
            throw new ServicoException("Não foi possível obter endereço por Id");
        }

        return endereco;
    }

}
