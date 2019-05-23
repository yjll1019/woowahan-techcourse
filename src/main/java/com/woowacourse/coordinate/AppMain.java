package com.woowacourse.coordinate;

import java.util.List;

import com.woowacourse.coordinate.domain.*;
import com.woowacourse.coordinate.view.InputView;
import com.woowacourse.coordinate.view.OutputView;

public class AppMain {
	private static List<Point> points;

	public static void main(String[] args) {
		boolean handledResult = tryHandlePoints();

		while (!handledResult) {
			handledResult = tryHandlePoints();
		}
	}

	public static boolean tryHandlePoints() {
		try {
			printPoints();
			return true;
		} catch (Exception e) {
			OutputView.printError(e.getMessage());
		}
		return false;
	}

	private static boolean printPoints() {
		points = Point.createWithPair(InputView.inputCoordinate());
		FigureCreator figureCreator = new FigureFactory();

		if (points.size() != 1) {
			Figure figure = figureCreator.create(points);
			OutputView.printCoordinate(points);
			OutputView.printShape(figure);
			return true;
		}

		OutputView.printCoordinate(points);
		return true;
	}
}
