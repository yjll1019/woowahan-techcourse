package com.woowacourse.coordinate.domain;

import java.util.Objects;
import java.util.Optional;

public class Point {
	private static final int MIN = 0;
	private static final int MAX = 24;

	private int x;
	private int y;

	public Point(int x, int y) {
		checkRange(x, y);
		this.x = x;
		this.y = y;
	}

	private void checkRange(int x, int y) {
		if (x <= MIN || x > MAX || y <= MIN || y > MAX) {
			throw new IllegalArgumentException(String.format("범위를 벗어난 좌표입니다 { x: %d, y: %d }", x, y));
		}
	}

	public boolean matchX(int x) {
		return this.x == x;
	}

	public boolean matchY(int y) {
		return this.y == y;
	}

	public double calculateDistance(Point point) {
		double dx = Math.abs(point.x - x);
		double dy = Math.abs(point.y - y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	public Optional<Double> calculateSlope(Point p) {
		if (p.x == this.x) {
			return Optional.ofNullable(null);
		}
		return Optional.of(Math.abs((p.y - this.y) / (double) (p.x - this.x)));
	}

	public double calculateAngle(Point p1, Point p2) {
		double cangleRad = Math.atan((double) (p2.y - this.y) / (p2.x - this.x)) - Math.atan((double) (p1.y - this.y) / (p1.x - this.x));
		return Math.abs(Math.toDegrees(cangleRad));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		Point point = (Point) o;
		return x == point.x &&
				y == point.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return String.format("Point { x: %d, y: %d }", x, y);
	}
}
