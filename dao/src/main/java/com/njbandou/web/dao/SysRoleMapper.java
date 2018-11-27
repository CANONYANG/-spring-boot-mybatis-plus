package com.njbandou.web.dao;

import com.njbandou.web.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
@Component(value = "sysRoleMapper")
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
