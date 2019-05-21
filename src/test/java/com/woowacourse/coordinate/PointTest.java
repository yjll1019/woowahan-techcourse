package com.woowacourse.coordinate;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointTest {
	@Test
	void creat() {
		Point point = new Point(10, 10);
		assertThat(point).isEqualTo(new Point(10, 10));
	}

	@Test
	void createWithString() {
		assertThat(new Point("(4, 10)")).isEqualTo(new Point(4, 10));
		assertThat(new Point("(4 , 10)")).isEqualTo(new Point(4, 10));
		assertThat(new Point("( 4 , 10 )")).isEqualTo(new Point(4, 10));
	}

	@Test
	void createWithPair() {
		assertThat(Point.createWithPair("(4, 10)-(13,13)")).containsExactly(new Point(4, 10), new Point(13, 13));
		assertThat(Point.createWithPair("(4, 10) - (13,13)")).containsExactly(new Point(4, 10), new Point(13, 13));
		assertThat(Point.createWithPair("(4, 10) - ( 13,13 )")).containsExactly(new Point(4, 10), new Point(13, 13));
		assertThat(Point.createWithPair("(10, 10) - ( 22,10 )-(22, 18)-(10, 18)"))
				.containsExactly(new Point(10, 10), new Point(22, 10), new Point(22, 18), new Point(10, 18));
	}

	@Test
	void validateInvalidString() {
		assertThrows(IllegalArgumentException.class, () -> new Point("4,10"));
	}

	@Test
	void validateRange() {
		assertThrows(IllegalArgumentException.class, () -> new Point(-1, 10));
		assertThrows(IllegalArgumentException.class, () -> new Point(10, -1));
		assertThrows(IllegalArgumentException.class, () -> new Point(5, 25));
		assertThrows(IllegalArgumentException.class, () -> new Point(25, 5));
	}

	@Test
	void calculateDistance() {
		assertThat(new Point(1, 1).calculateDistance(new Point(1, 3))).isEqualTo(2);
		assertThat(new Point(1, 1).calculateDistance(new Point(2, 2))).isEqualTo(1.414, offset(0.01));
	}


}
