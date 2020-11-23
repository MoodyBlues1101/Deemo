package com.deemo.service;


import com.deemo.dao.DeptMapper;
import com.deemo.dao.EmpMapper;
import com.deemo.entity.Dept;
import com.deemo.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Dept record) {
        return deptMapper.insert(record);
    }

    @Override
    public Dept selectByPrimaryKey(int id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Dept record) {
        return deptMapper.updateByPrimaryKey(record);
    }

}
