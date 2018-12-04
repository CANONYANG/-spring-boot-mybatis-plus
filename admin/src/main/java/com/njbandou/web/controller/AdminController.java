package com.njbandou.web.controller;

import com.njbandou.web.annotation.Authorization;
import com.njbandou.web.constant.AdminConstant;
import com.njbandou.web.dto.AdminDTO;
import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.redis.JWTRedisDAO;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.result.RestResultBuilder;
import com.njbandou.web.service.AdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Author: CANONYANG
 * Date: 2018/11/21
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@RestController
@RequestMapping("/sys/user")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @RequestMapping(value="login",method = RequestMethod.POST)
    public RestResult login(@RequestBody AdminDTO adminDTO){
        return adminService.login(adminDTO);
    }

    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public RestResult login(HttpSession session){
        return new RestResultBuilder<>().success();
    }

    @Authorization
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(@Valid ListDTO listDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new RestResultBuilder<>().error(bindingResult.getFieldError().getDefaultMessage());
        }
        return adminService.getPage(listDTO);
    }

    @Authorization
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("sys:manager:save")
    public RestResult add(@RequestBody AdminDTO adminDTO){
        return adminService.add(adminDTO);
    }

    @Authorization
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("sys:manager:update")
    public RestResult update(@RequestBody AdminDTO adminDTO){
        return adminService.update(adminDTO);
    }

    @Authorization
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:manager:delete")
    public RestResult delete(@RequestBody Integer[] userIds){
        return adminService.delete(userIds);
    }

    @Authorization
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @RequiresPermissions("sys:manager:updatePassword")
    public RestResult updatePassword(@RequestBody AdminDTO adminDTO){
        return adminService.updatePassword(adminDTO);
    }

    @Authorization
    @RequestMapping(value="/unlock",method = RequestMethod.POST)
    public RestResult unlock(@RequestBody AdminDTO adminDTO){
        return adminService.unlock(adminDTO);
    }
}