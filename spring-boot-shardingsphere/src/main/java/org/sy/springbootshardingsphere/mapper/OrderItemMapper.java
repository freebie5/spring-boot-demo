package org.sy.springbootshardingsphere.mapper;

import org.sy.springbootshardingsphere.entity.OrderItem;

import java.util.List;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public interface OrderItemMapper {

    List<OrderItem> findAll();

    int insertOne(OrderItem orderItem);

}
