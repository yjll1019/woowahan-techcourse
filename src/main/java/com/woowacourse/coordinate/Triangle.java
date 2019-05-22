package com.woowacourse.coordinate;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Triangle extends Figure {
	public static final int NUM_OF_POINTS = 3;

	private List<Point> points;

	public Triangle(List<Point> points) {
		super(points, NUM_OF_POINTS);

		checkIfValidTriangle(points);
		this.points = points;
	}

	private void checkIfValidTriangle(List<Point> points) {
		Optional<Double> maybeSlope1 = points.get(0).calculateSlope(points.get(1));
		Optional<Double> maybeSlope2 = points.get(0).calculateSlope(points.get(2));

		if (maybeSlope1.isPresent() && maybeSlope2.isPresent()) {
			double slope1 = maybeSlope1.get();
			double slope2 = maybeSlope2.get();
			throwIfSlopeEqual(slope1, slope2);
		}

		if (!maybeSlope1.isPresent() && !maybeSlope2.isPresent()) {
			throw new IllegalArgumentException("Three points are on same line");
		}

	}

	private void throwIfSlopeEqual(double slope1, double slope2) {
		if (slope1 == slope2) {
			throw new IllegalArgumentException("Three points are on same line");
		}
	}

	@Override
	public double calculateArea() {
		double lengthA = points.get(0).calculateDistance(points.get(1));
		double lengthB = points.get(1).calculateDistance(points.get(2));
		double lengthC = points.get(2).calculateDistance(points.get(0));
		double s = (lengthA + lengthB + lengthC) / 2;
		return Math.sqrt(s * (s - lengthA) * (s - lengthB) * (s - lengthC));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Triangle)) {
			return false;
		}
		final Triangle triangle = (Triangle) o;
		return Objects.equals(points, triangle.points);
	}

	@Override
	public int hashCode() {
		return Objects.hash(points);
	}

	@Override
	public String toString() {
		return "삼각형";
	}
}
