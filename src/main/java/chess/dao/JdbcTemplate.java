package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.database.DatabaseConnection;

public abstract class JdbcTemplate {
	public void insert(String sql) throws SQLException {
		try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
			setParam(pstmt);
			pstmt.executeUpdate();
		}
	}

	public abstract void setParam(PreparedStatement pstmt) throws SQLException;

}
