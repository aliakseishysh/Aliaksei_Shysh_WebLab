package com.epam.esm.entity;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.dao.impl.CertificateDaoImpl;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SearchData;
import com.epam.esm.database.entity.Tag;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CertificateDaoImplTest {

    private static EmbeddedDatabase embeddedDatabase;
    private static CertificateDao certificateDao;

    @BeforeAll
    public static void init() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb;DATABASE_TO_UPPER=false")
                .addScript("database_test.sql")
                .build();

        certificateDao = new CertificateDaoImpl(new JdbcTemplate(embeddedDatabase));
    }

    @Test
    @Order(1)
    public void createTest() {
        GiftCertificate giftCertificate = new GiftCertificate(
                2L, "cert name 2", "cert desc 2", BigDecimal.valueOf(2), 2,
                LocalDateTime.parse("2021-01-02T09:10:12.100"), LocalDateTime.parse("2021-06-02T09:10:12.100")
        );
        long expected = 2;
        long actual = certificateDao.create(giftCertificate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void readTest() {
        List<GiftCertificate> expected = new ArrayList<>();
        GiftCertificate giftCertificate1 = new GiftCertificate(
                1L, "cert name 1", "cert desc 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-05-02T09:10:12.100")
        );
        GiftCertificate giftCertificate2 = new GiftCertificate(
                2L, "cert name 2", "cert desc 2", BigDecimal.valueOf(2), 2,
                LocalDateTime.parse("2021-01-02T09:10:12.100"), LocalDateTime.parse("2021-06-02T09:10:12.100")
        );
        expected.add(giftCertificate1);
        expected.add(giftCertificate2);
        List<GiftCertificate> actual = certificateDao.read();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void readWithEmptySearchData() {
        List<CertificateTag> expected = new ArrayList<>();
        GiftCertificate giftCertificate1 = new GiftCertificate(
                1L, "cert name 1", "cert desc 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-05-02T09:10:12.100")
        );
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(tag1);
        tags1.add(tag2);
        GiftCertificate giftCertificate2 = new GiftCertificate(
                2L, "cert name 2", "cert desc 2", BigDecimal.valueOf(2), 2,
                LocalDateTime.parse("2021-01-02T09:10:12.100"), LocalDateTime.parse("2021-06-02T09:10:12.100")
        );
        expected.add(new CertificateTag(giftCertificate1, tags1));
        expected.add(new CertificateTag(giftCertificate2, new ArrayList<>()));
        List<CertificateTag> actual = certificateDao.read(new SearchData());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void readWithTagNameSearchData() {
        List<CertificateTag> expected = new ArrayList<>();
        GiftCertificate giftCertificate1 = new GiftCertificate(
                1L, "cert name 1", "cert desc 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-05-02T09:10:12.100")
        );
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(tag1);
        tags1.add(tag2);
        expected.add(new CertificateTag(giftCertificate1, tags1));
        List<CertificateTag> actual = certificateDao.read(new SearchData(
                "tag name 1", null, null, null
        ));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    public void readWithPartialCertificateNameSearchData() {
        List<CertificateTag> expected = new ArrayList<>();
        GiftCertificate giftCertificate1 = new GiftCertificate(
                1L, "cert name 1", "cert desc 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-05-02T09:10:12.100")
        );
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(tag1);
        tags1.add(tag2);
        expected.add(new CertificateTag(giftCertificate1, tags1));
        List<CertificateTag> actual = certificateDao.read(new SearchData(
                null, "name 1", null, null
        ));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    public void readWithPartialCertificateDescriptionSearchData() {
        List<CertificateTag> expected = new ArrayList<>();
        GiftCertificate giftCertificate1 = new GiftCertificate(
                1L, "cert name 1", "cert desc 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-05-02T09:10:12.100")
        );
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(tag1);
        tags1.add(tag2);
        expected.add(new CertificateTag(giftCertificate1, tags1));
        List<CertificateTag> actual = certificateDao.read(new SearchData(
                null, null, "desc 1", null
        ));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    public void updateTest() {
        GiftCertificate giftCertificate = new GiftCertificate(
                null, "cert name 2 new", null, null, 5,
                null, LocalDateTime.parse("2021-07-02T09:10:12.100")
        );
        boolean actual = certificateDao.update(2L, giftCertificate);
        Assertions.assertTrue(actual);
    }

    @Test
    @Order(8)
    public void deleteTest() {
        boolean actual = certificateDao.delete(2L);
        Assertions.assertTrue(actual);
    }

    @AfterAll
    public static void shutdownDatabase() {
        embeddedDatabase.shutdown();
    }

}
