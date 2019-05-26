package com.woowacourse.coordinate.domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(5, 10), new Point(10, 10),
				new Point(5, 8), new Point(10, 8)));
		assertThat(new Square(points)).isEqualTo(new Square(points));
	}

	@Test
	void invalidSizeOfPoints() {
		assertThrows(IllegalArgumentException.class, () -> new Square(new Points(Arrays.asList(new Point(1, 1)))));
		assertThrows(IllegalArgumentException.class, () -> new Square(new Points(Arrays.asList(new Point(1, 1)
				, new Point(5, 10), new Point(10, 14)))));
		assertThrows(IllegalArgumentException.class, () -> new Square(new Points(Arrays.asList(new Point(1, 1)
				, new Point(5, 10), new Point(10, 14), new Point(20, 20), new Point(21, 24)))));
	}

	@Test
	void invalidIsParallel() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Square(new Points(Arrays.asList(new Point(5, 10), new Point(8, 10),
					new Point(3, 8), new Point(6, 8))));
		});
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Square(new Points(Arrays.asList(new Point(5, 10), new Point(5, 10),
					new Point(3, 8), new Point(3, 8))));
		});
	}

	@Test
	void calculateArea() {
		assertThat(new Square(new Points(Arrays.asList(new Point(10, 10), new Point(22, 10),
				new Point(22, 18), new Point(10, 18)))).calculateArea()).isEqualTo(96);
		assertThat(new Square(new Points(Arrays.asList(new Point(6, 3), new Point(9, 6),
				new Point(3, 6), new Point(6, 9)))).calculateArea()).isEqualTo(18, offset(0.01));
	}
}
