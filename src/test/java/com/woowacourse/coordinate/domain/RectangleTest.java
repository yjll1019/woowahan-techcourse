package com.woowacourse.coordinate.domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(new Coordinate(5), new Coordinate(10)),
				new Point(new Coordinate(10), new Coordinate(10)),
				new Point(new Coordinate(5), new Coordinate(8)), new Point(new Coordinate(10), new Coordinate(8))));
		assertThat(new Rectangle(points)).isEqualTo(new Rectangle(points));
	}

	@Test
	void invalidSizeOfPoints() {
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(1))))));
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(11)),
				new Point(new Coordinate(5), new Coordinate(10)), new Point(new Coordinate(10), new Coordinate(14))))));
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(1)),
				new Point(new Coordinate(5), new Coordinate(10)), new Point(new Coordinate(10), new Coordinate(14)),
				new Point(new Coordinate(20), new Coordinate(20)), new Point(new Coordinate(21), new Coordinate(24))))));
	}

	@Test
	void invalidIsParallel() {
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(5), new Coordinate(10)),
				new Point(new Coordinate(8), new Coordinate(10)), new Point(new Coordinate(3), new Coordinate(8)),
				new Point(new Coordinate(6), new Coordinate(8))))));
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(5), new Coordinate(10)),
				new Point(new Coordinate(8), new Coordinate(10)), new Point(new Coordinate(3), new Coordinate(8)),
				new Point(new Coordinate(6), new Coordinate(8))))));
	}

	@Test
	void calculateArea() {
		assertThat(new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(10), new Coordinate(10)),
				new Point(new Coordinate(22), new Coordinate(10)), new Point(new Coordinate(22), new Coordinate(18)),
				new Point(new Coordinate(10), new Coordinate(18))))).calculateArea()).isEqualTo(96);
		assertThat(new Rectangle(new Points(Arrays.asList(new Point(new Coordinate(6), new Coordinate(3)),
				new Point(new Coordinate(9), new Coordinate(6)), new Point(new Coordinate(3), new Coordinate(6)),
				new Point(new Coordinate(6), new Coordinate(9))))).calculateArea()).isEqualTo(18, offset(0.01));
	}
}
