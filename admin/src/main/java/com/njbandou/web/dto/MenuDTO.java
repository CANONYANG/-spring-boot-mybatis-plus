package com.njbandou.web.dto;

/**
 * Created by CANONYANG on 2018/7/11.
 */
public class MenuDTO extends ListDTO{

    private Integer pkId;

    private Integer parentId;

    private String name;

    private String title;

    private Integer orderNum;

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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "pkId=" + pkId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
}

