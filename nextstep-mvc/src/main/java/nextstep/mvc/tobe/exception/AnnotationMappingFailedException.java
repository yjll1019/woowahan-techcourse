package nextstep.mvc.tobe.exception;

public class AnnotationMappingFailedException extends RuntimeException {
    public AnnotationMappingFailedException() {
        super("AnnotationMapping 처리를 실패했습니다.");
    }
}
