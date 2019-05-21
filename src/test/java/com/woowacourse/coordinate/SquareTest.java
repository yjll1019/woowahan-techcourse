package com.woowacourse.coordinate;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareTest {
	@Test
	void create() {
		assertThat(new Square(Arrays.asList(new Point(5, 10), new Point(10,10),
				new Point(5, 8), new Point(10, 8))))
				.isEqualTo(new Square(Arrays.asList(new Point(5, 10), new Point(10,10),
						new Point(5, 8), new Point(10, 8))));
	}


	@Test
	void invalidSizeOfPoints() {
		assertThrows(IllegalArgumentException.class, () -> new Square(Arrays.asList(new Point(1, 1))));
		assertThrows(IllegalArgumentException.class, () -> new Square(Arrays.asList(new Point(1, 1)
				, new Point(5,10), new Point(10, 14))));
		assertThrows(IllegalArgumentException.class, () -> new Square(Arrays.asList(new Point(1, 1)
				, new Point(5,10), new Point(10, 14), new Point(20, 20), new Point(21, 24))));
	}

	@Test
	void invalidIsParallel() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Square(Arrays.asList(new Point(5, 10), new Point(8, 10), new Point(3, 8), new Point(6, 8)));
		});
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Square(Arrays.asList(new Point(5, 10), new Point(5, 10), new Point(3, 8), new Point(3, 8)));
		});
	}

	@Test
	void calculateArea() {
		assertThat(new Square(Arrays.asList(new Point(10, 10), new Point(22,10),
				new Point(22, 18), new Point(10, 18))).calculateArea()).isEqualTo(96);
	}
}
