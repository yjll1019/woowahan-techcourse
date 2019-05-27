package com.woowacourse.coordinate.domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(new Coordinate(10), new Coordinate(10)),
				new Point(new Coordinate(14), new Coordinate(15)), new Point(new Coordinate(20), new Coordinate(8))));
		assertThat(new Triangle(points)).isEqualTo(new Triangle(points));
	}

	@Test
	void pointsOnSameLine() {
		assertThrows(IllegalArgumentException.class, () -> new Triangle(new Points(Arrays.asList(new Point(new Coordinate(3), new Coordinate(10)),
				new Point(new Coordinate(5), new Coordinate(10)), new Point(new Coordinate(20), new Coordinate(10))))));
		assertThrows(IllegalArgumentException.class, () -> new Triangle(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(1)),
				new Point(new Coordinate(5), new Coordinate(2)), new Point(new Coordinate(9), new Coordinate(3))))));
		assertThrows(IllegalArgumentException.class, () -> new Triangle(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(1)),
				new Point(new Coordinate(1), new Coordinate(5)), new Point(new Coordinate(1), new Coordinate(9))))));
	}

	@Test
	void calculateArea() {
		assertThat(new Triangle(new Points(Arrays.asList(new Point(new Coordinate(10), new Coordinate(10)), new Point(new Coordinate(14), new Coordinate(15)),
				new Point(new Coordinate(20), new Coordinate(8))))).calculateArea()).isEqualTo(29.0, offset(0.01));
		assertThat(new Triangle(new Points(Arrays.asList(new Point(new Coordinate(15), new Coordinate(20)), new Point(new Coordinate(15), new Coordinate(10)),
				new Point(new Coordinate(7), new Coordinate(10))))).calculateArea()).isEqualTo(40.0, offset(0.01));
	}
}
