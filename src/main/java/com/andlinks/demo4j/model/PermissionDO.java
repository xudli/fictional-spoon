package com.andlinks.demo4j.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 权限实体类
 */
public class PermissionDO implements Serializable {

    private static final long serialVersionUID = 826656225461573518L;

    private Long id;

    private String uuid;

    private Date createTime;

    private Date modifyTime;

    private Integer version;

    private Boolean deleted;

    private String permissionName;

    private String permissionNameCN;

    private List<RoleDO> roles;

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

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionNameCN() {
        return permissionNameCN;
    }

    public void setPermissionNameCN(String permissionNameCN) {
        this.permissionNameCN = permissionNameCN;
    }

    public List<RoleDO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionDO that = (PermissionDO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;
        return permissionNameCN != null ? permissionNameCN.equals(that.permissionNameCN) : that.permissionNameCN == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        result = 31 * result + (permissionNameCN != null ? permissionNameCN.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PermissionDO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", version=" + version +
                ", deleted=" + deleted +
                ", permissionName='" + permissionName + '\'' +
                ", permissionNameCN='" + permissionNameCN + '\'' +
                '}';
    }
}
