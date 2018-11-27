package com.njbandou.web.dao;

import com.njbandou.web.entity.SysAdminRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author CANONYANG
 * @since 2018-11-20
 */
@Mapper
@Component(value = "sysAdminRoleMapper")
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {

    @Select("SELECT * FROM sys_admin_role WHERE admin_id = #{adminId} AND delete_flag = 0")
    SysAdminRole findByAdminId(Integer adminId);
}
