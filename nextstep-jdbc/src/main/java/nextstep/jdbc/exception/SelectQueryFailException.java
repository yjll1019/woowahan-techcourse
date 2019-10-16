package nextstep.jdbc.exception;

public class SelectQueryFailException extends RuntimeException {
    public SelectQueryFailException() {
        super("Select 쿼리를 수행하는 데 실패했습니다.");
    }
}
