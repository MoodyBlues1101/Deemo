package com.deemo.service;


import com.deemo.dao.EmpMapper;
import com.deemo.dao.UserMapper;
import com.deemo.entity.Emp;
import com.deemo.entity.User;
import com.deemo.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return empMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Emp record) {
        return empMapper.insert(record);
    }

    @Override
    public Emp selectByPrimaryKey(int id) {
        return empMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Emp record) {
        return empMapper.updateByPrimaryKey(record);
    }

    @Override
    public Emp selectByPrimaryKeyWithDept(int id) {
        return empMapper.selectByPrimaryKeyWithDept(id);
    }

    @Override
    public List<Emp> selectAllWithDept() {
        return empMapper.selectAllWithDept();
    }

    @Override
    public boolean checkName(String empName) {
        Emp emp = empMapper.checkName(empName);
        boolean b = (emp == null) ? true : false;
        return b;
    }
}
