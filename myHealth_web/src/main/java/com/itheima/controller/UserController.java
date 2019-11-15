package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.mapping.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.DateUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:32 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;
    @RequestMapping("/getUserName")
    public Result findAllMuEn() {
        try {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_USERNAME_FAIL);
        }
    }
    //新增
    @RequestMapping("/add")
    public Result addUser(@RequestBody User user, Integer[] roles) {
        try {
            String birthday = user.getBirthday();
//            String format = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
            user.setBirthday(birthday);
            userService.addUser(user,roles);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_USERNAME_FAIL);
        }
        return new Result(true, MessageConstant.ADD_USERNAME_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            User user = userService.findById(id);
            return new Result(true, MessageConstant.QUERY_USERNAME_SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_USERNAME_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = userService.findPage(queryPageBean);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody User user, Integer[] roles) {
        try {
            userService.edit(user, roles);
            return new Result(true, MessageConstant.EDIT_USERNAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_USERNAME_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Role> RoleList= userService.findAll();
        if (RoleList != null && RoleList.size() > 0) {
            return new Result(true, MessageConstant.QUERY_USERNAMEROLER_SUCCESS, RoleList);
        } else {

            return new Result(false, MessageConstant.QUERY_USERNAMEROLER_FAIL);
        }
    }
    @RequestMapping("/findUser")
    public Result findUser(Integer id) {
        try {
            List<Integer> list=userService.findUser(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
