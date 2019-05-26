package com.woowacourse.coordinate.domain.coordinate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YCoordinateTest {
	@Test
	void create() {
		assertThat(new YCoordinate(13)).isEqualTo(new YCoordinate(13));
	}

	@Test
	void getX() {
		assertThat(new YCoordinate(13).getCoordinate()).isEqualTo(13);
	}

	@Test
	void validateInvalidNumber() {
		assertThrows(IllegalArgumentException.class, () -> new YCoordinate(-1));
		assertThrows(IllegalArgumentException.class, () -> new YCoordinate(25));
	}
}
