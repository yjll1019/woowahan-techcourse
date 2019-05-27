package com.woowacourse.coordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateTest {
	@Test
	void create() {
		assertThat(new Coordinate(13)).isEqualTo((new Coordinate(13)));
	}

	@Test
	void getX() {
		assertThat(new Coordinate(13).getCoordinate()).isEqualTo(13);
	}

	@Test
	void validateInvalidNumber() {
		assertThrows(IllegalArgumentException.class, () -> new Coordinate(-1));
		assertThrows(IllegalArgumentException.class, () -> new Coordinate(25));
	}
}
