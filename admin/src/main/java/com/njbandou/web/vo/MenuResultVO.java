package com.njbandou.web.vo;

import com.njbandou.web.entity.SysMenu;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class MenuResultVO {

    private Integer pkId;

    private Integer parentId;

    private String name;

    private String title;

    private String path;

    private Integer orderNum;

    private String open;

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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "MenuResultVO{" +
                "pkId=" + pkId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", orderNum=" + orderNum +
                ", open='" + open + '\'' +
                '}';
    }

    public static MenuResultVO fromMenu(SysMenu menu){

        MenuResultVO item = new MenuResultVO();
        item.setPkId(menu.getPkId());
        item.setName(menu.getName());
        item.setOrderNum(menu.getOrderNum());
        item.setParentId(menu.getParentId());
        item.setPath(menu.getPath());
        item.setTitle(menu.getTitle());
        return item;
    }
}
