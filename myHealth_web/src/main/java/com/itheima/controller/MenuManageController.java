package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.mapping.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuManageService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:23 2019/11/13
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/menuManage")
public class MenuManageController {
    @Reference
    private MenuManageService menuManageService;
    //新增
//    @RequestMapping("/add")
//    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
//        try {
//            menuManageService.addCheckItem(checkGroup, checkitemIds);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
//        }
//        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
//    }
//
//    @RequestMapping("/findById")
//    public Result findById(Integer id) {
//        try {
//            CheckGroup checkGroup = menuManageService.findById(id);
//            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS, checkGroup);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
//        }
//    }
//
//    @RequestMapping("/findPage")
//    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
//        try {
//            PageResult pageResult = menuManageService.findPage(queryPageBean);
//            return pageResult;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @RequestMapping("/edit")
//    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
//        try {
//            menuManageService.edit(checkitemIds, checkGroup);
//            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
//        }
//    }

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Menu> menuList = menuManageService.findAll();
        if (menuList != null && menuList.size() > 0) {
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, menuList);
        } else {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
