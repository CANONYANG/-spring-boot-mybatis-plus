package com.njbandou.web.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.njbandou.web.dao.SysMenuMapper;
import com.njbandou.web.dao.SysRoleMapper;
import com.njbandou.web.dao.SysRoleMenuMapper;
import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.entity.SysRole;
import com.njbandou.web.entity.SysRoleMenu;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.result.RestResultBuilder;
import com.njbandou.web.service.RoleService;
import com.njbandou.web.vo.BaseResultVO;
import com.njbandou.web.vo.RoleResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public RestResult getPage(ListDTO listDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRole,listDTO);
        Page<SysRole> page = new Page<>(listDTO.getPageNum(),listDTO.getPageSize());
        EntityWrapper<SysRole> entityWrapper = new EntityWrapper<>();
        entityWrapper
                .where("delete_flag={0}",0)
                .like("name",listDTO.getKeywords(), SqlLike.DEFAULT)
                .orderBy("create_time",true);
        List<SysRole> roles = sysRoleMapper.selectPage(page,entityWrapper);

        List<RoleResultVO> results = roles.stream().map(item -> RoleResultVO.fromRole(item)).collect(Collectors.toList());
        return new RestResultBuilder<>().setData(new BaseResultVO<>(Long.valueOf(page.getTotal()),page.getPages(),results)).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(String name, String remark, List<Integer> menuIds) {
        Date date = new Date();

        SysRole role = new SysRole();
        role.setName(name);
        role.setDes(remark);
        role.setCreateTime(date);
        role.setUpdateTime(date);
        role.setDeleteFlag(0);
        sysRoleMapper.insertAllColumn(role);
        List list = new ArrayList();
        for (Integer menuId : menuIds) {
            Integer id  = sysMenuMapper.getParentIds(menuId);
            list.add(id);
        }
        // 查找父节点
        menuIds.addAll(list);
        Set<Integer> allMenuIds = new HashSet<>();
        allMenuIds.addAll(menuIds);
        List<SysRoleMenu> roleMenus = fromRequest(role.getPkId(), allMenuIds);
        if (roleMenus != null && roleMenus.size() > 0) {
            for (SysRoleMenu roleMenu : roleMenus) {
                sysRoleMenuMapper.insert(roleMenu);
            }
        }
    }

    @Override
    public void update(Integer roleId, String name, String remark, List<Integer> menuIds) {
        Date date = new Date();

        SysRole role = sysRoleMapper.selectById(roleId);
        role.setName(name);
        role.setDes(remark);
        role.setUpdateTime(date);
        sysRoleMapper.updateById(role);

        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectByRoleId(roleId);
        for (SysRoleMenu sysRoleMenu : sysRoleMenuList){
            sysRoleMenuMapper.deleteById(sysRoleMenu.getPkId());
        }
        List list = new ArrayList();
        for (Integer menuId : menuIds) {
            Integer id  = sysMenuMapper.getParentIds(menuId);
            list.add(id);
        }
        if(list.size() != 0){
            menuIds.addAll(list);
        }
        // 查找父节点
        Set<Integer> allMenuIds = new HashSet<>();
        allMenuIds.addAll(menuIds);
        List<SysRoleMenu> roleMenus = fromRequest(role.getPkId(), allMenuIds);
        if (roleMenus.size() > 0) {
            for (SysRoleMenu roleMenu : roleMenus) {
                sysRoleMenuMapper.insert(roleMenu);
            }
        }
    }

    @Override
    public RestResult delete(Integer[] ids) {
        SysRole role;
        for (Integer id : ids) {
            role = sysRoleMapper.selectById(id);
            if (role != null) {
                role.setDeleteFlag(1);
                sysRoleMapper.updateById(role);
            }
        }
        return new RestResultBuilder<>().success();
    }

    @Override
    public RestResult info(Integer roleId) {
        List<SysRoleMenu> menus = sysRoleMenuMapper.selectByRoleId(roleId);
        Set<Integer> pkIds = new HashSet<>();
        menus.forEach(item -> pkIds.add(item.getMenuId()));
        Map map = new HashMap();
        map.put("menuIdList", pkIds);
        return new RestResultBuilder<>().success().setData(map);
    }

    protected List<SysRoleMenu> fromRequest(Integer roleId, Set<Integer> menuIds) {
        Date date = new Date();
        List<SysRoleMenu> roleMenus = new ArrayList<>();
        SysRoleMenu roleMenu;
        for (Integer menuId : menuIds) {
            roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setDeleteFlag(0);
            roleMenu.setMenuId(menuId);
            roleMenu.setCreateTime(date);
            roleMenu.setUpdateTime(date);
            roleMenus.add(roleMenu);
        }
        return roleMenus;
    }
}
