package unioeste.contrato.contrato.bo;

public class TipoContrato {
    private Long id;
    private String nomeTipo;

    @Override
    public String toString() {
        return nomeTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
