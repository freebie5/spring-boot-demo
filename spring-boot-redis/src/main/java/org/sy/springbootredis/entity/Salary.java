package org.sy.springbootredis.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Author john
 * @Date 2021/4/4 2:10
 * @Version 1.0
 */
@Data
@Builder
public class Salary {

    private String id;
    private String name;
    private Long count;

}
