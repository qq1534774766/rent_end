package com.aguo.vo.params;

import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/17 20:33
 * @Description: TODO
 */
@Data
public class RenterParam {
    private String username;
    private String name;
    private String phoneNumber;
    private Boolean rentState;
}
