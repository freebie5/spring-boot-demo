package org.sy.springbootshardingsphere.mapper;

import org.sy.springbootshardingsphere.entity.OrderItem;

import java.util.List;

public interface OrderItemMapper {

    List<OrderItem> findAll();

    int insertOne(OrderItem orderItem);

}
