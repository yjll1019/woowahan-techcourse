package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.database.DatabaseConnection;

public abstract class SelectJdbcTemplate {
	public Object executeQuery(String sql) throws SQLException {
		try {
			PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
			setParam(pstmt);
			ResultSet rs = pstmt.executeQuery();
			return mapRow(rs);
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	public abstract void setParam(PreparedStatement pstmt) throws SQLException;

	public abstract Object mapRow(ResultSet rs) throws SQLException;
}
