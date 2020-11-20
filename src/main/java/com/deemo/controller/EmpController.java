package com.deemo.controller;


import com.deemo.entity.Emp;
import com.deemo.entity.User;
import com.deemo.service.EmpService;
import com.deemo.service.UserService;
import com.deemo.utils.JsonMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/emps")
    @ResponseBody
    public JsonMsg findAllInJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        //startPage 后面紧跟的就是一个分页查询
        List<Emp> emps = empService.selectAllWithDept();
        //用pageinfo封装
        PageInfo<Emp> pages = new PageInfo(emps, 5);
        return JsonMsg.success().addInfo("pageInfo", pages);
    }

}