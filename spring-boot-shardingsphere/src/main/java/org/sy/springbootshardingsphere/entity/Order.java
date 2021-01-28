package org.sy.springbootshardingsphere.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Order {

    private long orderId;
    private String name;
    private long id;
    private long userId;
    private BigDecimal money;

}
