package org.sy.springbootshardingsphere.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Order {

    private long orderId;
    private String name;
    private long id;
    private long userId;

}
