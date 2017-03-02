package com.andlinks.demo4j.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户实体类
 */
public class UserDO implements Serializable{

    private static final long serialVersionUID = 1953426709224582386L;

    private Long id;

    private String uuid;

    private Date createTime;

    private Date modifyTime;

    private Integer version;

    private Boolean deleted;

    private String userName;

    private String password;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        UserDO userDO = (UserDO) o;

        if (id != null ? !id.equals(userDO.id) : userDO.id != null) return false;
        if (uuid != null ? !uuid.equals(userDO.uuid) : userDO.uuid != null) return false;
        if (createTime != null ? !createTime.equals(userDO.createTime) : userDO.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(userDO.modifyTime) : userDO.modifyTime != null) return false;
        if (version != null ? !version.equals(userDO.version) : userDO.version != null) return false;
        if (deleted != null ? !deleted.equals(userDO.deleted) : userDO.deleted != null) return false;
        if (userName != null ? !userName.equals(userDO.userName) : userDO.userName != null) return false;
        return password != null ? password.equals(userDO.password) : userDO.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", version=" + version +
                ", deleted=" + deleted +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
