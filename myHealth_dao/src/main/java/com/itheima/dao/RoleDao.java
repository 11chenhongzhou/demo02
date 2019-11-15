package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 14:43 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface RoleDao {
    @Select("select*from t_role tr,t_user_role tur where tr.id=tur.role_id and tur.user_id=#{id}")
    Set<Role> findRoleList(Integer id);
    @Insert("insert into (name,keyword,description) values(#{name},#{keyword},#{description})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty ="id" , before = false, resultType =Integer.class)
    void addRole(Role role);
    @Insert("insert into ")
    void addRoles(Map<String, Integer> map);

    List<Integer> findUser(Integer id);

    List<Permission> findAll();

    void updateRole(Role role);

    void delete(Integer id);

    Page<Role> findPage(String queryString);

    Role findById(Integer id);
}
