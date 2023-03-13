package com.aguo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 楼房表(ArchiBBuilding)表实体类
 *
 * @author makejava
 * @since 2021-11-20 14:02:07
 */
@SuppressWarnings("serial")
public class ArchiBBuilding extends Model<ArchiBBuilding> {
    //楼房ID
    @TableId(type = IdType.AUTO)
    private Integer buildingId;
    //楼房名称
    private String name;
    //地址
    private String address;
    //创建时间
    private Date createdTime;
    //更新时间
    private Date updateTime;


    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.buildingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiBBuilding that = (ArchiBBuilding) o;
        return Objects.equals(buildingId, that.buildingId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, name, address, createdTime, updateTime);
    }

    @Override
    public String toString() {
        return "ArchiBBuilding{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

