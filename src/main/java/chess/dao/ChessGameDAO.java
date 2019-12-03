package chess.dao;

import java.util.List;

import chess.domain.Player;

public class ChessGameDAO {
	private static final String INSERT_CHESS_GAME_QUERY = "insert into chessgame(turn) values(?)";
	private static final String SELECT_CHESS_GAME_TURN = "select turn from chessgame where room_number = ?";
	private static final String SELECT_NOT_OVER_CHESS_GAME = "select room_number from chessgame where gameover = 1";
	private static final String UPDATE_CHESS_GAME_TURN_QUERY = "update chessgame set turn = ? where room_number = ?";
	private static final String UPDATE_CHESS_GAME_OVER_QUERY = "update chessgame set gameover = ? where room_number = ?";

	public int addChessGame(String currentPlayerName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.executeInsert(INSERT_CHESS_GAME_QUERY, currentPlayerName);
	}

	public Player getChessGameTurn(int roomNumber) {
		RowMapper<Player> rm = rs -> Player.valueOf(rs.getString(1));

		JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
		return selectJdbcTemplate.executeQuery(SELECT_CHESS_GAME_TURN, rm, roomNumber);
	}

	public void changeTurn(int roomNumber, String currentPlayerName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(UPDATE_CHESS_GAME_TURN_QUERY, currentPlayerName, roomNumber);
	}

	public void gameover(int roomNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(UPDATE_CHESS_GAME_OVER_QUERY, false, roomNumber);
	}

	public List<Integer> getNotOverAllRoomNumbers() {
		PreparedStatementSetter pss = pstmt -> {};
		RowMapper<Integer> rm = rs -> rs.getInt(1);

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.executeQueryForMultiple(SELECT_NOT_OVER_CHESS_GAME, rm, pss);
	}
}
