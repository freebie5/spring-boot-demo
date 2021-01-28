package org.sy.springbootshardingsphere.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class OrderItem {

    private long orderItemId;
    private String name;
    private long id;
    private long userId;

}
