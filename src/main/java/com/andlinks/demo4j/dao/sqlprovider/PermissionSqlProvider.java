package com.andlinks.demo4j.dao.sqlprovider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.andlinks.demo4j.model.PermissionDO;

/**
 * Created by 王凯斌 on 2017/3/2. permission的SqlProvider类
 */
public class PermissionSqlProvider extends BaseSqlProvider<PermissionDO> {

	public String removeRoles() {
		return new SQL().DELETE_FROM("role_permission").WHERE("permission_uuid=#{uuid}").toString();
	}

	public String insertRoles(Map<String, Object> para) {

		String permissionUuid = (String) para.get("permissionUuid");
		String[] roleUuids = (String[]) para.get("roleUuids");
		StringBuffer sql = new StringBuffer("insert into role_permission (role_uuid, permission_uuid) values ");
		for (String roleUuid : roleUuids) {
			sql.append("(");
			sql.append(String.join(",", roleUuid, permissionUuid));
			sql.append("),");
		}
		sql.deleteCharAt(sql.length() - 1);
		return sql.toString();
	}

	public String listRoles(String uuid) {
		return new SQL().SELECT("r.id,r.uuid,r.role_name").FROM("role r")
				.LEFT_OUTER_JOIN("role_permission rp on r.uuid = rp.role_uuid")
				.WHERE("rp.permission_uuid = #{uuid}", "deleted = 0").toString();
	}

}
