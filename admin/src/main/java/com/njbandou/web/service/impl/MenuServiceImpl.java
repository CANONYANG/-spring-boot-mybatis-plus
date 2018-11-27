package com.njbandou.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.njbandou.web.dao.SysMenuMapper;
import com.njbandou.web.dto.MenuDTO;
import com.njbandou.web.entity.SysMenu;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.result.RestResultBuilder;
import com.njbandou.web.service.MenuService;
import com.njbandou.web.vo.BaseResultVO;
import com.njbandou.web.vo.MenuResultVO;
import com.njbandou.web.vo.NavigationResult;
import com.njbandou.web.vo.NavigationResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired

    private SysMenuMapper sysMenuMapper;

    @Override
    public RestResult getAll() {
        EntityWrapper<SysMenu> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("delete_flag={0}",0);
        Page<SysMenu> page = new Page<>();
        List<SysMenu> menus = sysMenuMapper.selectPage(page,entityWrapper);
        List<MenuResultVO> result = new ArrayList<>();
        for (SysMenu menu : menus) {
            MenuResultVO menuResultVO = MenuResultVO.fromMenu(menu);
            result.add(menuResultVO);
        }
        return new RestResultBuilder<>().setData(new BaseResultVO<>(Long.valueOf(page.getTotal()),page.getPages(),result)).build();
    }

    @Override
    public NavigationResult getNav(Integer adminId) {
        List<SysMenu> tempList = sysMenuMapper.findByAdminId(adminId);
        List<SysMenu> menus = new ArrayList<>();
        menus.addAll(tempList);
        List<NavigationResultVO> navigationResultVOS = buildByRecursive(menus);
        Set<String> permissions = new HashSet<>();
        menus.forEach(item -> {
            if (!StringUtils.isEmpty(item.getPerms())) {
                String[] PermissionsArray = item.getPerms().split(",");
                for (String permisson : PermissionsArray) {
                    permissions.add(permisson);
                }
            }
        });

        NavigationResult result = new NavigationResult(navigationResultVOS, permissions);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult addMenu(MenuDTO menuDTO) {
        //数据校验
        if(menuDTO.getParentId() == null){
            return new RestResultBuilder<>().error("菜单类型为空");
        }
        if(menuDTO.getName() == null || menuDTO.getName().equals("")){
            return new RestResultBuilder<>().error("菜单名为空");
        }
        if(menuDTO.getTitle() == null || menuDTO.getTitle().equals("")){
            return new RestResultBuilder<>().error("菜单标题为空");
        }
        if(menuDTO.getOrderNum() == null || menuDTO.getOrderNum().equals("")){
            return new RestResultBuilder<>().error("菜单排序号为空");
        }

        SysMenu sysMenu = new SysMenu();
        //一级菜单
        if(menuDTO.getParentId() == 0){
            sysMenu.setParentId(0);
            sysMenu.setOrderNum(menuDTO.getOrderNum());
            sysMenu.setType(0);
        }else {
            //次级菜单
            sysMenu.setParentId(menuDTO.getParentId());
            SysMenu sysMenuInfo = sysMenuMapper.selectById(menuDTO.getParentId());
            if(sysMenuInfo == null){
                return new RestResultBuilder<>().error("一级菜单不存在");
            }
            sysMenu.setPath(sysMenuInfo.getName());
            sysMenu.setOrderNum(0);
            sysMenu.setType(1);
        }
        Date date = new Date();
        sysMenu.setName(menuDTO.getName());
        sysMenu.setTitle(menuDTO.getTitle());
        sysMenu.setPerms("compose");
        sysMenu.setDeleteFlag(0);
        sysMenu.setCreateTime(date);
        sysMenu.setUpdateTime(date);
        sysMenuMapper.insertAllColumn(sysMenu);
        return new RestResultBuilder<>().success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateMenu(MenuDTO menuDTO) {
        //数据校验
        if(menuDTO.getParentId() == null){
            return new RestResultBuilder<>().error("菜单类型为空");
        }
        if(menuDTO.getName() == null || menuDTO.getName().equals("")){
            return new RestResultBuilder<>().error("菜单名为空");
        }
        if(menuDTO.getTitle() == null || menuDTO.getTitle().equals("")){
            return new RestResultBuilder<>().error("菜单标题为空");
        }
        if(menuDTO.getOrderNum() == null || menuDTO.getOrderNum().equals("")){
            return new RestResultBuilder<>().error("菜单排序号为空");
        }

        SysMenu sysMenu = sysMenuMapper.selectById(menuDTO.getPkId());
        if(sysMenu != null){
            //一级菜单
            if(menuDTO.getParentId() == 0){
                sysMenu.setOrderNum(menuDTO.getOrderNum());
            }else {
                //次级菜单
                sysMenu.setParentId(menuDTO.getParentId());
                SysMenu sysMenuInfo = sysMenuMapper.selectById(menuDTO.getParentId());
                if(sysMenuInfo == null){
                    return new RestResultBuilder<>().error("一级菜单不存在");
                }
                sysMenu.setPath(sysMenuInfo.getName());
            }
            sysMenu.setName(menuDTO.getName());
            sysMenu.setTitle(menuDTO.getTitle());
            sysMenu.setUpdateTime(new Date());
            sysMenuMapper.updateById(sysMenu);
        }
        return new RestResultBuilder<>().success();
    }

    @Override
    public RestResult getFirstMenu() {
        EntityWrapper<SysMenu> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("delete_flag={0}",0);
        List<SysMenu> sysMenus = sysMenuMapper.selectList(entityWrapper);
        List list = new ArrayList();
        for (SysMenu sysMenu : sysMenus){
            if(sysMenu.getParentId() == 0){
                list.add(sysMenu);
            }
        }
        return new RestResultBuilder<>().success().setData(list);
    }

    @Override
    public RestResult delete(Integer[] ids) {
        SysMenu role;
        for (Integer id : ids) {
            role = sysMenuMapper.selectById(id);
            if (role != null) {
                role.setDeleteFlag(1);
                sysMenuMapper.updateById(role);
            }
        }
        return new RestResultBuilder<>().success();
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public List<NavigationResultVO> buildByRecursive(List<SysMenu> treeNodes) {
        List<NavigationResultVO> trees = new ArrayList<>();
        for (SysMenu treeNode : treeNodes) {
            if (treeNode.getParentId() == 0) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static NavigationResultVO findChildren(SysMenu treeNode, List<SysMenu> treeNodes) {
        NavigationResultVO result = NavigationResultVO.fromMenu(treeNode);
        for (SysMenu it : treeNodes) {
            if (treeNode.getParentId() != null && treeNode.getPkId().longValue() == it.getParentId().longValue()) {
                result.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return result;
    }
}
