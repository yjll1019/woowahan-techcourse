package com.woowacourse.coordinate.domain.figure;

import java.util.Arrays;

import com.woowacourse.coordinate.domain.coordinate.XCoordinate;
import com.woowacourse.coordinate.domain.coordinate.YCoordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(new XCoordinate(10), new YCoordinate(10)),
				new Point(new XCoordinate(14), new YCoordinate(15)), new Point(new XCoordinate(20), new YCoordinate(8))));
		assertThat(new Triangle(points)).isEqualTo(new Triangle(points));
	}

	@Test
	void pointsOnSameLine() {
		assertThrows(IllegalArgumentException.class, () -> new Triangle(new Points(Arrays.asList(new Point(new XCoordinate(3), new YCoordinate(10)),
				new Point(new XCoordinate(5), new YCoordinate(10)), new Point(new XCoordinate(20), new YCoordinate(10))))));
		assertThrows(IllegalArgumentException.class, () -> new Triangle(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(1)),
				new Point(new XCoordinate(5), new YCoordinate(2)), new Point(new XCoordinate(9), new YCoordinate(3))))));
		assertThrows(IllegalArgumentException.class, () -> new Triangle(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(1)),
				new Point(new XCoordinate(1), new YCoordinate(5)), new Point(new XCoordinate(1), new YCoordinate(9))))));
	}

	@Test
	void calculateArea() {
		assertThat(new Triangle(new Points(Arrays.asList(new Point(new XCoordinate(10), new YCoordinate(10)), new Point(new XCoordinate(14), new YCoordinate(15)),
				new Point(new XCoordinate(20), new YCoordinate(8))))).calculateArea()).isEqualTo(29.0, offset(0.01));
		assertThat(new Triangle(new Points(Arrays.asList(new Point(new XCoordinate(15), new YCoordinate(20)), new Point(new XCoordinate(15), new YCoordinate(10)),
				new Point(new XCoordinate(7), new YCoordinate(10))))).calculateArea()).isEqualTo(40.0, offset(0.01));
	}
}
