package com.deemo.service;

import com.deemo.entity.Emp;
import com.deemo.entity.User;
import com.deemo.utils.JsonMsg;

import java.util.List;

public interface EmpService {

    int deleteByPrimaryKey(int id);

    int insert(Emp record);

    Emp selectByPrimaryKey(int id);

    List<Emp> selectAll();

    int updateByPrimaryKey(Emp record);

    Emp selectByPrimaryKeyWithDept(int id);

    List<Emp> selectAllWithDept();
}
