package cn.org.rookie.jeesdp.common.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public abstract class BaseEntity implements Cloneable {

    private String createUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss")
    private Date createTime;
    private String updateUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss")
    private Date updateTime;
    private long flag;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getFlag() {
        return flag;
    }

    public void setFlag(long flag) {
        this.flag = flag;
    }
}
