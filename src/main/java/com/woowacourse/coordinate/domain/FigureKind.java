package com.woowacourse.coordinate.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FigureKind {
	LINE(Line.NUM_OF_POINTS, Line::new),
	TRIANGLE(Triangle.NUM_OF_POINTS, Triangle::new),
	SQUARE(Rectangle.NUM_OF_POINTS, Rectangle::new);

	private int numOfPoints;
	private Function<Points, Figure> mapper;

	FigureKind(int numOfPoints, Function<Points, Figure> shapeMapper) {
		this.numOfPoints = numOfPoints;
		this.mapper = shapeMapper;
	}

	public Figure mapToShape(Points points) {
		return mapper.apply(points);
	}

	public static FigureKind valueOf(int numOfPoints) {
		return Arrays.stream(values())
				.filter(figureKind -> figureKind.numOfPoints == numOfPoints)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("일치하는 Figure 종류가 없습니다."))
				;
	}
}
