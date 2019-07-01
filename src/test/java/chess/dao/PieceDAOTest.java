package chess.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


import chess.domain.Player;
import chess.domain.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceDAOTest {
	@Test
	void crd테스트() throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		int chessGameId = chessGameDAO.addChessGame(Player.BLACK.name());

		PieceDAO pieceDAO = new PieceDAO();
		List<Piece> actual = Arrays.asList(Pawn.valueOf(Player.BLACK, Position.getPosition(1, 2)));
		pieceDAO.addAllPieces(chessGameId, actual);

		List<Piece> expect = pieceDAO.getChessPieces(chessGameId);
		assertThat(actual).isEqualTo(expect);

		pieceDAO.deleteAllPieces(chessGameId);
		expect = pieceDAO.getChessPieces(chessGameId);
		assertTrue(expect.size() == 0);
	}
}