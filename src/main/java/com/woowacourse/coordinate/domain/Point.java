package com.woowacourse.coordinate.domain;

import java.util.Objects;


public class Point {
	private Coordinate x;
	private Coordinate y;

	public Point(int x, int y) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}

	public boolean matchX(int x) {
		return this.x.getCoordinate() == x;
	}

	public boolean matchY(int y) {
		return this.y.getCoordinate() == y;
	}

	public double calculateDistance(Point point) {
		double dx = Math.abs(point.x.getCoordinate() - x.getCoordinate());
		double dy = Math.abs(point.y.getCoordinate() - y.getCoordinate());
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calculateSlope(Point p) {
		if (this.x.equals(p.x)) {
			throw new ArithmeticException("0으로 나눌 수 없습니다.");
		}
		return Math.abs((p.y.getCoordinate() - this.y.getCoordinate()) / (double) (p.x.getCoordinate() - this.x.getCoordinate()));
	}

	public double calculateAngle(Point p1, Point p2) {
		double angle = Math.atan((double) (p2.y.getCoordinate() - this.y.getCoordinate())
				/ (p2.x.getCoordinate() - this.x.getCoordinate()))
				- Math.atan((double) (p1.y.getCoordinate() - this.y.getCoordinate()) / (p1.x.getCoordinate() - this.x.getCoordinate()));
		return Math.abs(Math.toDegrees(angle));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		final Point point = (Point) o;
		return Objects.equals(x, point.x) &&
				Objects.equals(y, point.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
