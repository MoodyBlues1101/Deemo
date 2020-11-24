package com.deemo.dao;

import com.deemo.entity.Emp;
import com.deemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {

    int deleteByPrimaryKey(int id);

    int insert(Emp record);

    Emp selectByPrimaryKey(int id);

    List<Emp> selectAll();

    int updateByPrimaryKey(Emp record);

    Emp selectByPrimaryKeyWithDept(int id);

    List<Emp> selectAllWithDept();

    Emp checkName(String empName);
}