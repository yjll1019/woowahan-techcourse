package sql;

import nextstep.jdbc.ConnectionManager;
import nextstep.jdbc.JdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryTest {
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        ConnectionManager.initialize();
        jdbcTemplate = new JdbcTemplate();
    }

    @Test
    void hobbyIsCoding() {
        createIndexQueryOfHobby();

        String query = "select hobby, Round(((count(*) / (select count(*) from survey_results_public)) * 100), 1) " +
                "as percent from survey_results_public group by hobby";
        List<HobbyDto> expect = Arrays.asList(new HobbyDto("No", 19.2), new HobbyDto("Yes", 80.8));

        List<HobbyDto> actual = jdbcTemplate.selectTemplate(query, rs -> new HobbyDto(rs.getString("hobby"),
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

    @Test
    void getYearsOfProfessionalCodingExperience() {
        String query = "select devType, round(avg(years), 1) as percent\n" +
                "from (\n" +
                "select\n" +
                "\tcast(YearsCodingProf as UNSIGNED) as years,\n" +
                "\tSUBSTRING_INDEX (SUBSTRING_INDEX(DevType,';',numbers.n),';',-1) devType\n" +
                "from \n" +
                "\t(select 1 n union all\n" +
                "\tselect 2 union all select 3 union all\n" +
                "\tselect 4 union all select 5 union all\n" +
                "\tselect 6 union all select 7 union all\n" +
                "\tselect 8 union all select 9 union all\n" +
                "\tselect 10 union all select 11 union all\n" +
                "\tselect 12 union all select 13 union all\n" +
                "\tselect 14 union all select 15 union all\n" +
                "\tselect 16 union all select 17) numbers INNER  JOIN (\n" +
                "\t\tselect DevType, YearsCodingProf \n" +
                "\t\tfrom survey_results_public \n" +
                "\t\twhere YearsCodingProf != 'NA' \n" +
                "\t\tand DevType != 'NA'\n" +
                "\t) as table1\n" +
                "on CHAR_LENGTH ( DevType ) \n" +
                "- CHAR_LENGTH ( REPLACE ( DevType ,  ';' ,  '' ))>= numbers.n-1 \n" +
                ") as table2\n" +
                "group by devType\n" +
                "order by avg(years) desc;";

        List<DevTypeDto> actual = jdbcTemplate.selectTemplate(query,
                rs -> new DevTypeDto(rs.getString("devType"), rs.getDouble("percent")));

        assertTrue(actual.contains(new DevTypeDto("Engineering manager", 10.2)));
        assertTrue(actual.contains(new DevTypeDto("Database administrator", 6.9)));
        assertTrue(actual.contains(new DevTypeDto("Mobile developer", 5.2)));
    }
}
