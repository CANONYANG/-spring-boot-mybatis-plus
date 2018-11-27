package com.njbandou.web.dto;

import java.util.List;

/**
 * Author: CANONYANG
 * Date: 2018/11/23
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class AdminDTO {

    private Integer pkId;

    private String username;

    private String password;

    private String newPassword;

    private String mobile;

    private int status;

    private int roleId;

    private int deleteFlag;

    private List<Integer> roleIdList;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "pkId=" + pkId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", roleId=" + roleId +
                ", deleteFlag=" + deleteFlag +
                ", roleIdList=" + roleIdList +
                '}';
    }
}
