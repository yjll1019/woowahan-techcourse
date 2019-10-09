package nextstep.mvc.tobe.exception;

public class ManualMappingFailedException extends RuntimeException {
    public ManualMappingFailedException() {
        super("ManualMapping 처리를 실패했습니다.");
    }
}
