package org.deepinfo.proton.util;

import java.util.UUID;
/**
 * Created by lixudong on 2017/4/24.
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
