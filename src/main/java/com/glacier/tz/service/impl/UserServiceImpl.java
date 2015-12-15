package com.glacier.tz.service.impl;

/**
 * Created by Glacierlx on 2015/12/14.
 */
import com.glacier.tz.dao.UserMapper;
import com.glacier.tz.model.User;
import com.glacier.tz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(int userId) {
        // TODO Auto-generated method stub
        return this.userMapper.selectByPrimaryKey(userId);
    }

}