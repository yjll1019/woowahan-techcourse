package slipp.dao;

import java.sql.SQLException;
import java.util.List;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;

public class UserDao {
    private final JdbcTemplate JDBC_TEMPLATE;

    public UserDao() {
        this.JDBC_TEMPLATE = new JdbcTemplate();
    }

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JDBC_TEMPLATE.updateTemplate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        JDBC_TEMPLATE.updateTemplate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS";
        return JDBC_TEMPLATE.selectTemplate(sql, User.class);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return (User) JDBC_TEMPLATE.selectTemplate(sql, User.class, userId).get(0);
    }
}
