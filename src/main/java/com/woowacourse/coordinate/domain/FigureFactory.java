package com.woowacourse.coordinate.domain;

import java.util.List;

public class FigureFactory implements FigureCreator {

	@Override
	public Figure create(List<Point> points) {
		return FigureKind.valueOf(points.size()).mapToShape(points);
	}
}
