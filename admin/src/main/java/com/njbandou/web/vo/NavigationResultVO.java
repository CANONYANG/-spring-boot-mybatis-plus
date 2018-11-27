package com.njbandou.web.vo;

import com.njbandou.web.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class NavigationResultVO {

    private Integer pkId;

    private Integer parentId;

    private String name;

    private String title;

    private String path;

    private Integer type;

    private Integer orderNum;

    private String parentName;

    private String open;

    private List<NavigationResultVO> children = new ArrayList<>();

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public List<NavigationResultVO> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationResultVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "NavigationResultVO{" +
                "pkId=" + pkId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", parentName='" + parentName + '\'' +
                ", open='" + open + '\'' +
                ", children=" + children +
                '}';
    }

    public static NavigationResultVO fromMenu(SysMenu menu){

        if ( menu == null){

            System.out.println(menu);

        }

        NavigationResultVO result = new NavigationResultVO();
        result.setPkId(menu.getPkId());
        result.setName(menu.getName());
        result.setOrderNum(menu.getOrderNum());
        result.setPath(menu.getPath());
        result.setTitle(menu.getTitle());
        result.setType(menu.getType());
        result.setParentId(menu.getParentId());

        return result;
    }
}
