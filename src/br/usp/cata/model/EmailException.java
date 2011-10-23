package br.usp.cata.model;

public class EmailException extends Exception {

    private static final long serialVersionUID = 943210483745021L;

    public EmailException() {
        super();
    }

    public EmailException(final Throwable t) {
        super(t);
    }

    public EmailException(final String msg, final Throwable t) {
        super(msg, t);
    }

}
