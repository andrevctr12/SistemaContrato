package unioeste.geral.pessoa.bo;

import java.io.Serializable;

public class DDD implements Serializable {
    private int ddd;

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "DDD{" +
                "ddd=" + ddd +
                '}';
    }
}
