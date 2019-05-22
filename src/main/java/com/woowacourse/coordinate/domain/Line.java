package com.woowacourse.coordinate.domain;

import java.util.List;
import java.util.Objects;

public class Line extends Figure {
	public static final int NUM_OF_POINTS = 2;

	public Line(List<Point> points) {
		super(points, NUM_OF_POINTS);
		this.points = points;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Line)) {
			return false;
		}
		Line line = (Line) o;
		return Objects.equals(points, line.points);
	}

	@Override
	public int hashCode() {
		return Objects.hash(points);
	}

	@Override
	public double calculateArea() {
		return points.get(0).calculateDistance(points.get(1));
	}
}
