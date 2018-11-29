package unioeste.geral.pessoa.bo;

import java.io.Serializable;

public class DDI implements Serializable {
    private int ddi;

    public int getDdi() {
        return ddi;
    }

    public void setDdi(int ddi) {
        this.ddi = ddi;
    }

    @Override
    public String toString() {
        return "DDI{" +
                "ddi=" + ddi +
                '}';
    }
}
