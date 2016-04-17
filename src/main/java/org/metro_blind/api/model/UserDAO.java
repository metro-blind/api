package org.metro_blind.api.model;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface UserDAO
{
    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select name from something where id = :id")
    String findNameById(@Bind("id") int id);
}

