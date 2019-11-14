package sql;

import nextstep.jdbc.ConnectionManager;
import nextstep.jdbc.JdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryTest {
    private static final Logger logger = LoggerFactory.getLogger(QueryTest.class);
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        ConnectionManager.initialize();
        jdbcTemplate = new JdbcTemplate();
    }

    @Test
    void hobbyIsCoding() {
        createIndexQueryOfHobby();

        String hobbyIsCoding = "select hobby, Round(((count(*) / (select count(*) from survey_results_public)) * 100), 1) " +
                "as percent from survey_results_public group by hobby";
        List<HobbyDto> expect = Arrays.asList(new HobbyDto("No", 19.2), new HobbyDto("Yes", 80.8));

        List<HobbyDto> actual = jdbcTemplate.selectTemplate(hobbyIsCoding, rs -> new HobbyDto(rs.getString("hobby"),
                rs.getDouble("percent")));

        assertThat(expect).isEqualTo(actual);

        removeHobbyIdx();
    }

    private void createIndexQueryOfHobby() {
        String indexQuery = "create index idx_hobby on survey_results_public (hobby)";
        jdbcTemplate.updateTemplate(indexQuery);
    }

    private void removeHobbyIdx() {
        String idxRemoveQuery = "alter table survey_results_public drop index idx_hobby";

        jdbcTemplate.updateTemplate(idxRemoveQuery);
    }
}
