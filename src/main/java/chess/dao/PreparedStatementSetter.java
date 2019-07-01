package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	void setParam(PreparedStatement pstmt) throws SQLException;
}
