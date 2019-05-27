package com.woowacourse.coordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class PointTest {
	@Test
	void creat() {
		Point point = new Point(new Coordinate(10), new Coordinate(10));
		assertThat(point).isEqualTo(new Point(new Coordinate(10), new Coordinate(10)));
	}

	@Test
	void calculateDistance() {
		assertThat(new Point(new Coordinate(1), new Coordinate(1))
				.calculateDistance(new Point(new Coordinate(1), new Coordinate(3)))).isEqualTo(2);
		assertThat(new Point(new Coordinate(1), new Coordinate(1))
				.calculateDistance(new Point(new Coordinate(2), new Coordinate(2))))
				.isEqualTo(1.414, offset(0.01));
	}

	@Test
	void calculateSlope() {
		Point point = new Point(new Coordinate(1), new Coordinate(1));
		assertThat(point.calculateSlope(new Point(new Coordinate(3), new Coordinate(3)))).isEqualTo(1);
		assertThat(point.calculateSlope(new Point(new Coordinate(5), new Coordinate(2)))).isEqualTo(0.25, offset(0.01));
		assertThat(point.calculateSlope(new Point(new Coordinate(5), new Coordinate(1)))).isEqualTo(0);
	}

	@Test
	void calculateAngle() {
		assertThat(new Point(new Coordinate(6), new Coordinate(3))
				.calculateAngle(new Point(new Coordinate(9), new Coordinate(6)), new Point(new Coordinate(3),
						new Coordinate(6)))).isEqualTo(90);
		assertThat(new Point(new Coordinate(1), new Coordinate(1))
				.calculateAngle(new Point(new Coordinate(1), new Coordinate(5)), new Point(new Coordinate(7),
						new Coordinate(1)))).isEqualTo(90);
	}
}
