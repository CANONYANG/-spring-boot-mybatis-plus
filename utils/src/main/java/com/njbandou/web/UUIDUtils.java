package com.njbandou.web;

import java.util.UUID;

/**
 * UUID生成工具类
 */
public class UUIDUtils {
    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "").toString();
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
