package nextstep.jdbc;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import slipp.domain.User;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcTemplateTest {
    private JdbcTemplate jdbcTemplate;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        ConnectionManager.initialize("org.h2.Driver", "jdbc:h2:mem:jwp-framework",
                "sa", "");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
        this.jdbcTemplate = new JdbcTemplate();
        this.expectedUser = new User("admin", "password", "자바지기", "admin@slipp.net");
    }

    @Test
    @DisplayName("PreparedStatementSetter를 이용한 insert 쿼리문 실행")
    void insert_with_prepared_statement_setter() {
        String userInsertSql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        User user = new User("ssosso", "password", "쏘쏘", "ssosso@slipp.net");

        jdbcTemplate.updateTemplate(userInsertSql, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });

        String userFindSql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        User actualUser = jdbcTemplate.selectTemplateForObject(userFindSql, ((rs) -> new User(rs.getString("userId"),
                        rs.getString("password"), rs.getString("name"), rs.getString("email"))),
                ((pstmt) -> pstmt.setString(1, user.getUserId())));

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    @DisplayName("가변인자를 이용한 insert 쿼리문 실행")
    void insert_with_prepared_variable_argument() {
        String userInsertSql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        User user = new User("ssosso", "password", "쏘쏘", "ssosso@slipp.net");

        jdbcTemplate.updateTemplate(userInsertSql, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });

        String userFindSql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        User actualUser = jdbcTemplate.selectTemplateForObject(userFindSql, ((rs) -> new User(rs.getString("userId"),
                rs.getString("password"), rs.getString("name"),
                rs.getString("email"))), user.getUserId());

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    @DisplayName("PreparedStatementSetter를 이용하여 하나의 object를 조회하는 select 쿼리문 실행")
    void select_with_prepared_statement_setter_for_object() {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        User actual = jdbcTemplate.selectTemplate(sql, ((rs) -> new User(rs.getString("userId"),
                        rs.getString("password"), rs.getString("name"), rs.getString("email"))),
                ((pstmt) -> pstmt.setString(1, expectedUser.getUserId()))).get(0);

        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("가변인자를 이용하여 하나의 object를 조회하는 쿼리문 실행")
    void select_with_variable_argument_for_object() {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        User actual = jdbcTemplate.selectTemplate(sql, ((rs) -> (new User(rs.getString("userId"),
                        rs.getString("password"), rs.getString("name"), rs.getString("email")))),
                expectedUser.getUserId()).get(0);

        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("PreparedStatementSetter를 이용하여 여러 개의 object를 조회하는 select 쿼리문 실행")
    void select_with_prepared_statement_setter() {
        String sql = "SELECT userId, password, name, email FROM USERS";

        List<User> actual = jdbcTemplate.selectTemplate(sql, ((rs) -> new User(rs.getString("userId"),
                rs.getString("password"), rs.getString("name"), rs.getString("email"))));

        assertThat(actual.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("가변인자를 이용하여 여러 개의 object를 조회하는 쿼리문 실행")
    void select_with_variable_argument() {
        String sql = "SELECT userId, password, name, email FROM USERS";

        List<User> actual = jdbcTemplate.selectTemplate(sql, ((rs) -> (new User(rs.getString("userId"),
                rs.getString("password"), rs.getString("name"), rs.getString("email")))));

        assertThat(actual.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("PreparedStatementSetter를 이용한 update 쿼리문 실행")
    void update_with_prepared_statement_setter() {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";

        int countOfUpdatedRecords = jdbcTemplate.updateTemplate(sql, (pstmt) -> {
            pstmt.setString(1, expectedUser.getPassword());
            pstmt.setString(2, expectedUser.getName());
            pstmt.setString(3, expectedUser.getEmail());
            pstmt.setString(4, expectedUser.getUserId());
        });

        assertThat(countOfUpdatedRecords).isEqualTo(1);
    }

    @Test
    @DisplayName("가변인자를 이용한 update 쿼리문 실행")
    void update_with_variable_argument() {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";

        int countOfUpdatedRecords = jdbcTemplate.updateTemplate(sql, expectedUser.getPassword(),
                expectedUser.getName(), expectedUser.getEmail(), expectedUser.getUserId());

        assertThat(countOfUpdatedRecords).isEqualTo(1);
    }
}