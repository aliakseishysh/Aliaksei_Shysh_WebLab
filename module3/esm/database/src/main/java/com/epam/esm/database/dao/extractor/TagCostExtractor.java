package com.epam.esm.database.dao.extractor;

import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.entity.TagCost;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagCostExtractor implements ResultSetExtractor<List<TagCost>> {

    @Override
    public List<TagCost> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<TagCost> results = new ArrayList<>();
        while (rs.next()) {
            TagCost tagCost = new TagCost(extractTag(rs), extractCost(rs));
            results.add(tagCost);
        }
        return results;
    }

    private Tag extractTag(ResultSet rs) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getLong(1));
        tag.setName(rs.getString(2));
        return tag;
    }

    private BigDecimal extractCost(ResultSet rs) throws SQLException {
        BigDecimal cost = rs.getBigDecimal(3);
        return cost;
    }

}
