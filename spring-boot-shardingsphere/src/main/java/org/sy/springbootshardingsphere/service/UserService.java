package org.sy.springbootshardingsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sy.springbootshardingsphere.entity.User;
import org.sy.springbootshardingsphere.mapper.UserMapper;
import java.util.Calendar;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int insertOne(long userId, String name) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 10);
        User user = new User().setUserId(userId).setName(name).setRegisterDate(c.getTime());
        return userMapper.insertOne(user);
    }

}
