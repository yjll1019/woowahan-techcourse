package com.woowacourse.coordinate.domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(3, 3), new Point(6, 3)));
		assertThat(new Line(points)).isEqualTo(new Line(points));
	}

	@Test
	void calculateLength() {
		assertThat(new Line(new Points(Arrays.asList(new Point(3, 3), new Point(6, 3))))
				.calculateArea()).isEqualTo(3);
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> new Line(new Points(Arrays.asList(new Point(5,
				10), new Point(5, 10)))));
	}
}
