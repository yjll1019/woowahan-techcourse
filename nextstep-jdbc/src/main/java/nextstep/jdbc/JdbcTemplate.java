package nextstep.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import nextstep.jdbc.exception.SelectQueryFailException;
import nextstep.jdbc.exception.UpdateQueryFailException;

public class JdbcTemplate {
    public <T> List<T> selectTemplate(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pstmtSetter) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = setValues(con.prepareStatement(sql), pstmtSetter);
             ResultSet rs = pstmt.executeQuery()) {
            return rowMapper.mapRows(rs);
        } catch (Exception e) {
            throw new SelectQueryFailException();
        }
    }

    public <T> List<T> selectTemplate(String sql, RowMapper<T> rowMapper, Object... params) {
        return selectTemplate(sql, rowMapper, (pstmt) -> setValues(pstmt, params));
    }

    public int updateTemplate(String sql, PreparedStatementSetter pstmtSetter) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            throw new UpdateQueryFailException();
        }
    }

    public int updateTemplate(String sql, Object... params) {
        return updateTemplate(sql, (pstmt) -> setValues(pstmt, params));
    }

    private PreparedStatement setValues(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 1; i <= params.length; i++) {
            pstmt.setObject(i, params[i - 1]);
        }
        return pstmt;
    }

    private PreparedStatement setValues(PreparedStatement pstmt, PreparedStatementSetter pstmtSetter) throws SQLException {
        pstmtSetter.setValues(pstmt);
        return pstmt;
    }
}
