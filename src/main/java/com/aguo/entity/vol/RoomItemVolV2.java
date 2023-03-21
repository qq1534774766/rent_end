package com.aguo.entity.vol;

import lombok.Data;

import java.util.Date;

/**
 * 房屋查询结果
 *
 * @author Administrator
 */
@Data
public class RoomItemVolV2 {
    //用户信息

    private Integer userId;
    private String name;
    private String username;
    private String phoneNumber;

    //房屋信息

    private Integer buildingId;
    private String buildingName;
    private String address;
    private Integer roomId;
    private String houseNumber;

    //租用信息

    private Boolean rentState;
    private Date createdTime;
    private Date stopTime;


}
