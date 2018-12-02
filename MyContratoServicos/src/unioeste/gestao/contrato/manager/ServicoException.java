package unioeste.gestao.contrato.manager;

public class ServicoException extends Exception {

    public ServicoException() { super("[SERVIÇO] "); }

    public ServicoException(String message) {
        super("[SERVIÇO] " + message);
    }

    public ServicoException(String message, Throwable cause) { super("[SERVIÇO] " + message, cause); }

    public ServicoException(Throwable cause) { super("[SERVIÇO]", cause); }
}
