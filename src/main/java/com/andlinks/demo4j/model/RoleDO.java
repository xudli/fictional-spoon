package com.andlinks.demo4j.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 角色实体类
 */
public class RoleDO implements Serializable {

    private static final long serialVersionUID = -5818603968227961655L;

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Integer version;

    private Boolean deleted;

    private String roleName;

    private List<UserDO> users;

    private List<PermissionDO> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserDO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDO> users) {
        this.users = users;
    }

    public List<PermissionDO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDO> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDO roleDO = (RoleDO) o;

        if (id != null ? !id.equals(roleDO.id) : roleDO.id != null) return false;
        if (createTime != null ? !createTime.equals(roleDO.createTime) : roleDO.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(roleDO.modifyTime) : roleDO.modifyTime != null) return false;
        if (version != null ? !version.equals(roleDO.version) : roleDO.version != null) return false;
        if (deleted != null ? !deleted.equals(roleDO.deleted) : roleDO.deleted != null) return false;
        return roleName != null ? roleName.equals(roleDO.roleName) : roleDO.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", version=" + version +
                ", deleted=" + deleted +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
