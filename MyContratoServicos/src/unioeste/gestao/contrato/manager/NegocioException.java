package unioeste.gestao.contrato.manager;

public class NegocioException extends Exception {

    public NegocioException() { super("[NEGÓCIO] "); }

    public NegocioException(String message) {
        super("[NEGÓCIO] " + message);
    }

    public NegocioException(String message, Throwable cause) { super("[NEGÓCIO] " + message, cause); }

    public NegocioException(Throwable cause) { super("[NEGÓCIO] ", cause); }
}
