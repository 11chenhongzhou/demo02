package com.itheima.health.service.Impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:50 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserName(String username) {
        User user = userDao.findUserName(username);
        if (user != null) {
            Set<Role> roleSet= roleDao.findRoleList(user.getId());
            if (roleSet!=null&&roleSet.size()>0){
                for (Role role : roleSet) {
                    Set<Permission> permissionSet=permissionDao.findPermissionList(role.getId());
                    if (permissionSet!=null&&permissionSet.size()>0) {
                            role.setPermissions(permissionSet);
                    }
                }
                user.setRoles(roleSet);
            }
        }
        return user;
    }

    @Override
    public void addUser(User user, Integer[] roles) {
        userDao.addUser(user);
        addUserRoles(user.getId(),roles);
    }

    @Override
    public List<Integer> findUser(Integer id) {
        return userDao.findUser(id);
    }

    private void addUserRoles(Integer user_id, Integer[] roles) {
        if (roles!=null&&roles.length>0){
            for (Integer role_id : roles) {
                Map<String,Integer> map=new HashMap<>();
                map.put("user_id",user_id);
                map.put("role_id",role_id);
                userDao.addUserRoles(map);
            }
        }
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<User> userPage=userDao.findPage(queryPageBean.getQueryString());
        PageResult pageResult=new PageResult(userPage.getTotal(),userPage.getResult());
        return pageResult;
    }

    @Override
    public void edit(User user, Integer[] roles) {
        userDao.updateUser(user);
        userDao.delete(user.getId());
        addUserRoles(user.getId(),roles);
    }

    @Override
    public List<Role> findAll() {
        return userDao.findAll();
    }
}
