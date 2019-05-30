package com.woowacourse.coordinate.domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsFactoryTest {
	@Test
	void create() {
		assertEquals(PointsFactory.createPoints("(1,1)-(2,2)"),
				new Points(Arrays.asList(new Point(1, 1), new Point(2, 2))));
		assertEquals(PointsFactory.createPoints(" ( 1 , 1 ) - ( 2 , 2 ) "),
				new Points(Arrays.asList(new Point(1, 1), new Point(2, 2))));
	}
}