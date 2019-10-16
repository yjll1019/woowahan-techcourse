package nextstep.jdbc.exception;

public class UpdateQueryFailException extends RuntimeException {
    public UpdateQueryFailException() {
        super("Update 쿼리를 수행하는 데 실패했습니다.");
    }
}
