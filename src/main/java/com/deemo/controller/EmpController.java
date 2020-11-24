package com.deemo.controller;


import com.deemo.entity.Dept;
import com.deemo.entity.Emp;
import com.deemo.entity.User;
import com.deemo.service.DeptService;
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
    @Autowired
    private DeptService deptService;

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

    @RequestMapping("/depts")
    @ResponseBody
    public JsonMsg findDept() {
        List<Dept> depts = deptService.selectAll();
        return JsonMsg.success().addInfo("depts", depts);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Emp emp) {
        int i = empService.insert(emp);
        return JsonMsg.success();
    }

    @ResponseBody
    @RequestMapping("/checkName")
    public JsonMsg checkName(@RequestParam("empName") String empName) {
        //先判断用户名是否是合法的表达式
        String regx = "(^[a-z0-9_-]{6,16}$)|(^[\\u2e80-\\u9fff]{2,5})";
        //true表示匹配成功，false表示失败
        if (!empName.matches(regx)) {
            return JsonMsg.fail().addInfo("va_msg", "请输入2-5位汉字或5-16数字字母组合");
        }
        //true表示没有重复
        boolean b = empService.checkName(empName);
        if (b) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail().addInfo("va_msg", "用户名不可用");
        }
    }
}