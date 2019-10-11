package nextstep.mvc.tobe.exception;

public class NotFoundModelException extends RuntimeException {
    public NotFoundModelException() {
        super("model");
    }
}
