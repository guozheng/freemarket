package com.panda.freemarket.model;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gzge. All Rights Reserved
 */
public class ProjectResultSetMapper implements ResultSetMapper<Project> {
    @Override
    public Project map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Project(resultSet.getInt("id"),
                resultSet.getTimestamp("created_time"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getTimestamp("bid_deadline"),
                resultSet.getInt("buyer_id"));
    }
}
