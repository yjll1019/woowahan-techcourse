package com.woowacourse.coordinate.domain.coordinate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XCoordinateTest {
	@Test
	void create() {
		assertThat(new XCoordinate(13)).isEqualTo(new XCoordinate(13));
	}

	@Test
	void getX() {
		assertThat(new XCoordinate(13).getCoordinate()).isEqualTo(13);
	}

	@Test
	void validateInvalidNumber() {
		assertThrows(IllegalArgumentException.class, () -> new XCoordinate(-1));
		assertThrows(IllegalArgumentException.class, () -> new XCoordinate(25));
	}
}
