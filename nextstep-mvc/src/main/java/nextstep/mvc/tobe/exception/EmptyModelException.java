package nextstep.mvc.tobe.exception;

public class EmptyModelException extends RuntimeException {
    public EmptyModelException() {
        super("비어있는 model 입니다.");
    }
}
