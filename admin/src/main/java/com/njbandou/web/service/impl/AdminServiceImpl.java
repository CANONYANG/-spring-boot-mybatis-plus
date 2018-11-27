package com.njbandou.web.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.njbandou.web.JWTUtils;
import com.njbandou.web.constant.AdminConstant;
import com.njbandou.web.dao.SysAdminMapper;
import com.njbandou.web.dao.SysAdminRoleMapper;
import com.njbandou.web.dto.AdminDTO;
import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.entity.SysAdmin;
import com.njbandou.web.entity.SysAdminRole;
import com.njbandou.web.exception.ApiException;
import com.njbandou.web.redis.JWTRedisDAO;
import com.njbandou.web.result.RestResult;
import com.njbandou.web.result.RestResultBuilder;
import com.njbandou.web.service.AdminService;
import com.njbandou.web.shiro.EncryptUtils;
import com.njbandou.web.vo.AdminResultVO;
import com.njbandou.web.vo.BaseResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: CANONYANG
 * Date: 2018/11/21
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Autowired
    private SysAdminRoleMapper sysAdminRoleMapper;

    @Override
    public RestResult login(AdminDTO adminDTO) {
        SysAdmin admin = sysAdminMapper.findByName(adminDTO.getUsername());
        //密码校验
        if (admin == null || !EncryptUtils.encrypt(admin.getName(), adminDTO.getPassword(), admin.getSalt()).equals(admin.getPassword())) {
            return new RestResultBuilder<>().error("帐号不存在或密码错误");
        }
        if (admin.getStatus() == 1) {
            return new RestResultBuilder<>().error("管理员已被禁用");
        }

        //登录token
        Map domain = new HashMap();
        domain.put("pkId", admin.getPkId() + "");
        domain.put("username", adminDTO.getUsername());
        domain.put("time", System.currentTimeMillis());
        String token = JWTUtils.generateToken(AdminConstant.JWT_SECRET, domain);
        jwtRedisDAO.set(AdminConstant.SysAdmin_JWT_PREFIX + adminDTO.getUsername(), token);

        //登录用户信息返回
        Map resultMap = new HashMap();
        resultMap.put("pkId", admin.getPkId());
        resultMap.put("username", adminDTO.getUsername());
        resultMap.put("token", token);
        return new RestResultBuilder<>().success().setData(resultMap);
    }

    @Override
    public RestResult getPage(ListDTO listDTO) {

        SysAdmin sysAdmin = new SysAdmin();
        BeanUtils.copyProperties(listDTO,sysAdmin);
        Page<SysAdmin> page = new Page<>(listDTO.getPageNum(),listDTO.getPageSize());
        EntityWrapper<SysAdmin> entityWrapper = new EntityWrapper<>(sysAdmin);
        entityWrapper
                .where("delete_flag={0}",0)
//                .like("name",listDTO.getKeywords(),SqlLike.DEFAULT)
//                .or()
//                .like("phone",listDTO.getKeywords(),SqlLike.DEFAULT)
                .orderBy("create_time",true);
        System.out.println(entityWrapper.getSqlSegment());
        List<SysAdmin> adminList = sysAdminMapper.selectPage(page,entityWrapper);

        SysAdminRole sysAdminRole;
        Integer roleIds;
        List<AdminResultVO> results = new ArrayList<>();
        for (SysAdmin admin : adminList) {
            sysAdminRole = sysAdminRoleMapper.findByAdminId(admin.getPkId());
            roleIds = sysAdminRole.getRoleId();
            results.add(AdminResultVO.fromAdmin(admin,roleIds));
        }
        return new RestResultBuilder<>().setData(new BaseResultVO<>(Long.valueOf(page.getTotal()),page.getPages(),results)).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult add(AdminDTO adminDTO) {
        SysAdmin oldAdmin = sysAdminMapper.findByName(adminDTO.getUsername());
        if (oldAdmin != null) {
            return new RestResultBuilder<>().error("账号已存在");
        }
        oldAdmin = sysAdminMapper.findByPhone(adminDTO.getMobile());
        if(oldAdmin != null){
            return new RestResultBuilder<>().error("手机号码已存在");
        }

        //创建对象
        Date date = new Date();
        SysAdmin admin = new SysAdmin();
        admin.setName(adminDTO.getUsername());
        admin.setPhone(adminDTO.getMobile());
        admin.setCreateTime(date);
        admin.setUpdateTime(date);
        admin.setDeleteFlag(0);
        admin.setStatus(adminDTO.getStatus());

        String newSalt = EncryptUtils.createSalt();
        String savePassword = EncryptUtils.encrypt(admin.getName(), adminDTO.getPassword(), newSalt);
        admin.setSalt(newSalt);
        admin.setPassword(savePassword);
        sysAdminMapper.insertAllColumn(admin);

        SysAdminRole sysAdminRole = new SysAdminRole();
        sysAdminRole.setAdminId(admin.getPkId());
        sysAdminRole.setRoleId(adminDTO.getRoleId());
        sysAdminRole.setDeleteFlag(0);
        sysAdminRole.setCreateTime(date);
        sysAdminRoleMapper.insertAllColumn(sysAdminRole);

        return new RestResultBuilder<>().success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(AdminDTO adminDTO) {
        SysAdmin admin = sysAdminMapper.selectById(adminDTO.getPkId());
        SysAdmin oldAdmin = sysAdminMapper.findByPhone(adminDTO.getMobile());
        if (oldAdmin != null && !adminDTO.getPkId().equals(oldAdmin.getPkId())) {
            return new RestResultBuilder<>().error("该手机号码已存在！");
        }

        Date date = new Date();
        admin.setPhone(adminDTO.getMobile());
        admin.setUpdateTime(date);
        admin.setStatus(adminDTO.getStatus());
        // 密码填了才修改
        if (!StringUtils.isEmpty(adminDTO.getPassword())) {
            String newSalt = EncryptUtils.createSalt();
            String savePassword = EncryptUtils.encrypt(admin.getName(), adminDTO.getPassword(), newSalt);
            admin.setSalt(newSalt);
            admin.setPassword(savePassword);
        }

        sysAdminMapper.updateById(admin);
        SysAdminRole sysAdminRole = sysAdminRoleMapper.findByAdminId(adminDTO.getPkId());
        sysAdminRole.setUpdateTime(date);
        sysAdminRole.setRoleId(adminDTO.getRoleId());
        sysAdminRoleMapper.updateById(sysAdminRole);

        return new RestResultBuilder<>().success();
    }

    @Override
    public RestResult delete(Integer[] ids) {
        SysAdmin admin;
        for (int i = 0; i < ids.length; i++) {
            admin = sysAdminMapper.selectById(ids[i]);
            if (admin != null) {
                SysAdminRole adminRole = sysAdminRoleMapper.findByAdminId(admin.getPkId());
                if(adminRole.getRoleId() == 1){
                    return new RestResultBuilder<>().error("超级管理员无法删除");
                }
                admin.setDeleteFlag(1);
                sysAdminMapper.updateById(admin);
            }
        }
        return new RestResultBuilder<>().success();
    }

    @Override
    public RestResult updatePassword(AdminDTO adminDTO) {
        SysAdmin admin = sysAdminMapper.findByName(adminDTO.getUsername());
        if(admin == null){
            return new RestResultBuilder<>().error("用户不存在");
        }
        String encryptPassword = EncryptUtils.encrypt(admin.getName(), adminDTO.getPassword(), admin.getSalt());

        if (encryptPassword.equals(admin.getPassword())) {
            String newSalt = EncryptUtils.createSalt();
            String savePassword = EncryptUtils.encrypt(admin.getName(), adminDTO.getNewPassword(), newSalt);
            admin.setSalt(newSalt);
            admin.setPassword(savePassword);
            admin.setUpdateTime(new Date());
            sysAdminMapper.updateById(admin);
        } else {
            return new RestResultBuilder<>().error("原始密码错误！");
        }
        return new RestResultBuilder<>().success();
    }

    @Override
    public RestResult unlock(AdminDTO adminDTO) {
        SysAdmin admin = sysAdminMapper.findByName(adminDTO.getUsername());
        if(admin == null){
            return new RestResultBuilder<>().error("用户不存在");
        }
        String encryptPassword = EncryptUtils.encrypt(admin.getName(), adminDTO.getPassword(), admin.getSalt());
        if (!encryptPassword.equals(admin.getPassword())) {
            return new RestResultBuilder<>().error("密码错误,请重新输入！");
        }
        return new RestResultBuilder<>().success();
    }
}
