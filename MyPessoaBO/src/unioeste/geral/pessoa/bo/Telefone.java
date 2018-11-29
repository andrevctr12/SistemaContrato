package unioeste.geral.pessoa.bo;

import java.io.Serializable;

public class Telefone implements Serializable {
    private Long id;
    private String telefone;
    private DDI ddi;
    private DDD ddd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public DDI getDdi() {
        return ddi;
    }

    public void setDdi(DDI ddi) {
        this.ddi = ddi;
    }

    public DDD getDdd() {
        return ddd;
    }

    public void setDdd(DDD ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", telefone='" + telefone + '\'' +
                ", ddi=" + ddi +
                ", ddd=" + ddd +
                '}';
    }
}
