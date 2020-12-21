package org.sy.springbootshardingsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sy.springbootshardingsphere.entity.OrderItem;
import org.sy.springbootshardingsphere.mapper.OrderItemMapper;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderItem> findAll() {
        return orderItemMapper.findAll();
    }

}
