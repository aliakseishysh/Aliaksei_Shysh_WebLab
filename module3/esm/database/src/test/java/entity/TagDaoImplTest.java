package entity;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.dao.impl.TagDaoImpl;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagDaoImplTest {

    private static TagDao tagDao;
    private static EmbeddedDatabase embeddedDatabase;

    @BeforeAll
    public static void init() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb;DATABASE_TO_UPPER=false")
                .addScript("database_test.sql")
                .build();

        tagDao = new TagDaoImpl(new JdbcTemplate(embeddedDatabase));
    }

    @Test
    @Order(1)
    public void createTest() throws EntityAlreadyExistsDaoException {
        long actual = tagDao.create(new Tag(null, "tag name 4"));
        long expected = 4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void readTest() {
        List<Tag> expected = new ArrayList<>();
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        Tag tag3 = new Tag(3L, "tag name 3");
        Tag tag4 = new Tag(4L, "tag name 4");
        expected.add(tag1);
        expected.add(tag2);
        expected.add(tag3);
        expected.add(tag4);
        List<Tag> actual = tagDao.read();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void readByIdOrNameTest() {
        Tag searchWithIdOrName = new Tag(1L, "tag name 4");
        List<Tag> actual = tagDao.read(searchWithIdOrName);
        List<Tag> expected = new ArrayList<>();
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag4 = new Tag(4L, "tag name 4");
        expected.add(tag1);
        expected.add(tag4);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void deleteTest() {
        boolean actual = tagDao.delete(4L);
        Assertions.assertTrue(actual);
    }

    @AfterAll
    public static void shutdownDatabase() {
        embeddedDatabase.shutdown();
    }

}
