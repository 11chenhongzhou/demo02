package com.itheima.service;

import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:24 2019/11/13
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface MenuManageService {
    List<Menu> findAll();
//
//    void addCheckItem(CheckGroup checkGroup, Integer[] checkitemIds);
//
//    CheckGroup findById(Integer id);
//
//    PageResult findPage(QueryPageBean queryPageBean);
}
