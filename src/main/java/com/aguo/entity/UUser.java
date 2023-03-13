package com.aguo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * 用户名表(UUser)表实体类
 *
 * @author makejava
 * @since 2021-11-11 13:40:00
 */
@SuppressWarnings("serial")
public class UUser extends Model<UUser> implements UserDetails {
    //用户ID
    @TableId(type = IdType.AUTO)
    private Integer userId;
    //角色ID
    private Integer roleId;
    //用户名
    private String username;
    //用户密码
    private String password;
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

    public UUser() {
    }

    public UUser(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = username;
    }

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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (URole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * isAccountNonExpired()：当前账户是否未过期
     *
     * @return true=未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isAccountNonLocked()：当前账户是否未锁定
     *
     * @return true = 未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return "0".equals(locked);
    }

    /**
     * isCredentialsNonExpired()：当前账户密码是否未过期
     *
     * @return true = 密码未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * isEnabled()：当前账户是否可用
     *
     * @return true =可用
     */
    @Override
    public boolean isEnabled() {
        return "1".equals(enable);
    }

    @Override
    public String toString() {
        return "UUser{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
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

