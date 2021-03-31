package org.sy.springbootshardingsphere.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sy.springbootshardingsphere.entity.OrderItem;

import java.util.List;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */

public interface OrderItemMapper extends BaseMapper<OrderItem> {

    List<OrderItem> findAll();

    int insertOne(OrderItem orderItem);

}
