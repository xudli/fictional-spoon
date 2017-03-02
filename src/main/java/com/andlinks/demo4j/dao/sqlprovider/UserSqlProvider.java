package com.andlinks.demo4j.dao.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by 王凯斌 on 2017/3/1.
 * user的SqlProvider类
 */
public class UserSqlProvider {

    public String updateRoles(Map<String, Object> para){

        String userUuid = (String)para.get("userUuid");
        String[] roleUuids = (String[])para.get("roleUuids");
        StringBuffer sql = new StringBuffer("insert into user_role (user_uuid, role_uuid) values ");
        for(String roleUuid:roleUuids){
            sql.append("(");
            sql.append(String.join(",",userUuid,roleUuid));
            sql.append("),");
        }
        sql.deleteCharAt(sql.length()-1);
        return sql.toString();
    }

    public String remove(){
        return new SQL()
                .UPDATE("user")
                .SET("is_deleted = 1","modify_time=now()","version=version+1")
                .WHERE("uuid = #{uuid}")
                .toString();
    }
}
