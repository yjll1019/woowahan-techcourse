package com.woowacourse.coordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Line {
	public static final int NUM_OF_POINTS = 2;

	private final List<Point> points;

	public Line(List<Point> points) {
		if(new HashSet<>(points).size() != NUM_OF_POINTS) {
			throw new PointDuplicateException();
		}
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

	public double calculateLength() {
		return points.get(0).calculateDistance(points.get(1));
	}
}
