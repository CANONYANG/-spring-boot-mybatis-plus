package com.njbandou.web.vo;

import com.njbandou.web.DateUtils;
import com.njbandou.web.entity.SysRole;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class RoleResultVO {

    private Integer pkId;

    private String roleName;

    private String remark;

    private String date;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RoleResultVO{" +
                "pkId=" + pkId +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public static RoleResultVO fromRole(SysRole role){
        RoleResultVO result = new RoleResultVO();
        result.setPkId(role.getPkId());
        result.setRemark(role.getDes());
        result.setRoleName(role.getName());
        result.setDate(DateUtils.date2String(role.getCreateTime()));
        return result;
    }
}
