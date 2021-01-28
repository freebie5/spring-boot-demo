package org.sy.springbootshardingsphere.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sy.springbootshardingsphere.entity.Order;
import org.sy.springbootshardingsphere.entity.OrderItem;
import org.sy.springbootshardingsphere.service.OrderItemService;
import org.sy.springbootshardingsphere.service.OrderService;
import org.sy.springbootshardingsphere.service.UserService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

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
                              @Param("name") String name, @Param("money") BigDecimal money) {
        return orderService.insertOne(orderId, userId, name, money);
    }

    @RequestMapping("/orderItem/insertOne")
    public int orderItemInsertOne(@Param("orderItemId") long orderItemId, @Param("userId") long userId,
                                  @Param("name") String name) {
        return orderItemService.insertOne(orderItemId, userId, name);
    }

    @RequestMapping("/user/insertOne")
    public int userInsertOne(@Param("userId") long userId, @Param("name") String name) {
        return userService.insertOne(userId, name);
    }

}
