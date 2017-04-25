package org.deepinfo.proton.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by lixudong on 2017/4/24.
 */
@MappedSuperclass
public class BaseDO extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2695854714414036396L;

    @Column(nullable = false)
    private String uuid;
    private Date createTime;
    private Date modifyTime;
    private Integer version;
    private Boolean deleted;

    public String getUuid() {
        return uuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Integer getVersion() {
        return version;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
