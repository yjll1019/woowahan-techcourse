package slipp.controller.api.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("해당 userId가 존재하지 않습니다.");
    }
}
