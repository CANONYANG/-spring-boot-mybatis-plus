package com.njbandou.web.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Company: NJBandou
 * @Author: D丶Cheng
 * @Description:
 * @Date: 2018/4/20 下午8:39
 */
public class NavigationResult {

    private List<NavigationResultVO> menuList = new ArrayList<>();

    private Set<String> permissions = new HashSet<>();

    public List<NavigationResultVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<NavigationResultVO> menuList) {
        this.menuList = menuList;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public NavigationResult(List<NavigationResultVO> menuList, Set<String> permissions) {
        this.menuList = menuList;
        this.permissions = permissions;
    }
}
