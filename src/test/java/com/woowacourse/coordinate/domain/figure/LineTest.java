package com.woowacourse.coordinate.domain.figure;

import java.util.Arrays;

import com.woowacourse.coordinate.domain.coordinate.XCoordinate;
import com.woowacourse.coordinate.domain.coordinate.YCoordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(new XCoordinate(3), new YCoordinate(3)),
				new Point(new XCoordinate(6), new YCoordinate(3))));
		assertThat(new Line(points)).isEqualTo(new Line(points));
	}

	@Test
	void invalidSizeOfPoints() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Line(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(1)))));
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Line(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(1)),
					new Point(new XCoordinate(5), new YCoordinate(10)),
					new Point(new XCoordinate(10), new YCoordinate(14)))));
		});
	}

	@Test
	void calculateLength() {
		assertThat(new Line(new Points(Arrays.asList(new Point(new XCoordinate(3), new YCoordinate(3)),
				new Point(new XCoordinate(6), new YCoordinate(3)))))
				.calculateArea()).isEqualTo(3);
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> new Line(new Points(Arrays.asList(new Point(new XCoordinate(5),
				new YCoordinate(10)), new Point(new XCoordinate(5), new YCoordinate(10))))));
	}
}
