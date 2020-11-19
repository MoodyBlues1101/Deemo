package com.deemo.service;

import com.deemo.entity.User;
import com.deemo.utils.JsonMsg;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(String id);

    JsonMsg insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
