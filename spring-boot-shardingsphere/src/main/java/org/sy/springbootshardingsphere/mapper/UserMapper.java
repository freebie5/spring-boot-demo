package org.sy.springbootshardingsphere.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.sy.springbootshardingsphere.entity.User;

public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into t_user(user_id, name, register_date) value(#{userId},#{name},#{registerDate})")
    int insertOne(User user);

}
