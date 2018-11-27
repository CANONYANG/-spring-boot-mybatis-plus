package com.njbandou.web.service.impl;

import com.njbandou.web.dto.ListDTO;
import com.njbandou.web.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: CANONYANG
 * Date: 2018/11/22
 * Describe: TODO
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void getPage() throws Exception {
        ListDTO listDTO = new ListDTO();
        listDTO.setPageNum(0);
        listDTO.setPageSize(10);
        listDTO.setKeywords("");
        adminService.getPage(listDTO);
    }

}