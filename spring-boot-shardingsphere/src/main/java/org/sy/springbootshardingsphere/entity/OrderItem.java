package org.sy.springbootshardingsphere.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderItem {

    private long orderItemId;
    private String name;
    private long id;
    private long userId;

}
