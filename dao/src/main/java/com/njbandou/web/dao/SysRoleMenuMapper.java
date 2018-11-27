package com.njbandou.web.dao;

import com.njbandou.web.entity.SysRoleMenu;
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
@Component(value = "sysRoleMenuMapper")
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    @Select(value = "SELECT\n" +
            "    *\n" +
            "    FROM sys_role_menu\n" +
            "    WHERE role_id = #{roleId} AND delete_flag = 0")
    List<SysRoleMenu> selectByRoleId(Integer roleId);
}
