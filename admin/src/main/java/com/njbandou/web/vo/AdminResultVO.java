package com.njbandou.web.vo;

import com.njbandou.web.DateUtils;
import com.njbandou.web.entity.SysAdmin;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class AdminResultVO {

    private Integer pkId;

    private String username;

    private String mobile;

    private Integer status;

    private Integer roleIds;

    private String createTime;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer roleIds) {
        this.roleIds = roleIds;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AdminResultVO{" +
                "pkId=" + pkId +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", roleIds=" + roleIds +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public static AdminResultVO fromAdmin(SysAdmin admin, Integer roleIds){

        AdminResultVO result = new AdminResultVO();
        result.setPkId(admin.getPkId());
        result.setMobile(admin.getPhone());
        result.setUsername(admin.getName());
        result.setRoleIds(roleIds);
        result.setStatus(admin.getStatus());
        result.setCreateTime(DateUtils.date2String(admin.getCreateTime()));
        return result;
    }
}
