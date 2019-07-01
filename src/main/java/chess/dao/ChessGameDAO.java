package chess.dao;

import java.sql.*;
import java.util.List;

import chess.database.DatabaseConnection;
import chess.domain.Player;

public class ChessGameDAO {
	private static final int ROUND_INDEX = 1;
	private static final String INSERT_CHESS_GAME_QUERY = "insert into chessgame(turn) values(?)";
	private static final String SELECT_CHESS_GAME_TURN = "select turn from chessgame where room_number = ?";
	private static final String SELECT_NOT_OVER_CHESS_GAME = "select room_number from chessgame where gameover = 1";
	private static final String UPDATE_CHESS_GAME_TURN_QUERY = "update chessgame set turn = ? where room_number = ?";
	private static final String UPDATE_CHESS_GAME_OVER_QUERY = "update chessgame set gameover = ? where room_number = ?";

	//TODO : JdbcTemplate 반영
	public int addChessGame(String currentPlayerName) throws SQLException {
		try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(
				INSERT_CHESS_GAME_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, currentPlayerName);
			pstmt.executeUpdate();
			return getRoomNumber(pstmt);
		}
	}

	private int getRoomNumber(PreparedStatement pstmt) throws SQLException {
		try (ResultSet resultSet = pstmt.getGeneratedKeys()) {
			if (!resultSet.next()) {
				throw new SQLException();
			}
			return resultSet.getInt(ROUND_INDEX);
		}
	}

	public Player getChessGameTurn(int roomNumber) {
		RowMapper<Player> rm = new RowMapper<Player>() {
			@Override
			public Player mapRow(ResultSet rs) throws SQLException {
				return Player.valueOf(rs.getString(1));
			}
		};

		JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
		return selectJdbcTemplate.executeQuery(SELECT_CHESS_GAME_TURN, rm, roomNumber);
	}

	public void changeTurn(int roomNumber, String currentPlayerName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(UPDATE_CHESS_GAME_TURN_QUERY, currentPlayerName, roomNumber);
	}

	public void gameover(int roomNumber) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(UPDATE_CHESS_GAME_OVER_QUERY, false, roomNumber);
	}

	public List<Integer> getNotOverAllRoomNumbers() {
		RowMapper<Integer> rm = new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs) throws SQLException {
				return rs.getInt(1);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.executeQueryForMultiple(SELECT_NOT_OVER_CHESS_GAME, rm, null);
	}
}
