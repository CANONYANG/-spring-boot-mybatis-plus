package com.njbandou.web.service;

import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.result.RestResult;

import java.util.List;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public interface RoleService {

    RestResult getPage(ListDTO listDTO);

    void add(String name, String remark, List<Integer> menuIds);

    void update(Integer roleId, String name, String remark, List<Integer> menuIds);

    RestResult delete(Integer[] ids);

    RestResult info(Integer roleId);
}
