package com.njbandou.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Author: CANONYANG
 * Date: 2018/11/21
 * Describe: 列表查询公共类
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class ListDTO {

    @Min(value = 0,message = "当前页必须大于等于0")
    @NotNull(message = "当前页不能为空")
    private Integer pageNum;

    @Min(value = 1,message = "每页数量必须大于0")
    @NotNull(message = "每页数量不能为空")
    private Integer pageSize;

    private String keywords;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "ListDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
