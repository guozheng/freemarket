package com.panda.freemarket.model;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gzge. All Rights Reserved
 */
public class BidResultSetMapper implements ResultSetMapper<Bid> {
    @Override
    public Bid map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Bid(resultSet.getInt("id"),
                resultSet.getTimestamp("created_time"),
                resultSet.getDouble("price"),
                resultSet.getString("status"),
                resultSet.getInt("seller_id"),
                resultSet.getInt("project_id"));
    }
}
