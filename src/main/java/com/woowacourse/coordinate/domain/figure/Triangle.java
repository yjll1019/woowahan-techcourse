package com.woowacourse.coordinate.domain.figure;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Triangle extends Figure {
	public static final int NUM_OF_POINTS = 3;
	private static final int FIRST_POINT_OF_TRIANGLE = 0;
	private static final int SECOND_POINT_OF_TRIANGLE = 1;
	private static final int THIRD_POINT_OF_TRIANGLE = 2;

	public Triangle(Points points) {
		super(points, NUM_OF_POINTS);
		checkIfValidTriangle(points.getPoints());
		this.points = points;
	}

	private void checkIfValidTriangle(List<Point> points) {
		double maybeSlope1 = points.get(FIRST_POINT_OF_TRIANGLE).calculateSlope(points.get(SECOND_POINT_OF_TRIANGLE));
		double maybeSlope2 = points.get(FIRST_POINT_OF_TRIANGLE).calculateSlope(points.get(THIRD_POINT_OF_TRIANGLE));

		if (maybeSlope1 == maybeSlope2) {
			throw new IllegalArgumentException("삼각형을 만들 수 없는 좌표입니다.");
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
