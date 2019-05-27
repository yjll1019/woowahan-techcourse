package com.woowacourse.coordinate.domain;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(new Coordinate(3), new Coordinate(3)),
				new Point(new Coordinate(6), new Coordinate(3))));
		assertThat(new Line(points)).isEqualTo(new Line(points));
	}

	@Test
	void invalidSizeOfPoints() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Line(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(1)))));
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Line(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(1)),
					new Point(new Coordinate(5), new Coordinate(10)),
					new Point(new Coordinate(10), new Coordinate(14)))));
		});
	}

	@Test
	void calculateLength() {
		assertThat(new Line(new Points(Arrays.asList(new Point(new Coordinate(3), new Coordinate(3)),
				new Point(new Coordinate(6), new Coordinate(3)))))
				.calculateArea()).isEqualTo(3);
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> new Line(new Points(Arrays.asList(new Point(new Coordinate(5),
				new Coordinate(10)), new Point(new Coordinate(5), new Coordinate(10))))));
	}
}
