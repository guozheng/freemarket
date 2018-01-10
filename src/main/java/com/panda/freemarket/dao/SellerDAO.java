package com.panda.freemarket.dao;

import com.panda.freemarket.model.Seller;
import com.panda.freemarket.model.SellerResultSetMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 *
 * See: http://jdbi.org
 *
 * SellerDAO
 */
@RegisterMapper(SellerResultSetMapper.class)
public interface SellerDAO {
    @SqlQuery("SELECT * FROM seller WHERE id=:id")
    Seller getById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM seller ORDER BY id LIMIT :count OFFSET (:from-1)")
    List<Seller> getByRange(@Bind("from") int from, @Bind("count") int count);

    @SqlUpdate("INSERT INTO seller(name) VALUES (:name)")
    void add(@Bind("name") String name);

    @SqlUpdate("UPDATE seller SET name=:name where id=:id")
    void update(@BindBean final Seller seller);
}
