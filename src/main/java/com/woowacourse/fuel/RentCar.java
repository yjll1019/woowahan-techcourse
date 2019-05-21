package com.woowacourse.fuel;

public abstract class RentCar implements Car{
	private final String name;
	private final double distancePerLiter;
	private final int distance;

	public RentCar(final String name, final double distancePerLiter, final int distance) {
		this.name = name;
		this.distancePerLiter = distancePerLiter;
		this.distance = distance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getChargeQuantity() {
		return distance / distancePerLiter;
	}
}
