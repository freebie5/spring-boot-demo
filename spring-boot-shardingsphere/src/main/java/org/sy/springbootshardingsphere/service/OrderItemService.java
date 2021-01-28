package org.sy.springbootshardingsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sy.springbootshardingsphere.entity.OrderItem;
import org.sy.springbootshardingsphere.mapper.OrderItemMapper;

import java.util.List;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Service
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderItem> findAll() {
        return orderItemMapper.findAll();
    }

    public int insertOne(long orderItemId, long userId, String name) {
        OrderItem orderItem = new OrderItem().setOrderItemId(orderItemId).setName(name).setUserId(userId);
        return orderItemMapper.insertOne(orderItem);
    }

}
