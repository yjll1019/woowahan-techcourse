package com.woowacourse.coordinate.utils;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringSeparatorTest {
	@Test
	void separateString() {
		assertThat(StringSeparator.separateString("( 1 , 3 ) - ( 2 , 3 )")).isEqualTo(Arrays.asList("(1,3)", "(2,3)"));
		assertThat(StringSeparator.separateString("(1,3)-(2,3)")).isEqualTo(Arrays.asList("(1,3)", "(2,3)"));
	}

	@Test
	void separateCoordinate() {
		assertThat(StringSeparator.separateCoordinate("(1,2)")).isEqualTo(Arrays.asList(1, 2));
	}

	@Test
	void validateInvalidString() {
		assertThrows(IllegalArgumentException.class, () -> StringSeparator.separateCoordinate("4,10"));
	}

	@Test
	void validateInvalidRange() {
		assertThrows(IllegalArgumentException.class, () -> StringSeparator.separateCoordinate("(-4,10)"));
		assertThrows(IllegalArgumentException.class, () -> StringSeparator.separateCoordinate("(-4,-10)"));
		assertThrows(IllegalArgumentException.class, () -> StringSeparator.separateCoordinate("(4,-10)"));
	}
}