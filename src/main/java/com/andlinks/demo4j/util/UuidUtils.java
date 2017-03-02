package com.andlinks.demo4j.util;

import java.util.UUID;

/**
 * Created by 王凯斌 on 2017/3/2.
 * uuid相关的util类
 */
public class UuidUtils {

    private final static String SEPARATOR = "-";

    /*
    获得根据类获得uuid的方法
     */
    public static String getUUID(Class clazz){

        return String.join(SEPARATOR, clazz.getSimpleName(),UUID.randomUUID().toString());
    }
}
