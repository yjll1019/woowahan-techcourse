package slipp.dao;

import java.util.ArrayList;
import java.util.List;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;

public class UserDao2 {
    private final JdbcTemplate JDBC_TEMPLATE;

    public UserDao2() {
        this.JDBC_TEMPLATE = new JdbcTemplate();
    }

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JDBC_TEMPLATE.updateTemplate(sql, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        JDBC_TEMPLATE.updateTemplate(sql, (pstmt) -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        });
    }

    public List<User> findAll() {
        String sql = "SELECT userId, password, name, email FROM USERS";
        return JDBC_TEMPLATE.selectTemplate(sql, (pstmt) -> {}, (rs) -> {
            List<User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
                users.add(user);
            }
            return users;
        });
    }

    public User findByUserId(String userId) {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return JDBC_TEMPLATE.<User>selectTemplate(sql,
                (pstmt) -> pstmt.setString(1, userId),
                (rs) -> {
                    List<User> users = new ArrayList<>();
                    if(rs.next()) {
                        User user = new User(rs.getString("userId"), rs.getString("password"),
                                rs.getString("name"), rs.getString("email"));
                        users.add(user);
                    }
                    return users;
                }).get(0);
    }
}
