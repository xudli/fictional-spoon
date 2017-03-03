package com.andlinks.demo4j.dao.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by 王凯斌 on 2017/3/2.
 * permission的SqlProvider类
 */
public class PermissionSqlProvider {

    public String getById(@Param("id")Long id){
        return new SQL()
                .SELECT("id, permission_name, create_time, modify_time, version, is_deleted, uuid")
                .FROM("permission")
                .WHERE("id = #{id}").toString();
    }

    public String getByUuid(){
        return new SQL()
                .SELECT("id, permission_name, create_time, modify_time, version, is_deleted, uuid")
                .FROM("permission")
                .WHERE("uuid = #{uuid}").toString();
    }

    public String list(){
        return new SQL()
                .SELECT("id, permission_name, create_time, modify_time, version, is_deleted, uuid")
                .FROM("permission")
                .WHERE("is_deleted = 0").toString();
    }

    public String save(){
        return new SQL().INSERT_INTO("permission")
                .VALUES("permission_name, create_time, modify_time, version, is_deleted, uuid",
                        "#{permissionName}, now(), now(), 0, 0, #{uuid}").toString();
    }

    public String update(){
        return new SQL().UPDATE("permission")
                .SET("permission_name=#{permissionName}, modify_time=now(),version=version+1")
                .WHERE("uuid = #{uuid}").toString();
    }

    public String removeRoles(){
        return new SQL().DELETE_FROM("role_permission")
                .WHERE("permission_uuid=#{uuid}").toString();
    }

    public String insertRoles(Map<String, Object> para){

        String permissionUuid = (String)para.get("permissionUuid");
        String[] roleUuids = (String[])para.get("roleUuids");
        StringBuffer sql = new StringBuffer("insert into role_permission (role_uuid, permission_uuid) values ");
        for(String roleUuid:roleUuids){
            sql.append("(");
            sql.append(String.join(",",roleUuid,permissionUuid));
            sql.append("),");
        }
        sql.deleteCharAt(sql.length()-1);
        return sql.toString();
    }

    public String remove(){
        return new SQL()
                .UPDATE("permission")
                .SET("is_deleted = 1","modify_time=now()","version=version+1")
                .WHERE("uuid = #{uuid}")
                .toString();
    }

    public String listRoles(String uuid){
        return new SQL()
                .SELECT("r.id,r.uuid,r.role_name")
                .FROM("role r")
                .LEFT_OUTER_JOIN("role_permission rp on r.uuid = rp.role_uuid")
                .WHERE("ur.permission_uuid = #{uuid}","is_deleted = 0").toString();
    }
}
