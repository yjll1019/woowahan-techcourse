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
		Points points = new Points(Arrays.asList(new Point((1), (10)), new Point((5), (11))));
		assertThat(creator.create(points)).isEqualTo(new Line(points));
	}

	@Test
	void createTriangle() {
		Points points = new Points(Arrays.asList(new Point((10), (10)),
				new Point((14), (15)), new Point((20), (8))));
		assertThat(creator.create(points)).isEqualTo(new Triangle(points));
	}

	@Test
	void createRectangle() {
		Points points = new Points(Arrays.asList(new Point((10), (10)),
				new Point((22), (10)), new Point((22), (18)),
				new Point((10), (18))));
		assertThat(creator.create(points)).isEqualTo(new Rectangle(points));
	}

}