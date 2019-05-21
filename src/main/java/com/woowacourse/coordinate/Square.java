package com.woowacourse.coordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Square {
	public static final int NUM_OF_POINTS = 4;

	private final List<Point> points;

	public Square(List<Point> points) {
		if (new HashSet<>(points).size() != NUM_OF_POINTS) {
			throw new IllegalArgumentException("Invalid size of points");
		}
		if (!isParallelWithAxis(points)) {
			throw new IllegalArgumentException("Invalid set of points");
		}
		this.points = points;
	}

	private boolean isParallelWithAxis(List<Point> points) {
		for (Point p : points) {
			if (filterMatchXPoints(points, p).size() != 2 ||
					filterMatchYPoints(points, p).size() != 2) {
				return false;
			}
		}

		return true;
	}

	private List<Point> filterMatchXPoints(List<Point> points, Point x) {
		return points.stream()
				.filter(p -> p.matchX(x))
				.collect(Collectors.toList());
	}

	private List<Point> filterMatchYPoints(List<Point> points, Point y) {
		return points.stream()
				.filter(p -> p.matchY(y))
				.collect(Collectors.toList());
	}

	public int calculateArea() {
		List<Point> filteredPoints = filterMatchXPoints(points, points.get(0));
		int d1 = (int) filteredPoints.get(0).calculateDistance(filteredPoints.get(1));
		filteredPoints = filterMatchYPoints(points, points.get(0));
		int d2 = (int) filteredPoints.get(0).calculateDistance(filteredPoints.get(1));
		return d1 * d2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Square)) {
			return false;
		}
		Square square = (Square) o;
		return Objects.equals(points, square.points);
	}

	@Override
	public int hashCode() {
		return Objects.hash(points);
	}

}
