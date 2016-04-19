package org.metro_blind.api.model;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.metro_blind.api.model.User;

public interface UserDAO
{
    @SqlUpdate("insert into user (name, password) values (:name, :password)")
    void insert(@BindBean User user);

    @SqlQuery("select name from user where id = :id")
    String findNameById(@Bind("id") int id);
}
