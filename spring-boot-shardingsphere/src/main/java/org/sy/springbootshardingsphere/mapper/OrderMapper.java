package org.sy.springbootshardingsphere.mapper;

import org.sy.springbootshardingsphere.entity.Order;

import java.util.List;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public interface OrderMapper {

    List<Order> findAll();

    int insertOne(Order order);

}
