package com.aguo.entity.vol;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    //记录ID
    private Integer rentingId;
    //用户ID
    private Integer userId;
    //房屋ID
    private Integer roomId;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    //停租时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopTime;

    private Boolean rentState;
}
