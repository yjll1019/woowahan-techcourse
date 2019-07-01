package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.database.DatabaseConnection;

public class JdbcTemplate {
	public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
		try {
			PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
			pss.setParam(pstmt);
			return rm.mapRow(pstmt.executeQuery());
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	public void executeUpdate(String sql, PreparedStatementSetter pss) throws SQLException {
		try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
			pss.setParam(pstmt);
			pstmt.executeUpdate();
		}
	}
}
