package com.woowacourse.coordinate.domain;

import java.util.List;

public interface FigureCreator {
	Figure create(List<Point> asList);
}
