package com.deemo.service;

import com.deemo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
