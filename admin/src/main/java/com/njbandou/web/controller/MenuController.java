package com.njbandou.web.controller;

import com.njbandou.web.annotation.Authorization;
import com.njbandou.web.dto.MenuDTO;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.result.RestResultBuilder;
import com.njbandou.web.service.MenuService;
import com.njbandou.web.vo.NavigationResult;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController{

    @Autowired
    private MenuService menuService;

    @Authorization
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:list")
    public RestResult list() {
        return menuService.getAll();
    }

    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    @RequiresAuthentication
    public RestResult nav(HttpServletRequest request){
        Integer adminId = getAdminIdFromHeader(request);
        NavigationResult pageResult = menuService.getNav(adminId);
        return new RestResultBuilder<>().success().setData(pageResult);
    }

    @Authorization
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:save")
    public RestResult add(@RequestBody MenuDTO menuDTO){
        return menuService.addMenu(menuDTO);
    }

    @Authorization
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:update")
    public RestResult update(@RequestBody MenuDTO menuDTO){
        return menuService.updateMenu(menuDTO);
    }

    @Authorization
    @RequestMapping(value = "/getFirstMenu", method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:getFirstMenu")
    public RestResult getFirstMenu(){
        return menuService.getFirstMenu();
    }

    @Authorization
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:delete")
    public RestResult delete(@RequestBody Integer[] pkIds) {
        return menuService.delete(pkIds);
    }
}
