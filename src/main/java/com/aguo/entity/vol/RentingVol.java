package com.aguo.entity.vol;

import lombok.Data;

import java.util.Date;

/**
 * 租房记录表视图
 *
 * @Author: wenqiaogang
 * @DateTime: 2023/3/21 0:22
 * @Description: TODO
 */
@Data
public class RentingVol {
    //用户ID
    private Integer userId;
    //房屋ID
    private Integer roomId;
    //创建时间
    private Date createdTime;
    //停租时间
    private Date stopTime;

    private Boolean rentState;
}
