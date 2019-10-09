package nextstep.mvc.tobe.scanner;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerScannerTest {

    @Test
    void scanController() {
        Set<Class<?>> controllers = ControllerScanner.scanController("nextstep.mvc.tobe");
        assertThat(controllers.size()).isEqualTo(1);
    }
}