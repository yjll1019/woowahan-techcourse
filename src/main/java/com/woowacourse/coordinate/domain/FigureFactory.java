package com.woowacourse.coordinate.domain;

public class FigureFactory implements FigureCreator {
	@Override
	public Figure create(Points points) {
		return FigureKind.valueOf(points.getPoints().size()).mapToShape(points);
	}
}
