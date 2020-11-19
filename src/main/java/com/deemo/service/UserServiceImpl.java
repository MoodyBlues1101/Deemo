package com.deemo.service;


import com.deemo.dao.UserMapper;
import com.deemo.entity.User;
import com.deemo.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    //注入UserMapper
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public JsonMsg insert(User record) {
        JsonMsg jsonMsg = new JsonMsg();
        record.setId(UUID.randomUUID().toString());
        int i = userMapper.insert(record);
        if (i == 0) {
            jsonMsg.setMsg("添加失败");
            jsonMsg.setCode(400);
        } else {
            jsonMsg.setMsg("添加成功");
            jsonMsg.setCode(200);
        }

        return jsonMsg;
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
