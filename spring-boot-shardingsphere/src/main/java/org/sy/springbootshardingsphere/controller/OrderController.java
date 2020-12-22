package org.sy.springbootshardingsphere.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sy.springbootshardingsphere.entity.Order;
import org.sy.springbootshardingsphere.entity.OrderItem;
import org.sy.springbootshardingsphere.service.OrderItemService;
import org.sy.springbootshardingsphere.service.OrderService;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping("/order/findAll")
    public List<Order> orderFindAll() {
        return orderService.findAll();
    }

    @RequestMapping("/orderItem/findAll")
    public List<OrderItem> orderItemFindAll() {
        return orderItemService.findAll();
    }

    @RequestMapping("/order/insertOne")
    public int orderInsertOne(@Param("orderId") long orderId, @Param("userId") long userId,
                              @Param("name") String name) {
        return orderService.insertOne(orderId, userId, name);
    }

    @RequestMapping("/orderItem/insertOne")
    public int orderItemInsertOne(@Param("orderItemId") long orderItemId, @Param("userId") long userId,
                                  @Param("name") String name) {
        return orderItemService.insertOne(orderItemId, userId, name);
    }

}
