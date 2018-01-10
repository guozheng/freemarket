package com.panda.freemarket.model;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gzge. All Rights Reserved
 */
public class BuyerResultSetMapper implements ResultSetMapper<Buyer> {

    @Override
    public Buyer map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Buyer(resultSet.getInt("id"),
                resultSet.getString("name"));
    }
}
