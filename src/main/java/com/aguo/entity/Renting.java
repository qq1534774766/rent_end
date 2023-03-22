package com.aguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 租房关系表(Renting)表实体类
 *
 * @author makejava
 * @since 2021-11-20 16:33:14
 */
@SuppressWarnings("serial")
@Data
public class Renting extends Model<Renting> {
    //租用ID
    @TableId(type = IdType.AUTO)
    private Integer rentingId;
    //用户ID
    private Integer userId;
    //房屋ID
    private Integer roomId;
    //创建时间
    private Date createdTime;
    //停租时间
    private Date stopTime;
    //更新时间
    private Date updateTime;
    //用户
    @TableField(select = false)
    private UUser uUser;
//    用户真实姓名
    @TableField(select = false)
    private String userRealName;
    //房屋
    @TableField(select = false)
    private ArchiRRoom archiRRoom;

    //用户名
    @TableField(select = false)
    private String username;

//    租用状态
    @TableField(select = false)
    private Boolean rentState;

    public Boolean getRentState() {
        return rentState;
    }

    public void setRentState(Boolean rentState) {
        this.rentState = rentState;
    }

    public Integer getId() {
        return rentingId;
    }

    public void setId(Integer rentingId) {
        this.rentingId = rentingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UUser getuUser() {
        return uUser;
    }

    public void setuUser(UUser uUser) {
        this.uUser = uUser;
    }

    public ArchiRRoom getArchiRRoom() {
        return archiRRoom;
    }

    public void setArchiRRoom(ArchiRRoom archiRRoom) {
        this.archiRRoom = archiRRoom;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    @Override
    public String toString() {
        return "Renting{" +
                "id=" + rentingId +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", createdTime=" + createdTime +
                ", stopTime=" + stopTime +
                ", updateTime=" + updateTime +
                ", uUser=" + uUser +
                ", userRealName='" + userRealName + '\'' +
                ", archiRRoom=" + archiRRoom +
                ", rentState=" + rentState +
                '}';
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.rentingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renting renting = (Renting) o;
        return Objects.equals(rentingId, renting.rentingId) &&
                Objects.equals(userId, renting.userId) &&
                Objects.equals(roomId, renting.roomId) &&
                Objects.equals(createdTime, renting.createdTime) &&
                Objects.equals(stopTime, renting.stopTime) &&
                Objects.equals(updateTime, renting.updateTime) &&
                Objects.equals(uUser, renting.uUser) &&
                Objects.equals(archiRRoom, renting.archiRRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentingId, userId, roomId, createdTime, stopTime, updateTime, uUser, archiRRoom);
    }

}

