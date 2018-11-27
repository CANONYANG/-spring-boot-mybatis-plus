package com.njbandou.web.controller;

import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.result.RestResultBuilder;
import com.njbandou.web.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@RestController
@RequestMapping(value = "/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:list")
    public RestResult list(@Valid ListDTO listDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new RestResultBuilder<>().error(bindingResult.getFieldError().getDefaultMessage());
        }
        return roleService.getPage(listDTO);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:save")
    public RestResult add(@RequestBody Map map) {
        String roleName = String.valueOf(map.get("roleName"));
        String remark = String.valueOf(map.get("remark"));
        List<Integer> menuList = (List<Integer>) map.get("menuIdList");
        roleService.add(roleName, remark, menuList);
        return new RestResultBuilder<>().success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:update")
    public RestResult update(@RequestBody Map map) {
        Integer pkId = Integer.parseInt(String.valueOf(map.get("pkId")));
        String roleName = String.valueOf(map.get("roleName"));
        String remark = String.valueOf(map.get("remark"));
        List<Integer> menuList = (List<Integer>) map.get("menuIdList");
        roleService.update(pkId, roleName, remark, menuList);
        return new RestResultBuilder<>().success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:delete")
    public RestResult delete(@RequestBody Integer[] pkIds) {
        return roleService.delete(pkIds);
    }

    @RequestMapping(value = "/info/{roleId}")
    @RequiresPermissions({"sys:role:list", "sys:role:info"})
    public RestResult info(@PathVariable Integer roleId) {
        return roleService.info(roleId);
    }
}
