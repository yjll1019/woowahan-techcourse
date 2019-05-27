package com.woowacourse.coordinate.domain;

import java.util.Objects;

public class Coordinate {
	private static final int COORDINATE_MIN = 0;
	private static final int COORDINATE_MAX = 24;

	private final int coordinate;

	public Coordinate(final int coordinate) {
		checkRange(coordinate);
		this.coordinate = coordinate;
	}

	private void checkRange(int coordinate) {
		if (coordinate <= COORDINATE_MIN || coordinate > COORDINATE_MAX) {
			throw new IllegalArgumentException("범위를 벗어난 좌표입니다.");
		}
	}

	public int getCoordinate() {
		return coordinate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Coordinate)) {
			return false;
		}
		final Coordinate that = (Coordinate) o;
		return getCoordinate() == that.getCoordinate();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCoordinate());
	}
}
