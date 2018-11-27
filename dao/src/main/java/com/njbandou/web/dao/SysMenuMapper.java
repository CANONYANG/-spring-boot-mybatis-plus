package com.njbandou.web.dao;

import com.njbandou.web.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CANONYANG
 * @since 2018-11-20
 */
@Mapper
@Component(value = "sysMenuMapper")
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select(value = "SELECT * FROM sys_menu m, sys_role_menu rm,sys_admin_role am WHERE m.pk_id = rm.menu_id AND rm.role_id = am.role_id AND am.admin_id = #{adminId}")
    List<SysMenu> findByAdminId(Integer adminId);

    @Select(value = "SELECT DISTINCT m.parent_id from sys_menu m WHERE m.pk_id = #{menuId}")
    int getParentIds(Integer menuId);
}
