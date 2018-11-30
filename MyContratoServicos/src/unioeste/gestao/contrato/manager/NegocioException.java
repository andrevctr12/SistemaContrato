package unioeste.gestao.contrato.manager;

public class NegocioException extends Exception {

    public NegocioException() { super(); }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) { super(message, cause); }

    public NegocioException(Throwable cause) { super(cause); }
}
