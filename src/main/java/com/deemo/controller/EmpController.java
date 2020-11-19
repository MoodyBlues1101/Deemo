package com.deemo.controller;


import com.deemo.entity.User;
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
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public JsonMsg findAllInJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        //startPage 后面紧跟的就是一个分页查询
        List<User> users = userService.selectAll();
        //用pageinfo封装
        PageInfo<User> pages = new PageInfo(users, 5);
        return JsonMsg.success().addInfo("pageInfo", pages);
    }

    //废弃
//    @RequestMapping("/user")
    public String selectAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 5);
        //startPage 后面紧跟的就是一个分页查询
        List<User> users = userService.selectAll();
        //用pageinfo封装
        PageInfo<User> pages = new PageInfo(users, 5);
        model.addAttribute("pageInfo", pages);
        return "list";
    }

    /**
     * 登录：跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 对登录页面输入的用户名和密码做简单的判断
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg dologin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + password);
        if (!"admin1234".equals(username + password)) {
            return JsonMsg.fail().addInfo("login_error", "输入账号用户名与密码不匹配，请重新输入！");
        }
        return JsonMsg.success();
    }

    @RequestMapping(value = "deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return userService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(User record) {
        return userService.insert(record);
    }

    public User selectByPrimaryKey(String id) {
        return userService.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(User record) {
        return userService.updateByPrimaryKey(record);
    }
}