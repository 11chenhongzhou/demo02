package com.itheima.service;

import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:49 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface UserService {
    User findUserName(String username);

    User findById(Integer id);

    PageResult findPage(QueryPageBean queryPageBean);

    void edit(User user, Integer[] roles);

    List<Role> findAll();
    void addUser(User user, Integer[] roles);

    List<Integer> findUser(Integer id);
}
