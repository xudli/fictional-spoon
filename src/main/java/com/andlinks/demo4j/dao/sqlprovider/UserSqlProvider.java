package com.andlinks.demo4j.dao.sqlprovider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.andlinks.demo4j.model.UserDO;

/**
 * Created by 王凯斌 on 2017/3/2. user的SqlProvider类
 */
public class UserSqlProvider extends BaseSqlProvider<UserDO> {

	public String getByUserName() {
		return new SQL().SELECT("id, user_name, password, create_time, modify_time, version, deleted, uuid")
				.FROM("user").WHERE("user_name = #{userName}").toString();
	}

	public String updateWithoutPassword() {
		return new SQL().UPDATE("user").SET("user_name=#{userName}, modify_time=now(),version=version+1")
				.WHERE("uuid = #{uuid}").toString();
	}

	public String removeRoles() {
		return new SQL().DELETE_FROM("user_role").WHERE("user_uuid=#{uuid}").toString();
	}

	public String insertRoles(Map<String, Object> para) {

		String userUuid = (String) para.get("userUuid");
		String[] roleUuids = (String[]) para.get("roleUuids");
		StringBuffer sql = new StringBuffer("insert into user_role (user_uuid, role_uuid) values ");
		for (String roleUuid : roleUuids) {
			sql.append("(");
			sql.append(String.join(",", "'" + userUuid + "'", "'" + roleUuid + "'"));
			sql.append("),");
		}
		sql.deleteCharAt(sql.length() - 1);
		return sql.toString();
	}

	public String listRoles(String uuid) {
		return new SQL().SELECT("r.id,r.uuid,r.role_name").FROM("role r")
				.LEFT_OUTER_JOIN("user_role ur on r.uuid = ur.role_uuid").WHERE("ur.user_uuid = #{uuid}", "deleted = 0")
				.toString();
	}

	public String listPermissions(String uuid) {
		String format = "select id,uuid,permission_name from permission where uuid in " + "(select rp.permission_uuid "
				+ "from role r left join user_role ur on r.uuid = ur.role_uuid "
				+ "left join role_permission rp on r.uuid = rp.role_uuid " + "where ur.user_uuid = '%s')";

		return String.format(format, uuid);
	}
}
