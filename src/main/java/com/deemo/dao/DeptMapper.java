package com.deemo.dao;

import com.deemo.entity.Dept;
import com.deemo.entity.Emp;
import com.deemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {

    int deleteByPrimaryKey(int id);

    int insert(Dept record);

    Dept selectByPrimaryKey(int id);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);
}