package com.aguo.vo.params;

import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/12 23:23
 * @Description: TODO
 */
@Data
public class PageParam {
    private Integer page=1;
    private Integer pageSize=10;
}
