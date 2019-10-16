package slipp.dao;

import java.util.ArrayList;
import java.util.List;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.updateTemplate(sql, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        jdbcTemplate.updateTemplate(sql, (pstmt) -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        });
    }

    public List<User> findAll() {
        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.selectTemplate(sql, ((rs) -> {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
                users.add(user);
            }
            return users;
        }), (pstmt) -> {
        });
    }

    public User findByUserId(String userId) {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return jdbcTemplate.selectTemplateForObject(sql, ((rs) -> {
            List<User> users = new ArrayList<>();
            if (rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
                users.add(user);
            }
            return users;
        }), ((pstmt) -> pstmt.setString(1, userId)));
    }
}
