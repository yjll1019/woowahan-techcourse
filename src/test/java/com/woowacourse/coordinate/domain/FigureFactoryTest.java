package com.woowacourse.coordinate.domain;

import java.util.Arrays;
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
		Points points = new Points(Arrays.asList(new Point(new Coordinate(1), new Coordinate(10)), new Point(new Coordinate(5), new Coordinate(11))));
		assertThat(creator.create(points)).isEqualTo(new Line(points));
	}

	@Test
	void createTriangle() {
		Points points = new Points(Arrays.asList(new Point(new Coordinate(10), new Coordinate(10)),
				new Point(new Coordinate(14), new Coordinate(15)), new Point(new Coordinate(20), new Coordinate(8))));
		assertThat(creator.create(points)).isEqualTo(new Triangle(points));
	}

	@Test
	void createRectangle() {
		Points points = new Points(Arrays.asList(new Point(new Coordinate(10), new Coordinate(10)),
				new Point(new Coordinate(22), new Coordinate(10)), new Point(new Coordinate(22), new Coordinate(18)),
				new Point(new Coordinate(10), new Coordinate(18))));
		assertThat(creator.create(points)).isEqualTo(new Rectangle(points));
	}

}