package com.woowacourse.coordinate.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rectangle extends Figure {
	public static final int NUM_OF_POINTS = 4;
	private static final int ANGLE_OF_RECTANGLE = 90;
	private static final int FIRST_POINT_OF_RECTANGLE = 0;
	private static final int SECOND_POINT_OF_RECTANGLE = 1;
	private static final int THIRD_POINT_OF_RECTANGLE = 2;

	public Rectangle(Points points) {
		super(points);
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
		if (p.equals(points.get(FIRST_POINT_OF_RECTANGLE)) || p.equals(maxDistancePoint)) {
			return;
		}
		double angle = p.calculateAngle(points.get(FIRST_POINT_OF_RECTANGLE), maxDistancePoint);
		if (angle != ANGLE_OF_RECTANGLE) {
			throw new IllegalArgumentException("사각형이 아닙니다.");
		}
	}

	private Point getLongestPoint(List<Point> points) {
		Point maxDistancePoint = points.get(SECOND_POINT_OF_RECTANGLE);
		for (int i = 2; i < points.size(); i++) {
			maxDistancePoint = getLongerPoint(points, maxDistancePoint, points.get(i));
		}
		return maxDistancePoint;
	}

	private Point getLongerPoint(List<Point> points, Point p1, Point p2) {
		if (points.get(FIRST_POINT_OF_RECTANGLE).calculateDistance(p2) > points.get(FIRST_POINT_OF_RECTANGLE).calculateDistance(p1)) {
			return p2;
		}
		return p1;
	}

	@Override
	public double calculateArea() {
		Point maxDistancePoint = getLongestPoint(points.getPoints());
		List<Point> nearPoints = new ArrayList<>(points.getPoints());
		nearPoints.remove(maxDistancePoint);
		return nearPoints.get(FIRST_POINT_OF_RECTANGLE).calculateDistance(nearPoints.get(SECOND_POINT_OF_RECTANGLE))
				* nearPoints.get(FIRST_POINT_OF_RECTANGLE).calculateDistance(nearPoints.get(THIRD_POINT_OF_RECTANGLE));
	}

	@Override
	public String getCalculateMessage() {
		return String.format("사각형의 넓이는 %.1f", calculateArea());
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
		return String.format("사각형의 넓이는 %.1f", calculateArea());
	}
}
