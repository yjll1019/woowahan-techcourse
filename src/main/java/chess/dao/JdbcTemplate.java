package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.dao.exception.DataAccessException;
import chess.database.DatabaseConnection;

public class JdbcTemplate {
	private <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		List<T> result = executeQueryForMultiple(sql, rm, pss);
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public <T> List<T> executeQueryForMultiple(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		try {
			PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
			ResultSet rs;
			pss.setParams(pstmt);
			rs = pstmt.executeQuery();
			List<T> result = new ArrayList<>();

			while(rs.next()) {
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
		try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
			pss.setParams(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void executeUpdate(String sql, Object... params) {
		executeUpdate(sql, createPreparedStatementSetter(params));
	}

	public PreparedStatementSetter createPreparedStatementSetter(Object[] params) {
		return new PreparedStatementSetter() {
			@Override
			public void setParams(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
		};
	}
}
