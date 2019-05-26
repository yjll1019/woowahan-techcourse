package com.woowacourse.coordinate;

import java.util.List;

import com.woowacourse.coordinate.domain.*;
import com.woowacourse.coordinate.view.InputView;
import com.woowacourse.coordinate.view.OutputView;

public class AppMain {
	public static void main(String[] args) {
		calculateCoordinates();
	}

	public static void calculateCoordinates() {
		try {
			List<Point> points = createPoints();
			Figure figure = createFigure(points);

			OutputView.printCoordinate(points);
			printFigure(figure);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			calculateCoordinates();
		}
	}

	public static List<Point> createPoints() {
		return Point.createWithPair(InputView.inputCoordinate());
	}

	public static void printFigure(Figure figure) {
		if (figure == null) {
			return;
		}
		OutputView.printShape(figure);
	}

	public static Figure createFigure(List<Point> points) {
		FigureCreator figureCreator = new FigureFactory();

		if (points.size() != 1) {
			return figureCreator.create(points);
		}
		return null;
	}
}
