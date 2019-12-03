package com.woowacourse.coordinate.view;

import java.util.List;
import java.util.stream.Collectors;

import com.woowacourse.coordinate.domain.Line;
import com.woowacourse.coordinate.domain.Point;
import com.woowacourse.coordinate.domain.Points;
import com.woowacourse.coordinate.domain.Shape;

public class OutputView {
	public static void printCoordinate(Points points) {
		List<Point> point = points.getPoints();
		StringBuilder sb = new StringBuilder();

		for (int i = 24; i > 0; --i) {
			final int currentY = i;
			sb.append(getYAxisString(currentY));
			List<Point> pointsToPrint = point.stream()
					.filter(p -> p.matchY(currentY))
					.collect(Collectors.toList());
			printPoint(sb, pointsToPrint);
			sb.append("\n");
		}
		printXAxis(sb);
		System.out.println(sb.toString());
		System.out.println();
	}

	private static String getYAxisString(int y) {
		if (y % 2 == 0) {
			return String.format("%2s|", y);
		}
		return "  |";
	}

	private static void printPoint(StringBuilder sb, List<Point> pointsToPrint) {
		for (int j = 0; j <= 24; ++j) {
			final int currentX = j;
			sb.append(getPointString(pointsToPrint, currentX));
		}
	}

	private static String getPointString(List<Point> pointsToPrint, int currentX) {
		if (pointsToPrint.stream()
				.filter(p -> p.matchX(currentX))
				.count() > 0) {
			return " ·";
		}
		return "  ";
	}

	private static void printXAxis(StringBuilder sb) {
		sb.append("  +");
		for (int i = 0; i < 24; i++) {
			sb.append("──");
		}
		sb.append("─\n  ");
		for (int i = 0; i <= 24; i++) {
			sb.append(getXAxisString(i));
		}
	}

	private static String getXAxisString(int x) {
		if (x % 2 == 0) {
			return String.format("%2d", x);
		}
		return "  ";
	}

	public static void printShape(Shape s) {
		if (s == null) {
			return;
		}
		System.out.println(s.getCalculateMessage());
	}
}
