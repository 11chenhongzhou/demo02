package com.itheima.service;

import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 20:38 2019/11/14
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface RoleService {
    void addUser(Role role, Integer[] permissions);

    List<Integer> findUser(Integer id);

    List<Permission> findAll();

    void edit(Role role, Integer[] permissions);

    PageResult findPage(QueryPageBean queryPageBean);

    Role findById(Integer id);
}
