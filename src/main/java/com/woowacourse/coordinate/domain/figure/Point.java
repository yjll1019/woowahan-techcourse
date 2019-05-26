package com.woowacourse.coordinate.domain.figure;

import java.util.Objects;
import java.util.Optional;

import com.woowacourse.coordinate.domain.coordinate.XCoordinate;
import com.woowacourse.coordinate.domain.coordinate.YCoordinate;

public class Point {
	private XCoordinate x;
	private YCoordinate y;

	public Point(XCoordinate x, YCoordinate y) {
		this.x = x;
		this.y = y;
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

	public Optional<Double> calculateSlope(Point p) {
		if (this.equals(p)) {
			throw new IllegalArgumentException("삼각형을 만들 수 없는 좌표입니다.");
		}

		if(this.x == p.x) {
			return Optional.of(Math.abs((p.y.getCoordinate() - this.y.getCoordinate()) / 1.0));
		}

		return Optional.of(Math.abs((p.y.getCoordinate() - this.y.getCoordinate()) / (double) (p.x.getCoordinate() - this.x.getCoordinate())));
	}

	public double calculateAngle(Point p1, Point p2) {
		double cangleRad = Math.atan((double) (p2.y.getCoordinate() - this.y.getCoordinate())
				/ (p2.x.getCoordinate() - this.x.getCoordinate()))
				- Math.atan((double) (p1.y.getCoordinate() - this.y.getCoordinate()) / (p1.x.getCoordinate() - this.x.getCoordinate()));
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
