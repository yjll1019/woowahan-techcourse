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
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomNumber);
			}
		};

		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					throw new SQLException();
				}
				return Player.valueOf(rs.getString(1));
			}
		};

		JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
		return (Player) selectJdbcTemplate.executeQuery(SELECT_CHESS_GAME_TURN, pss, rm);
	}

	public void changeTurn(int roomNumber, String currentPlayerName) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, currentPlayerName);
				pstmt.setInt(2, roomNumber);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(UPDATE_CHESS_GAME_TURN_QUERY, pss);
	}

	public void gameover(int roomNumber) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setBoolean(1, false);
				pstmt.setInt(2, roomNumber);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(UPDATE_CHESS_GAME_OVER_QUERY, pss);
	}

	public List<Integer> getNotOverAllRoomNumbers() throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParam(PreparedStatement pstmt) throws SQLException {
				return;
			}
		};

		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				List<Integer> roomNumbers = new ArrayList<>();
				while (rs.next()) {
					roomNumbers.add(rs.getInt(1));
				}
				return roomNumbers;
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return (List<Integer>) jdbcTemplate.executeQuery(SELECT_NOT_OVER_CHESS_GAME, pss, rm);
	}
}
