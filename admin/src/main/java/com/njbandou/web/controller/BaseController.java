package com.njbandou.web.controller;

import com.njbandou.web.JWTUtils;
import com.njbandou.web.constant.AdminConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Company: NJBandou
 * @Author: D丶Cheng
 * @Description:
 * @Date: 2018/4/23 下午3:34
 */
public class BaseController {

    public Integer getAdminIdFromHeader(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        Map map = JWTUtils.getClaims(AdminConstant.JWT_SECRET, token);

        Integer adminId = Integer.valueOf((String) map.get("pkId"));

        return adminId;
    }

}
