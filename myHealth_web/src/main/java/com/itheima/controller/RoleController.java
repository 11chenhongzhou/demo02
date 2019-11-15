package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.mapping.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 20:35 2019/11/14
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    private RoleService roleService;
    //新增
    @RequestMapping("/add")
    public Result addRole(@RequestBody Role role, Integer[] permissions) {
        try {
            roleService.addUser(role,permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_ROLE_SUCCESS);
        }
        return new Result(true, MessageConstant.ADD_ROLE_FAIL);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Role role = roleService.findById(id);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = roleService.findPage(queryPageBean);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Role role, Integer[] permissions) {
        try {
            roleService.edit(role, permissions);
            return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_ROLE_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Permission> permissionList= roleService.findAll();
        if (permissionList != null && permissionList.size() > 0) {
            return new Result(true, MessageConstant.QUERY_ROLEPERMISSION_SUCCESS, permissionList);
        } else {
            return new Result(false, MessageConstant.QUERY_ROLEPERMISSION_FAIL);
        }
    }
    @RequestMapping("/findUser")
    public Result findUser(Integer id) {
        try {
            List<Integer> list=roleService.findUser(id);
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }
}
