package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.dao.extractor.TagCostExtractor;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.entity.TagCost;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    private static final String CREATE_TAG = "INSERT INTO tags(name) VALUES (?)";
    private static final String READ_TAGS = "SELECT id, name FROM tags";
    private static final String READ_TAG_BY_NAME = "SELECT id, name FROM tags WHERE name = ?";
    private static final String READ_MOST_WIDELY_USED_WITH_OVERALL_COST = "WITH cte_data AS (" +
            "  SELECT" +
            "    tags.id," +
            "    tags.name," +
            "    SUM(orders.price) OVER(PARTITION BY tags.id, tags.name) AS cost," +
            "    COUNT(*) OVER(PARTITION BY tags.id, tags.name) AS count" +
            "  FROM orders" +
            "  INNER JOIN users ON orders.user_id = users.id" +
            "  INNER JOIN tags_gift_certificates ON orders.certificate_id = tags_gift_certificates.certificate_id" +
            "  INNER JOIN tags ON tags_gift_certificates.tag_id = tags.id" +
            "  WHERE users.username = ?" +
            "), cte_max_count AS (" +
            "  SELECT MAX(cte_data.count) AS max_count FROM cte_data" +
            ")" +
            "  SELECT DISTINCT" +
            "    cte_data.id," +
            "    cte_data.name," +
            "    cte_data.cost" +
            "  FROM cte_data CROSS JOIN cte_max_count" +
            "  WHERE cte_data.count = cte_max_count.max_count";
    private static final String DELETE_TAG = "DELETE FROM tags WHERE id = ?";
    private static final String DELETE_TAG_BY_NAME = "DELETE FROM tags WHERE name = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Tag tag) throws EntityAlreadyExistsDaoException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(CREATE_TAG, new String[]{"id"});
                statement.setString(1, tag.getName());
                return statement;
            }, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (DuplicateKeyException e) {
            throw new EntityAlreadyExistsDaoException("Entity Tag with name=" + tag.getName() + " already exists.");
        }
    }

    @Override
    public List<Tag> read() {
        return jdbcTemplate.query(READ_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public List<Tag> read(String name) {
        return jdbcTemplate.query(READ_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), name);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_TAG, id) == 1;
    }

    @Override
    public boolean delete(String name) {
        return jdbcTemplate.update(DELETE_TAG_BY_NAME, name) == 1;
    }

    @Override
    public List<TagCost> readMostWidelyUsed(String username) {
        return jdbcTemplate.query(READ_MOST_WIDELY_USED_WITH_OVERALL_COST, new TagCostExtractor(), username);
    }
}
