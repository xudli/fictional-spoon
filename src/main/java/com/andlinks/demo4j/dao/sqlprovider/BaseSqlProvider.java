package com.andlinks.demo4j.dao.sqlprovider;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.jdbc.SQL;

import com.andlinks.demo4j.model.BaseDO;
import com.andlinks.demo4j.util.CamelCaseUtils;

public abstract class BaseSqlProvider<T extends BaseDO> {

	public String getById() {
		return new SQL().SELECT("*").FROM(getTableName()).WHERE("id = #{id}").toString();
	}

	public String getByUuid() {
		return new SQL().SELECT("*").FROM(getTableName()).WHERE("uuid = #{uuid}").toString();
	}

	public String list() {
		return new SQL().SELECT("*").FROM(getTableName()).WHERE("deleted = 0").toString();
	}

	public String remove() {
		return new SQL().UPDATE(getTableName()).SET("deleted = 1", "modify_time=now()", "version=version+1")
				.WHERE("uuid = #{uuid}").toString();
	}

	public String save() {
		return new SQL().INSERT_INTO(getTableName())
				.VALUES(getFieldMap().keySet().toString().substring(1, getFieldMap().keySet().toString().length() - 1),
						getFieldMap().values().toString().substring(1, getFieldMap().values().toString().length() - 1))
				.toString();
	}

	public String update() {
		Map<String, String> map = getFieldMap();
		if (map.containsKey("uuid"))
			map.remove("uuid");
		if (map.containsKey("create_time"))
			map.remove("create_time");
		if (map.containsKey("deleted"))
			map.remove("deleted");
		if (map.containsKey("version"))
			map.put("version", "version+1");
		return new SQL().UPDATE(getTableName()).SET(map.toString().substring(1, map.toString().length() - 1))
				.WHERE("uuid = #{uuid}").toString();
	}

	private Class<T> getTClass() {
		@SuppressWarnings("unchecked")
		Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return tClass;
	}

	// private String getFieldStr() {
	// Field[] fs = getTClass().getDeclaredFields();
	// if(getTClass().getSuperclass()!=null){
	// ArrayUtils.addAll(fs, getTClass().getSuperclass().getDeclaredFields());
	// }
	// StringBuffer str = new StringBuffer();
	// for (Field field : fs) {
	// if(field.getName().equals("serialVersionUID"))
	// continue;
	// str.append(HumpUtil.humpToLine(field.getName())).append(",");
	// }
	// str.substring(0, str.length() - 1);
	// return str.toString();
	// }

	private Map<String, String> getFieldMap() {
		Field[] fs = getTClass().getDeclaredFields();
		if (getTClass().getSuperclass() != null) {
			fs = ArrayUtils.addAll(fs, getTClass().getSuperclass().getDeclaredFields());
		}
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : fs) {
			if (field.getName().equals("serialVersionUID"))
				continue;
			if (field.getType().getSimpleName().equals("List"))
				continue;
			if (field.getType().getSimpleName().equals("Set"))
				continue;
			map.put(CamelCaseUtils.toUnderlineName(field.getName()), "#{" + field.getName() + "}");
		}
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("create_time"))
			map.put("create_time", "now()");
		if (map.containsKey("modify_time"))
			map.put("modify_time", "now()");
		if (map.containsKey("version"))
			map.put("version", "0");
		if (map.containsKey("deleted"))
			map.put("deleted", "0");

		return map;

	}

	private String getTableName() {
		String tableName = CamelCaseUtils.toUnderlineName(getTClass().getSimpleName().replace("DO", ""));
		return tableName;
	}

}
