package com.deemo.service;

import com.deemo.entity.Dept;
import com.deemo.entity.Emp;

import java.util.List;

public interface DeptService {

    int deleteByPrimaryKey(int id);

    int insert(Dept record);

    Dept selectByPrimaryKey(int id);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);

}
