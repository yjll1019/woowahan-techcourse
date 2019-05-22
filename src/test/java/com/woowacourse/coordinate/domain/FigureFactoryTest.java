package com.woowacourse.coordinate.domain;

import java.util.Arrays;
import java.util.List;

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
		List<Point> points = Arrays.asList(new Point(1,10), new Point(5, 11));
		assertThat(creator.create(points))
				.isEqualTo(new Line(points));
	}

	@Test
	void createTriangle() {
		List<Point> points = Arrays.asList(new Point(10, 10), new Point(14, 15), new Point(20, 8));
		assertThat(creator.create(points))
				.isEqualTo(new Triangle(Arrays.asList(new Point(10, 10), new Point(14, 15), new Point(20, 8))));
	}

	@Test
	void createSquare() {
		List<Point> points = Arrays.asList(new Point(10, 10), new Point(22, 10), new Point(22, 18), new Point(10, 18));
		assertThat(creator.create(points)).isEqualTo(new Square(points));
	}

}