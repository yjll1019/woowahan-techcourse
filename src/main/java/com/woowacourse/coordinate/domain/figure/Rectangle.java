package com.woowacourse.coordinate.domain.figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rectangle extends Figure {
	public static final int NUM_OF_POINTS = 4;
	private static final int ANGLE_OF_RECTANGLE = 90;

	public Rectangle(Points points) {
		super(points, NUM_OF_POINTS);
		checkIfSquare(points.getPoints());
		this.points = points;
	}

	private void checkIfSquare(List<Point> points) {
		Point maxDistancePoint = getLongestPoint(points);

		for (Point p : points) {
			checkAngle(points, maxDistancePoint, p);
		}
	}

	private void checkAngle(List<Point> points, Point maxDistancePoint, Point p) {
		if (p.equals(points.get(0)) || p.equals(maxDistancePoint)) {
			return;
		}
		double angle = p.calculateAngle(points.get(0), maxDistancePoint);
		if (angle != ANGLE_OF_RECTANGLE) {
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
		Point maxDistancePoint = getLongestPoint(points.getPoints());
		List<Point> nearPoints = new ArrayList<>(points.getPoints());
		nearPoints.remove(maxDistancePoint);
		return nearPoints.get(0).calculateDistance(nearPoints.get(1)) * nearPoints.get(0).calculateDistance(nearPoints.get(2));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rectangle)) {
			return false;
		}
		Rectangle rectangle = (Rectangle) o;
		return Objects.equals(points, rectangle.points);
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
