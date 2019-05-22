package com.woowacourse.coordinate;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {
	@Test
	void create() {
		assertThat(new Triangle(Arrays.asList(new Point(10, 10), new Point(14,15),
				new Point(20, 8)))).isEqualTo(new Triangle(Arrays.asList(new Point(10, 10), new Point(14,15),
				new Point(20, 8))));
	}

	@Test
	void pointsOnSameLine() {
		assertThrows(IllegalArgumentException.class, () -> new Triangle(Arrays.asList(new Point(3, 10), new Point(5,10),
				new Point(20, 10))));
		assertThrows(IllegalArgumentException.class, () -> new Triangle(Arrays.asList(new Point(1, 1), new Point(5,2),
				new Point(9, 3))));
		assertThrows(IllegalArgumentException.class, () -> new Triangle(Arrays.asList(new Point(1, 1)
				, new Point(1,5), new Point(1, 9))));
	}

	@Test
	void calculateArea() {
		assertThat(new Triangle(Arrays.asList(new Point(10, 10), new Point(14,15),
				new Point(20, 8))).calculateArea()).isEqualTo(29.0, offset(0.01));
		assertThat(new Triangle(Arrays.asList(new Point(15, 20), new Point(15,10),
				new Point(7, 10))).calculateArea()).isEqualTo(40.0, offset(0.01));
	}
}
