package com.woowacourse.fuel;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RentCar)) {
			return false;
		}
		final RentCar rentCar = (RentCar) o;
		return Double.compare(rentCar.distancePerLiter, distancePerLiter) == 0 &&
				distance == rentCar.distance &&
				Objects.equals(getName(), rentCar.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), distancePerLiter, distance);
	}
}
