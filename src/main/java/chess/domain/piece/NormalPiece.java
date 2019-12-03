package chess.domain.piece;

import java.util.List;
import java.util.Objects;

import chess.domain.*;

public class NormalPiece extends Piece {
	private final List<MovementInfo> movementInfos;

	NormalPiece(Player player, Type type, List<MovementInfo> movementInfos, Position position, Score score) {
		super(player, type, position, score);
		this.movementInfos = movementInfos;
	}

	@Override
	public Path getMovablePath(Position end) {
		return getValidPath(end, movementInfos);
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getMovablePath(end);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof NormalPiece)) {
			return false;
		}
		final NormalPiece that = (NormalPiece) o;
		return Objects.equals(movementInfos, that.movementInfos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(movementInfos);
	}
}
