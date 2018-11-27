package com.njbandou.web.service;

import com.njbandou.web.dto.AdminDTO;
import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.result.RestResult;

/**
 * Author: CANONYANG
 * Date: 2018/11/21
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public interface AdminService {

    RestResult login(AdminDTO adminDTO);

    RestResult getPage(ListDTO listDTO);

    RestResult add(AdminDTO adminDTO);

    RestResult update(AdminDTO adminDTO);

    RestResult delete(Integer[] ids);

    RestResult updatePassword(AdminDTO adminDTO);

    RestResult unlock(AdminDTO adminDTO);
}
