package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MenuManageDao;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:28 2019/11/13
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = MenuManageService.class)
@Transactional
public class MenuManageServiceImpl implements MenuManageService{
    @Autowired
    private MenuManageDao menuManageDao;

    @Override
    public List<Menu> findAll() {
        return menuManageDao.findAll();
    }
}
