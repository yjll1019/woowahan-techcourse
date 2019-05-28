package com.woowacourse.coordinate.domain;

import java.util.Objects;

public class Line extends Figure {
	public static final int NUM_OF_POINTS = 2;
	private static final int FIRST_POINT_OF_LINE = 0;
	private static final int SECOND_POINT_OF_LINE = 1;

	public Line(Points points) {
		super(points);
		this.points = points;
	}

	@Override
	public double calculateArea() {
		return points.getPoints().get(FIRST_POINT_OF_LINE).calculateDistance(points.getPoints().get(SECOND_POINT_OF_LINE));
	}

	@Override
	public String getCalculateMessage() {
		return String.format("두 점 사이의 거리는 %f", calculateArea());
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
}
