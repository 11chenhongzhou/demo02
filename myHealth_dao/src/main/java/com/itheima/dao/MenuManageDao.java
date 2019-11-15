package com.itheima.dao;

import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:31 2019/11/13
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface MenuManageDao {
    @Select("select*from t_menu tm,t_role_menu trm where trm.menu_id=tm.id and tm.parentMenuId IS not NULL")
    List<Menu> findAll();
}
