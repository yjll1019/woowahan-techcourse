package com.woowacourse.coordinate.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FigureKind {
	LINE(Line.NUM_OF_POINTS, Line::new),
	TRIANGLE(Triangle.NUM_OF_POINTS, Triangle::new),
	SQUARE(Square.NUM_OF_POINTS, Square::new);

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
		List<FigureKind> filtered = Arrays.stream(values())
				.filter(k -> k.numOfPoints == numOfPoints)
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			throw new IllegalArgumentException("일치하는 Figure 종류가 없습니다.");
		}
		return filtered.get(0);
	}
}
