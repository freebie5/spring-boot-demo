package org.sy.springbootshardingsphere.mapper;

import org.apache.ibatis.annotations.Insert;
import org.sy.springbootshardingsphere.entity.User;

public interface UserMapper {

    @Insert("insert into t_user(user_id, name, register_date) value(#{userId},#{name},#{registerDate})")
    int insertOne(User user);

}
