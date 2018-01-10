package com.panda.freemarket.dao;

import com.panda.freemarket.model.Buyer;
import com.panda.freemarket.model.BuyerResultSetMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 */
@RegisterMapper(BuyerResultSetMapper.class)
public interface BuyerDAO {

    @SqlQuery("SELECT * FROM buyer WHERE id=:id")
    Buyer getById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM buyer ORDER BY id LIMIT :count OFFSET (:from-1)")
    List<Buyer> getByRange(@Bind("from") int from, @Bind("count") int count);

    @SqlUpdate("INSERT INTO buyer(name) VALUES (:name)")
    void add(@Bind("name") String name);

    @SqlUpdate("UPDATE buyer SET name=:name WHERE id=:id")
    void update(@BindBean final Buyer buyer);
}
