package com.andlinks.demo4j.dao.sqlprovider;

import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/3/2.
 * role的SqlProvider类
 */
public class RoleSqlProvider {

    public String getById(){
        return new SQL()
                .SELECT("id, role_name, create_time, modify_time, version, is_deleted, uuid")
                .FROM("role")
                .WHERE("id = #{id}").toString();
    }

    public String getByUuid(){
        return new SQL()
                .SELECT("id, role_name, create_time, modify_time, version, is_deleted, uuid")
                .FROM("role")
                .WHERE("uuid = #{uuid}").toString();
    }

    public String list(){
        return new SQL()
                .SELECT("id, role_name, create_time, modify_time, version, is_deleted, uuid")
                .FROM("role")
                .WHERE("is_deleted = 0").toString();
    }

    public String save(){
        return new SQL().INSERT_INTO("role")
                .VALUES("role_name, create_time, modify_time, version, is_deleted, uuid",
                        "#{roleName}, now(), now(), 0, 0, #{uuid}").toString();
    }

    public String update(){
        return new SQL().UPDATE("role")
                .SET("role_name=#{roleName}, modify_time=now(),version=version+1")
                .WHERE("uuid = #{uuid}").toString();
    }

    public String removeUsers(){
        return new SQL().DELETE_FROM("user_role")
                .WHERE("role_uuid=#{uuid}").toString();
    }

    public String insertUsers(Map<String, Object> para){

        String roleUuid = (String)para.get("roleUuid");
        String[] userUuids = (String[])para.get("userUuids");
        StringBuffer sql = new StringBuffer("insert into user_role (user_uuid, role_uuid) values ");
        for(String userUuid:userUuids){
            sql.append("(");
            sql.append(String.join(",","'"+userUuid+"'","'"+roleUuid+"'"));
            sql.append("),");
        }
        sql.deleteCharAt(sql.length()-1);
        return sql.toString();
    }

    public String removePermissions(){
        return new SQL().DELETE_FROM("role_permission")
                .WHERE("role_uuid=#{uuid}").toString();
    }

    public String insertPermissions(Map<String, Object> para){

        String roleUuid = (String)para.get("roleUuid");
        String[] permissionUuids = (String[])para.get("permissionUuids");
        StringBuffer sql = new StringBuffer("insert into role_permission (role_uuid, permission_uuid) values ");
        for(String permissionUuid:permissionUuids){
            sql.append("(");
            sql.append(String.join(",","'"+roleUuid+"'","'"+permissionUuid+"'"));
            sql.append("),");
        }
        sql.deleteCharAt(sql.length()-1);
        return sql.toString();
    }

    public String remove(){
        return new SQL()
                .UPDATE("role")
                .SET("is_deleted = 1","modify_time=now()","version=version+1")
                .WHERE("uuid = #{uuid}")
                .toString();
    }

    public String listUsers(String uuid){
        return new SQL()
                .SELECT("u.id,u.uuid,u.user_name,u.password")
                .FROM("user u")
                .LEFT_OUTER_JOIN("user_role ur on u.uuid = ur.user_uuid")
                .WHERE("ur.role_uuid = #{uuid}","is_deleted = 0").toString();
    }

    public String listPermissions(){
        return new SQL()
                .SELECT("p.id,p.uuid,p.permission_name")
                .FROM("permission p")
                .LEFT_OUTER_JOIN("role_permission rp on p.uuid = rp.permission_uuid")
                .WHERE("rp.role_uuid = #{uuid}","is_deleted = 0").toString();
    }
}
