package com.woowacourse.coordinate.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square extends Figure {
	public static final int NUM_OF_POINTS = 4;

	public Square(List<Point> points) {
		super(points, NUM_OF_POINTS);
		checkIfSquare(points);
		this.points = points;
	}

	private void checkIfSquare(List<Point> points) {
		Point maxDistancePoint = getLongestPoint(points);

		for(Point p : points) {
			checkAngle(points, maxDistancePoint, p);
		}
	}

	private void checkAngle(List<Point> points, Point maxDistancePoint, Point p) {
		if (p.equals(points.get(0)) || p.equals(maxDistancePoint)) {
			return;
		}
		double angle = p.calculateAngle(points.get(0), maxDistancePoint);
		if (angle != 90) {
			throw new IllegalArgumentException("사각형이 아닙니다.");
		}
	}

	private Point getLongestPoint(List<Point> points) {
		Point maxDistancePoint = points.get(1);
		for (int i = 2; i < points.size(); i++) {
			maxDistancePoint = getLongerPoint(points, maxDistancePoint, points.get(i));
		}
		return maxDistancePoint;
	}

	private Point getLongerPoint(List<Point> points, Point p1, Point p2) {
		if (points.get(0).calculateDistance(p2) > points.get(0).calculateDistance(p1)) {
			return p2;
		}
		return p1;
	}

	@Override
	public double calculateArea() {
		Point maxDistancePoint = getLongestPoint(points);
		List<Point> nearPoints = new ArrayList<>(points);
		nearPoints.remove(maxDistancePoint);
		return nearPoints.get(0).calculateDistance(nearPoints.get(1)) * nearPoints.get(0).calculateDistance(nearPoints.get(2));
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

	@Override
	public String toString() {
		return "사각형";
	}

}
