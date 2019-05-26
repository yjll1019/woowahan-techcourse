package com.woowacourse.coordinate.domain.figure;

import java.util.HashSet;

public abstract class Figure implements Shape {
	protected Points points;

	Figure(Points points, int numOfPoints) {
		if (new HashSet<>(points.getPoints()).size() != numOfPoints) {
			throw new IllegalArgumentException("위치가 같은 점(point)이 존재합니다. 각 점의 위치는 달라야 합니다.");
		}
	}
}
