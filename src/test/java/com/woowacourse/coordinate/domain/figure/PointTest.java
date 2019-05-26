package com.woowacourse.coordinate.domain.figure;

import com.woowacourse.coordinate.domain.coordinate.XCoordinate;
import com.woowacourse.coordinate.domain.coordinate.YCoordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PointTest {
	@Test
	void creat() {
		Point point = new Point(new XCoordinate(10), new YCoordinate(10));
		assertThat(point).isEqualTo(new Point(new XCoordinate(10), new YCoordinate(10)));
	}

	@Test
	void calculateDistance() {
		assertThat(new Point(new XCoordinate(1), new YCoordinate(1))
				.calculateDistance(new Point(new XCoordinate(1), new YCoordinate(3)))).isEqualTo(2);
		assertThat(new Point(new XCoordinate(1), new YCoordinate(1))
				.calculateDistance(new Point(new XCoordinate(2), new YCoordinate(2))))
				.isEqualTo(1.414, offset(0.01));
	}

	@Test
	void calculateSlope() {
		Point point = new Point(new XCoordinate(1), new YCoordinate(1));
		assertThat(point.calculateSlope(new Point(new XCoordinate(3), new YCoordinate(3))).get()).isEqualTo(1);
		assertThat(point.calculateSlope(new Point(new XCoordinate(5), new YCoordinate(2))).get()).isEqualTo(0.25, offset(0.01));
		assertThat(point.calculateSlope(new Point(new XCoordinate(5), new YCoordinate(1))).get()).isEqualTo(0);
	}

	@Test
	void calculateAngle() {
		assertThat(new Point(new XCoordinate(6), new YCoordinate(3))
				.calculateAngle(new Point(new XCoordinate(9), new YCoordinate(6)), new Point(new XCoordinate(3),
						new YCoordinate(6)))).isEqualTo(90);
		assertThat(new Point(new XCoordinate(1), new YCoordinate(1))
				.calculateAngle(new Point(new XCoordinate(1), new YCoordinate(5)), new Point(new XCoordinate(7),
						new YCoordinate(1)))).isEqualTo(90);
	}
}
