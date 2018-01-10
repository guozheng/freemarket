package com.panda.freemarket.model;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gzge. All Rights Reserved
 */
public class SellerResultSetMapper implements ResultSetMapper<Seller> {

    @Override
    public Seller map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Seller(resultSet.getInt("id"),
                resultSet.getString("name"));
    }
}
