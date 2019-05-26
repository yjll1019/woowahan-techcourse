package com.woowacourse.coordinate.domain.figure;

import java.util.Arrays;

import com.woowacourse.coordinate.domain.coordinate.XCoordinate;
import com.woowacourse.coordinate.domain.coordinate.YCoordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {
	@Test
	void create() {
		Points points = new Points(Arrays.asList(new Point(new XCoordinate(5), new YCoordinate(10)),
				new Point(new XCoordinate(10), new YCoordinate(10)),
				new Point(new XCoordinate(5), new YCoordinate(8)), new Point(new XCoordinate(10), new YCoordinate(8))));
		assertThat(new Rectangle(points)).isEqualTo(new Rectangle(points));
	}

	@Test
	void invalidSizeOfPoints() {
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(1))))));
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(11)),
				new Point(new XCoordinate(5), new YCoordinate(10)), new Point(new XCoordinate(10), new YCoordinate(14))))));
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(1)),
				new Point(new XCoordinate(5), new YCoordinate(10)), new Point(new XCoordinate(10), new YCoordinate(14)),
				new Point(new XCoordinate(20), new YCoordinate(20)), new Point(new XCoordinate(21), new YCoordinate(24))))));
	}

	@Test
	void invalidIsParallel() {
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(5), new YCoordinate(10)),
				new Point(new XCoordinate(8), new YCoordinate(10)), new Point(new XCoordinate(3), new YCoordinate(8)),
				new Point(new XCoordinate(6), new YCoordinate(8))))));
	}

	@Test
	void duplicatePoints() {
		assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(5), new YCoordinate(10)),
				new Point(new XCoordinate(8), new YCoordinate(10)), new Point(new XCoordinate(3), new YCoordinate(8)),
				new Point(new XCoordinate(6), new YCoordinate(8))))));
	}

	@Test
	void calculateArea() {
		assertThat(new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(10), new YCoordinate(10)),
				new Point(new XCoordinate(22), new YCoordinate(10)), new Point(new XCoordinate(22), new YCoordinate(18)),
				new Point(new XCoordinate(10), new YCoordinate(18))))).calculateArea()).isEqualTo(96);
		assertThat(new Rectangle(new Points(Arrays.asList(new Point(new XCoordinate(6), new YCoordinate(3)),
				new Point(new XCoordinate(9), new YCoordinate(6)), new Point(new XCoordinate(3), new YCoordinate(6)),
				new Point(new XCoordinate(6), new YCoordinate(9))))).calculateArea()).isEqualTo(18, offset(0.01));
	}
}
