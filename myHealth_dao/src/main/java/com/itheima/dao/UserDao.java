package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 14:42 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface UserDao {
    @Select("select*from t_user where username=#{username}")
    User findUserName(String username);
    @Insert("insert into t_user (username,birthday,gender,telephone,station,remark) values(#{username},#{birthday},#{gender},#{telephone},#{station},#{remark})")
    @SelectKey(statement ="SELECT LAST_INSERT_ID()" ,keyColumn = "id",keyProperty = "id", before = false, resultType =Integer.class )
    void addUser(User user);
    @Insert("insert into t_user_role(user_id,role_id) values(#{user_id},#{role_id})")
    void addUserRoles(Map<String, Integer> map);
    @Select("select *from t_user where id=#{id}")
    User findById(Integer id);
    @Select("<script>select*from t_user <if test='value!=null and value.length>0'>where username=#{value} or gender=#{value} or telephone=#{value}</if></script>")
    Page<User> findPage(String queryString);
    @Update("<script>update t_user " +
            "<set>" +
            "<if test='username!=null'>" +
            "username=#{username}," +
            "</if>" +
            "<if test='birthday!=null'>" +
            "birthday=#{birthday}," +
            "</if>" +
            "<if test='gender!=null'>" +
            "gender=#{gender}," +
            "</if>" +
            "<if test='telephone!=null'>" +
            "telephone=#{telephone}," +
            "</if>" +
            "<if test='station!=null'>" +
            "station=#{station}," +
            "</if>" +
            "<if test='remark!=null'>" +
            "remark=#{remark}," +
            "</if>" +
            "</set>" +
            "<where>" +
            "id=#{id}" +
            "</where>" +
            "</script>")
    void updateUser(User user);
    @Delete("delete from t_user_role where user_id=#{id}")
    void delete(Integer id);
    @Select("select *from t_role")
    List<Role> findAll();
    @Select("select role_id from t_user_role where user_id=#{id}")
    List<Integer> findUser(Integer id);
}
