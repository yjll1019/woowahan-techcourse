package nextstep.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T> {
     List<T> mapRows(ResultSet rs) throws SQLException;
}
