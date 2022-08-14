package com.atguigu.server_sta.entity.vo;

import lombok.Data;

/**
 * @BelongsProject: guli_parent
 * @BelongsPackage: com.atguigu.server_sta.entity.vo
 * @Author: chenzhiwei
 * @CreateTime: 2022-08-06  16:24
 * @Description: TODO
 * @Version: 1.0
 */

@Data
public class ConditionVo {

    // 选择查询的类型
    private String type;

    // 开始时间
    private String begin;

    // 结束时间
    private String end;
}
