package com.panda.freemarket.dao;

import com.panda.freemarket.model.Project;
import com.panda.freemarket.model.ProjectResultSetMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 */
@RegisterMapper(ProjectResultSetMapper.class)
public interface ProjectDAO {
    @SqlQuery("SELECT * FROM project WHERE id=:id")
    Project getById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM project WHERE buyer_id=:buyer_id")
    List<Project> getByBuyerId(@Bind("buyer_id") int buyerId);

    @SqlQuery("SELECT * FROM project WHERE bid_deadline >= NOW() ORDER BY id LIMIT :count OFFSET (:from-1)")
    List<Project> getOpenByRange(@Bind("from") int from, @Bind("count") int count);

    @SqlQuery("SELECT * FROM project WHERE bid_deadline < NOW() ORDER BY id LIMIT :count OFFSET (:from-1)")
    List<Project> getClosedByRange(@Bind("from") int from, @Bind("count") int count);

    @SqlUpdate("INSERT INTO project(created_time, name, description, bid_deadline, buyer_id) VALUES " +
            "(:created_time, :name, :description, :bid_deadline, :buyer_id)")
    void add(@BindBean final Project project);

    /**
     * Only allow bid_deadline to be updated
     * @param project
     */
    @SqlUpdate("UPDATE project SET bid_deadline=:bid_deadline WHERE id=:id")
    void update(@BindBean final Project project);
}
