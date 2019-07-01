package chess.dao;

import java.sql.*;
import java.util.ArrayList;
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

	public Player getChessGameTurn(int roomNumber) throws SQLException {
		SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomNumber);
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					throw new SQLException();
				}
				return Player.valueOf(rs.getString(1));
			}
		};

		return (Player) selectJdbcTemplate.executeQuery(SELECT_CHESS_GAME_TURN);
	}

	public void changeTurn(int roomNumber, String currentPlayerName) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, currentPlayerName);
				pstmt.setInt(2, roomNumber);
			}
		};
		jdbcTemplate.insert(UPDATE_CHESS_GAME_TURN_QUERY);
	}

	public void gameover(int roomNumber) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setBoolean(1, false);
				pstmt.setInt(2, roomNumber);
			}
		};
		jdbcTemplate.insert(UPDATE_CHESS_GAME_OVER_QUERY);
	}

	public List<Integer> getNotOverAllRoomNumbers() throws SQLException {
		SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
			@Override
			public void setParam(PreparedStatement pstmt) {
				return;
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				List<Integer> roomNumbers = new ArrayList<>();
				while (rs.next()) {
					roomNumbers.add(rs.getInt(1));
				}
				return roomNumbers;
			}
		};

		return (List<Integer>) jdbcTemplate.executeQuery(SELECT_NOT_OVER_CHESS_GAME);
	}
}
