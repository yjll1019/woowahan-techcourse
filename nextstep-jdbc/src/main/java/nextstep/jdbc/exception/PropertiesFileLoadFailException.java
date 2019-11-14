package nextstep.jdbc.exception;

public class PropertiesFileLoadFailException extends RuntimeException {
    public PropertiesFileLoadFailException() {
        super("Properties 파일을 로드하는데 실패했습니다.");
    }
}