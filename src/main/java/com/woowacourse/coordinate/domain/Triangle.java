package com.woowacourse.coordinate.domain;

import java.util.List;
import java.util.Objects;

public class Triangle extends Figure {
	public static final int NUM_OF_POINTS = 3;
	private static final int FIRST_POINT_OF_TRIANGLE = 0;
	private static final int SECOND_POINT_OF_TRIANGLE = 1;
	private static final int THIRD_POINT_OF_TRIANGLE = 2;
	private static final int SLOPE_IS_ZERO = 0;

	public Triangle(Points points) {
		super(points);
		checkIfValidTriangle(points.getPoints());
		this.points = points;
	}

	private void checkIfValidTriangle(List<Point> points) {
		double maybeSlope1 = getSlope(points, SECOND_POINT_OF_TRIANGLE);
		double maybeSlope2 = getSlope(points, THIRD_POINT_OF_TRIANGLE);

		if (maybeSlope1 == maybeSlope2) {
			throw new IllegalArgumentException("삼각형을 만들 수 없는 좌표입니다.");
		}
	}

	private double getSlope(List<Point> points, int secondPointOfTriangle) {
		try {
			return points.get(FIRST_POINT_OF_TRIANGLE).calculateSlope(points.get(secondPointOfTriangle));
		} catch (Exception e) {
			return SLOPE_IS_ZERO;
		}
	}


	@Override
	public double calculateArea() {
		double lengthA = points.getPoints().get(FIRST_POINT_OF_TRIANGLE).calculateDistance(points.getPoints().get(SECOND_POINT_OF_TRIANGLE));
		double lengthB = points.getPoints().get(SECOND_POINT_OF_TRIANGLE).calculateDistance(points.getPoints().get(THIRD_POINT_OF_TRIANGLE));
		double lengthC = points.getPoints().get(THIRD_POINT_OF_TRIANGLE).calculateDistance(points.getPoints().get(FIRST_POINT_OF_TRIANGLE));
		double s = (lengthA + lengthB + lengthC) / 2;
		return Math.sqrt(s * (s - lengthA) * (s - lengthB) * (s - lengthC));
	}

	@Override
	public String getCalculateMessage() {
		return String.format("삼각형의 넓이는 %.1f", calculateArea());
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
}
