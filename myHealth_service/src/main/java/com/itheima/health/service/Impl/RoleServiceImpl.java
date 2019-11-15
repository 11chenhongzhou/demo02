package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 20:52 2019/11/14
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public void addUser(Role role, Integer[] permissions) {
        roleDao.addRole(role);
        addRole(role.getId(),permissions);
    }

    private void addRole(Integer role_id, Integer[] permissions) {
        if (permissions!=null&&permissions.length>0){
            for (Integer permission_id : permissions) {
                Map<String,Integer> map=new HashMap<>();
                map.put("permission_id",permission_id);
                map.put("role_id",role_id);
                roleDao.addRoles(map);
            }
        }
    }

    @Override
    public List<Integer> findUser(Integer id) {
        return roleDao.findUser(id);
    }

    @Override
    public List<Permission> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void edit(Role role, Integer[] permissions) {
        roleDao.updateRole(role);
        roleDao.delete(role.getId());
        addRole(role.getId(),permissions);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Role> RolePage=roleDao.findPage(queryPageBean.getQueryString());
        PageResult pageResult=new PageResult(RolePage.getTotal(),RolePage.getResult());
        return pageResult;
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }
}
