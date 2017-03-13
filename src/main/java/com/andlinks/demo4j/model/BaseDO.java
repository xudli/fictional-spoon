package com.andlinks.demo4j.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基类
 * 
 * @author lixudong
 *
 */
public abstract class BaseDO implements Serializable {

	private static final long serialVersionUID = 6188209726031556507L;

	private Long id;

	private String uuid;

	private Date createTime;

	private Date modifyTime;

	private Integer version;

	private Boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return String.format("Entity of type %s with uuid: %s", getClass().getName(), getUuid());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseDO.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseDO other = (BaseDO) obj;
		return getUuid() != null ? getUuid().equals(other.getUuid()) : false;
	}

	@Override
	public int hashCode() {
		int hashCode = 34;
		hashCode += getUuid() != null ? getUuid().hashCode() * 31 : 0;
		return hashCode;
	}

}
