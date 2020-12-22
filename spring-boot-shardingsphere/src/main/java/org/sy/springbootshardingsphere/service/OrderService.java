package org.sy.springbootshardingsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sy.springbootshardingsphere.entity.Order;
import org.sy.springbootshardingsphere.mapper.OrderMapper;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public int insertOne(long orderId, long userId, String name) {
        Order order = new Order().setOrderId(orderId).setName(name).setUserId(userId);
        return orderMapper.insertOne(order);
    }

}
