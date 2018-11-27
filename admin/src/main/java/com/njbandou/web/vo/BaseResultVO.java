package com.njbandou.web.vo;

/**
 * @author D丶Cheng
 * @Created with IntelliJ IDEA.
 * @date: 2018/10/23
 * @time: 10:17
 * @Description:  测试用例，仅供参考
 */
public class BaseResultVO<T> {

    /**
     * 总条数
     */
    private Long totalElements;

    /**
     * 一共几页
     */
    private Integer totalPages;

    private T data;

    public BaseResultVO() {
    }

    public BaseResultVO(Long totalElements, Integer totalPages, T data) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.data = data;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResultVO{" +
                "totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", data=" + data +
                '}';
    }
}
