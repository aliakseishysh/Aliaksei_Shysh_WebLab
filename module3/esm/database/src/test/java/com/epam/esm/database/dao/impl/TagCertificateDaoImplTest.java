package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import com.epam.esm.database.dao.impl.TagCertificateDaoImpl;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagCertificateDaoImplTest {

    private static EmbeddedDatabase embeddedDatabase;
    private static TagCertificateDao tagCertificateDao;

    @BeforeAll
    public static void init() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb;DATABASE_TO_UPPER=false")
                .addScript("database_test.sql")
                .build();

        tagCertificateDao = new TagCertificateDaoImpl(new JdbcTemplate(embeddedDatabase));
    }

    @Test
    @Order(1)
    public void createTest() {
        boolean actual = tagCertificateDao.create(3L, 1L);
        Assertions.assertTrue(actual);
    }

    @AfterAll
    public static void shutdownDatabase() {
        embeddedDatabase.shutdown();
    }

}
