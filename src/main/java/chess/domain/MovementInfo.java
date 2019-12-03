package chess.domain;

import java.util.Objects;

public class MovementInfo {
	private final Direction direction;
	private final int maxDistance;

	public MovementInfo(final Direction direction, final int maxDistance) {
		this.direction = direction;
		this.maxDistance = maxDistance;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MovementInfo)) {
			return false;
		}
		final MovementInfo that = (MovementInfo) o;
		return getMaxDistance() == that.getMaxDistance() &&
				getDirection() == that.getDirection();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDirection(), getMaxDistance());
	}
}
