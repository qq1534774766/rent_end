package com.aguo.entity.vol;

import com.aguo.entity.URole;
import com.aguo.entity.UUser;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.util.List;

@TableName(value="u_user")
public class UUserVol extends Model<UUserVol> {
    //用户ID
    @TableId(type = IdType.AUTO)
    private Integer userId;
    //角色ID
    private Integer roleId;
    //用户名
    private String username;
    //性别
    private String sex;
    //姓名
    private String name;
    //电话号码
    private String phoneNumber;
    //身份证号码
    private String identity;
    //锁定状态 0未锁 1已锁
    private String locked;
    //可用状态 1可用 0不可用
    private String enable;
    //创建时间
    private Date createdTime;
    //更新时间
    private Date updateTime;
    @TableField(select = false)
    private List<URole> roles;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<URole> getRoles() {
        return roles;
    }

    public void setRoles(List<URole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UUserVol{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identity='" + identity + '\'' +
                ", locked='" + locked + '\'' +
                ", enable='" + enable + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", roles=" + roles +
                '}';
    }
}
