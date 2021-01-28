package org.sy.springbootshardingsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sy.springbootshardingsphere.entity.Order;
import org.sy.springbootshardingsphere.mapper.OrderMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public int insertOne(long orderId, long userId, String name, BigDecimal money) {
        Order order = new Order().setOrderId(orderId).setName(name).setUserId(userId).setMoney(money);
        return orderMapper.insertOne(order);
    }

}
