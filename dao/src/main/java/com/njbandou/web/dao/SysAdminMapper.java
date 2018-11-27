package com.njbandou.web.dao;

import com.njbandou.web.entity.SysAdmin;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CANONYANG
 * @since 2018-11-20
 */
@Mapper
@Component(value = "sysAdminMapper")
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    @Select("SELECT * FROM sys_admin WHERE delete_flag = 0 AND NAME = #{username}")
    SysAdmin findByName(String username);

    @Select("SELECT * FROM sys_admin WHERE delete_flag = 0 AND phone = #{phone}")
    SysAdmin findByPhone(String phone);
}
