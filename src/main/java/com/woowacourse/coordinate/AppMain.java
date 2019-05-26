package com.woowacourse.coordinate;

import com.woowacourse.coordinate.domain.*;
import com.woowacourse.coordinate.view.InputView;
import com.woowacourse.coordinate.view.OutputView;

public class AppMain {
	public static void main(String[] args) {
		calculateCoordinates();
	}

	public static void calculateCoordinates() {
		try {
			Points points = createPoints();
			Figure figure = createFigure(points);

			OutputView.printCoordinate(points);
			printFigure(figure);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			calculateCoordinates();
		}
	}

	public static Points createPoints() {
		return new Points(Point.createWithPair(InputView.inputCoordinate()));
	}

	public static void printFigure(Figure figure) {
		if (figure == null) {
			return;
		}
		OutputView.printShape(figure);
	}

	public static Figure createFigure(Points points) {
		FigureCreator figureCreator = new FigureFactory();

		if (points.getPointsSize() != 1) {
			return figureCreator.create(points);
		}
		return null;
	}
}
