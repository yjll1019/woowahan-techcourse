package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import chess.dao.exception.DataAccessException;
import chess.database.DatabaseConnection;

public class JdbcTemplate {
	private static final int ROUND_INDEX = 1;

	private <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		List<T> result = executeQueryForMultiple(sql, rm, pss);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public <T> List<T> executeQueryForMultiple(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = createPreparedStatement(conn, sql, pss);
			 ResultSet rs = pstmt.executeQuery()) {
			List<T> result = new ArrayList<>();

			while (rs.next()) {
				result.add(rm.mapRow(rs));
			}
			return result;
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}


	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... params) {
		return executeQuery(sql, rm, createPreparedStatementSetter(params));
	}

	private void executeUpdate(String sql, PreparedStatementSetter pss) {
		try (Connection con = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = createPreparedStatement(con, sql, pss)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void executeUpdate(String sql, Object... params) {
		executeUpdate(sql, createPreparedStatementSetter(params));
	}

	private PreparedStatement createPreparedStatement(Connection conn, String sql, PreparedStatementSetter pss) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pss.setParams(pstmt);
			return pstmt;
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public int executeInsert(String sql, Object... params) {
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			PreparedStatementSetter pss = createPreparedStatementSetter(params);
			pss.setParams(pstmt);
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (!rs.next()) {
					throw new DataAccessException();
				}
				return rs.getInt(ROUND_INDEX);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public PreparedStatementSetter createPreparedStatementSetter(Object[] params) {
		return pstmt -> {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		};
	}
}