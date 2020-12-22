package org.sy.springbootshardingsphere.mapper;

import org.sy.springbootshardingsphere.entity.Order;

import java.util.List;

public interface OrderMapper {

    List<Order> findAll();

    int insertOne(Order order);

}
