package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
	void setParams(PreparedStatement pstmt) throws SQLException;
}
