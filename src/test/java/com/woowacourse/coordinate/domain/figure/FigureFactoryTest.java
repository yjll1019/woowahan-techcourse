package com.woowacourse.coordinate.domain.figure;

import java.util.Arrays;

import com.woowacourse.coordinate.domain.coordinate.XCoordinate;
import com.woowacourse.coordinate.domain.coordinate.YCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FigureFactoryTest {
	private FigureCreator creator;

	@BeforeEach
	void initCreator() {
		creator = new FigureFactory();
	}

	@Test
	void createLine() {
		Points points = new Points(Arrays.asList(new Point(new XCoordinate(1), new YCoordinate(10)), new Point(new XCoordinate(5), new YCoordinate(11))));
		assertThat(creator.create(points)).isEqualTo(new Line(points));
	}

	@Test
	void createTriangle() {
		Points points = new Points(Arrays.asList(new Point(new XCoordinate(10), new YCoordinate(10)),
				new Point(new XCoordinate(14), new YCoordinate(15)), new Point(new XCoordinate(20), new YCoordinate(8))));
		assertThat(creator.create(points)).isEqualTo(new Triangle(points));
	}

	@Test
	void createRectangle() {
		Points points = new Points(Arrays.asList(new Point(new XCoordinate(10), new YCoordinate(10)),
				new Point(new XCoordinate(22), new YCoordinate(10)), new Point(new XCoordinate(22), new YCoordinate(18)),
				new Point(new XCoordinate(10), new YCoordinate(18))));
		assertThat(creator.create(points)).isEqualTo(new Rectangle(points));
	}

}