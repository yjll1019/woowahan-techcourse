package com.woowacourse.coordinate.domain;

import java.util.HashSet;
import java.util.List;

public abstract class Figure implements Shape {
	protected List<Point> points;

	Figure(List<Point> points, int numOfPoints) {
		if(points.size() != numOfPoints) {
			throw new IllegalArgumentException("잘못된 좌표 입력입니다.");
		}
		if (new HashSet<>(points).size() != numOfPoints) {
			throw new IllegalArgumentException("위치가 같은 점(point)이 존재합니다. 각 점의 위치는 달라야 합니다.");
		}
	}
}
