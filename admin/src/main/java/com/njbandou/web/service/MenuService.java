package com.njbandou.web.service;

import com.njbandou.web.dto.MenuDTO;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.vo.NavigationResult;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public interface MenuService {

    RestResult getAll();

    NavigationResult getNav(Integer adminId);

    RestResult addMenu(MenuDTO menuDTO);

    RestResult updateMenu(MenuDTO menuDTO);

    RestResult getFirstMenu();

    RestResult delete(Integer[] ids);
}
