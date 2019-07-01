package chess.dao;

import java.sql.SQLException;

import chess.domain.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDAOTest {
	@Test
	void cru테스트() throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		int chessGameId = chessGameDAO.addChessGame(Player.BLACK.name());
		Player player = chessGameDAO.getChessGameTurn(chessGameId);
		assertThat(player).isEqualTo(Player.BLACK);

		chessGameDAO.changeTurn(chessGameId, Player.WHITE.name());
		player = chessGameDAO.getChessGameTurn(chessGameId);
		assertThat(player).isEqualTo(Player.WHITE);
	}
}