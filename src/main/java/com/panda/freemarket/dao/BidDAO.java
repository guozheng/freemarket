package com.panda.freemarket.dao;

import com.panda.freemarket.model.Bid;
import com.panda.freemarket.model.BidResultSetMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 */
@RegisterMapper(BidResultSetMapper.class)
public interface BidDAO {
    @SqlQuery("SELECT * FROM bid WHERE id=:id")
    Bid getById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM bid WHERE seller_id=:seller_id")
    List<Bid> getBySellerId(@Bind("seller_id") int sellerId);

    @SqlQuery("SELECT * FROM bid WHERE project_id=:project_id")
    List<Bid> getByProjectId(@Bind("project_id") int projectId);

    @SqlUpdate("INSERT INTO bid(created_time, price, status, seller_id, project_id) VALUES " +
            "(:created_time, :price, :status, :seller_id, :project_id)")
    void add(@BindBean final Bid bid);

    /**
     * Only allow price to be updated
     * @param bid
     */
    @SqlUpdate("UPDATE bid SET price=:price WHERE id=:id")
    void update(@BindBean final Bid bid);
}
